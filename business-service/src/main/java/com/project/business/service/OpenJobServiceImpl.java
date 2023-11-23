package com.project.business.service;

import com.project.business.dto.get.GetAlumniDto;
import com.project.business.dto.get.GetFullOpenJobDto;
import com.project.business.dto.post.PostOpenJobDto;
import com.project.business.model.Application;
import com.project.business.model.OpenJob;
import com.project.business.model.id.ApplicationId;
import com.project.business.repository.CompanyRepository;
import com.project.business.repository.OpenJobRepository;
import com.project.business.service.feign.UserServiceClient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAllowedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenJobServiceImpl implements OpenJobService {
    private final OpenJobRepository openJobRepository;
    private final CompanyRepository companyRepo;
    private final UserServiceClient userServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public GetFullOpenJobDto addOpenJob(PostOpenJobDto openJobDto, String mail) {
        var creatorDto = userServiceClient.getUser(mail);
        var company = companyRepo.findById(openJobDto.getCompanyId()).orElseThrow();
        OpenJob openJob = modelMapper.map(openJobDto, OpenJob.class);
        openJob.setId(null);
        openJob.setCompany(company);
        openJob.setCreator_id(creatorDto.getId());
        openJob.setApplications(openJobDto.getApplicantIds().stream()
                .map(applicantId -> {
                    Application application = new Application();
                    application.setOpenJob(openJob);
                    var applicationId = new ApplicationId();
                    applicationId.setOpenJobId(openJob.getId());
                    applicationId.setAlumniId(applicantId);
                    application.setId(applicationId);
                    return application;
                }).collect(Collectors.toList()));
        openJobRepository.save(openJob);
        return populateAndReturnDto(openJob);
    }

    @Override
    public List<GetFullOpenJobDto> getAll() {
        return openJobRepository.findAll().stream()
                .map(this::populateAndReturnDto)
                .collect(Collectors.toList());
    }

    @Override
    public GetFullOpenJobDto getById(Long id) {
        OpenJob openJob = openJobRepository.findById(id).orElseThrow();
        return populateAndReturnDto(openJob);
    }

    @Override
    public List<GetFullOpenJobDto> searchByState(String state) {
        return openJobRepository.findAllByCompany_Address_State(state).stream()
                .map(this::populateAndReturnDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetFullOpenJobDto> searchByCity(String city) {
        return openJobRepository.findAllByCompany_Address_City(city).stream()
                .map(this::populateAndReturnDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<GetFullOpenJobDto> searchBycompanyName(String companyName) {
        return openJobRepository.findAllByCompany_Name(companyName).stream()
                .map(this::populateAndReturnDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public GetFullOpenJobDto updateById(Long id, PostOpenJobDto openJobDto, String mail) {
        var creatorDto = userServiceClient.getUser(mail);
        var company = companyRepo.findById(openJobDto.getCompanyId()).orElseThrow();
        OpenJob openJob = modelMapper.map(openJobDto, OpenJob.class);
        if (!openJob.getCreator_id().equals(creatorDto.getId()) && !creatorDto.getRoleName().equals("ADMIN")) {
            throw new NotAllowedException("not allowed to edit others job advertisement");
        }
        openJob.setCompany(company);
        openJob.setCreator_id(creatorDto.getId());
        openJob.setApplications(openJobDto.getApplicantIds().stream()
                .map(applicantId -> {
                    Application application = new Application();
                    application.setOpenJob(openJob);
                    var applicationId = new ApplicationId();
                    applicationId.setOpenJobId(openJob.getId());
                    applicationId.setAlumniId(applicantId);
                    application.setId(applicationId);
                    return application;
                }).collect(Collectors.toList()));
        openJobRepository.save(openJob);
        return populateAndReturnDto(openJob);
    }

    @Transactional
    @Override
    public GetFullOpenJobDto deleteById(Long id) {
        OpenJob openJob = openJobRepository.findById(id).orElseThrow();
        GetFullOpenJobDto retOpenJob = populateAndReturnDto(openJob);
        openJobRepository.delete(openJob);
        return retOpenJob;
    }

    @Override
    public List<GetAlumniDto> getApplicants(Long id, String mail) {
        var data = openJobRepository.findById(id).orElseThrow();
        var user = userServiceClient.getUser(mail);
        if (user.getRoleName().equals("ADMIN")) {
            return data.getApplications().stream()
                    .map(application -> {
                        var applicantId = application.getId().getAlumniId();
                        return userServiceClient.getUser(applicantId);
                    })
                    .collect(Collectors.toList());
        }
        throw new NotAllowedException("authority err");
    }

    private GetFullOpenJobDto populateAndReturnDto(OpenJob openJob) {
        GetFullOpenJobDto obj = modelMapper.map(openJob, GetFullOpenJobDto.class);
        obj.setCompanyName(openJob.getCompany().getName());
        obj.setCompanyIndustry(openJob.getCompany().getIndustry());
        obj.setState(openJob.getCompany().getAddress().getState());
        obj.setCity(openJob.getCompany().getAddress().getCity());
//        obj.setApplicants(
//                openJob.getApplicants().stream()
//                        .map(alumni -> modelMapper.map(alumni, GetFullOpenJobDto.InnerAlumniDto.class))
//                        .collect(Collectors.toList())
//        );
        return obj;
    }
}
