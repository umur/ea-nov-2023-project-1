package com.project.user.service;

import com.project.user.dto.get.GetAllAlumniDto;
import com.project.user.dto.get.GetAlumniAuth;
import com.project.user.dto.get.GetFullAlumniDto;
import com.project.user.dto.post.AuthRequest;
import com.project.user.dto.put.PutFullAlumniDto;


import java.util.List;

public interface AlumniService {
    List<GetAllAlumniDto> getAllAlumni();
    GetFullAlumniDto getAlumniById(long id);

    GetAlumniAuth getAlumniByMail(String mail);

    GetAlumniAuth saveAlumni(AuthRequest getAlumniAuth);
    void updateAlumni(long id, PutFullAlumniDto postFullAlumniDto, String mail);
    void deleteAlumni(long id);

    void activateAlumni(long id);

    List<GetFullAlumniDto> getAllAlumniByLocation(String location);

    List<GetFullAlumniDto> getAlumniByIndustry(String industry);


//    GetFullAlumniDto getAlumniByMail(String mail);
}
