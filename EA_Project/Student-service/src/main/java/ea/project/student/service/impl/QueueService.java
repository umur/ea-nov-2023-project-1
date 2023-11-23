package ea.project.student.service.impl;

import ea.project.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueService {
    private final StudentService studentService;

    @RabbitListener(queues = {"student.queue"})
    public void deleteUserListener(Message message)
    {
        try {
            Long userId = Long.parseLong(new String(message.getBody()));
            System.out.println(" receive delete user in student service user id:" + userId);
            studentService.remove(userId);
        }catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
