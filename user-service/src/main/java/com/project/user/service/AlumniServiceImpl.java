package com.project.user.service;

import com.project.user.dto.get.GetAllAlumniDto;
import com.project.user.dto.get.GetAlumniAuth;
import com.project.user.dto.get.GetFullAlumniDto;
import com.project.user.dto.post.AuthRequest;
import com.project.user.dto.put.PutFullAlumniDto;
import com.project.user.model.Alumni;
import com.project.user.repository.AlumniRepository;
import com.project.user.repository.RoleTypeRepository;
import com.project.user.service.feign.BusinessServiceClient;
import jakarta.ws.rs.NotAllowedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumniServiceImpl implements AlumniService {
    private final AlumniRepository alumniRepository;
    private final RoleTypeRepository roleTypeRepository;
    private final ModelMapper modelMapper;
    private final BusinessServiceClient businessServiceClient;
    private final RabbitTemplate rabbitTemplate;


    @Override
    public List<GetAllAlumniDto> getAllAlumni() {
        var alumniList = alumniRepository.findAll();
        return alumniList.stream()
                .map(alumni -> modelMapper.map(alumni, GetAllAlumniDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetFullAlumniDto getAlumniById(long id) {
        try {
            return mapGetResponse(alumniRepository.findById(id).orElseThrow(), false, true);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public GetAlumniAuth getAlumniByMail(String mail) {
        try {
            return modelMapper.map(alumniRepository.findAlumniByMail(mail).orElseThrow(), GetAlumniAuth.class);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public GetAlumniAuth saveAlumni(AuthRequest authRequest) {
        Alumni alumni = modelMapper.map(authRequest, Alumni.class);
        alumni.setRole(roleTypeRepository.findById(authRequest.getRoleId()).orElseThrow());
        if (alumniRepository.findAlumniByMail(alumni.getMail()).isPresent()) {
            return null;
        }
        alumni.setId(null);
        var result = alumniRepository.save(alumni);
        return GetAlumniAuth.builder()
                .mail(result.getMail())
                .password(result.getPassword())
                .roleId(result.getRole().getId()).build();
    }

    @Override
    public void updateAlumni(long id, PutFullAlumniDto putFullAlumniDto, String mail) {
        Optional<Alumni> optinalCurrentAlumni = alumniRepository.findById(id);
        Optional<Alumni> optionalChangerUser = alumniRepository.findAlumniByMail(mail);
        var currentAlumni = optinalCurrentAlumni.orElseThrow();
        var changeUser = optionalChangerUser.orElseThrow();
        if (!currentAlumni.getMail().equals(mail) && !changeUser.getRole().getName().equals("ADMIN")) {
            throw new NotAllowedException("This operation is not allowed.");
        }
        Alumni alumni = modelMapper.map(putFullAlumniDto, Alumni.class);
        alumni.setId(id);
        alumni.setRole(roleTypeRepository.findById(putFullAlumniDto.getRoleId()).orElseThrow());
        var result = alumniRepository.save(alumni);
        var profExpDtos = putFullAlumniDto.getProfExperiences();
        profExpDtos.forEach(pe -> {
            pe.setAlumniId(result.getId());
            rabbitTemplate.convertAndSend("direct-prof-experience-exchange",
                    "create-update-prof-experience", pe);
        });
    }

    @Override
    public void deleteAlumni(long id) {
        alumniRepository.deleteById(id);
        rabbitTemplate.convertAndSend("delete-alumni-exchange", "", id);
    }

    @Override
    public void activateAlumni(long id) {
        var alumniopt = alumniRepository.findById(id);
        var alumni = alumniopt.orElseThrow();
        alumni.setDeleted(false);
    }

    @Override
    public List<GetFullAlumniDto> getAllAlumniByLocation(String location) {
        return alumniRepository.findAlumniByLocation(location).stream()
                .map(alumni -> mapGetResponse(alumni, true, true))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetFullAlumniDto> getAlumniByIndustry(String industry) {
        var peList = businessServiceClient.getProfExperiencesByIndustry(industry);
        return peList.stream()
                .map(pe -> {
                    var alumni = alumniRepository.findById(pe.getAlumniId()).orElseThrow();
                    var alumniDto =  mapGetResponse(alumni, true, false);
                    alumniDto.setProfExperiences(Collections.singletonList(pe));
                    return alumniDto;
                })
                .collect(Collectors.toList());
    }


    private GetFullAlumniDto mapGetResponse(Alumni alumni, boolean maskPassword, boolean setPe) {
        var alumniDto = modelMapper.map(alumni, GetFullAlumniDto.class);
        alumniDto.setRoleName(alumni.getRole().getName());
        if (setPe) {
            var peList = businessServiceClient.getProfExperiencesByAlumniId(alumni.getId());
            alumniDto.setProfExperiences(peList);
        }
        if (maskPassword)
            alumniDto.setPassword(null);
        return alumniDto;

    }


}
