package edu.ea.jobservice.service.impl;


import edu.ea.jobservice.model.Job;
import edu.ea.jobservice.repository.JobRepo;
import edu.ea.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueueService {
    private final JobRepo jobRepo;

    @RabbitListener(queues = {"job.queue"})
    public void deleteUserListener(Message message)
    {
        try {
            Long userId = Long.parseLong(new String(message.getBody()));
            System.out.println(" receive delete user in Job service user id:" + userId);
            List<Job> byUserId = jobRepo.findByUserId(userId);
            for (Job job : byUserId)
            {
                job.setDeleted(true);
            }
            jobRepo.saveAll(byUserId);

            List<Job> byAndAppliedUsersContaining = jobRepo.findByAndAppliedUsersContaining(userId);
            for (Job job : byAndAppliedUsersContaining)
            {
                job.getAppliedUsers().remove(userId);
            }
            jobRepo.saveAll(byAndAppliedUsersContaining);

        }catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
