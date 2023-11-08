package com.project.alumni.service.alumniDirectory;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.alumniDirectory.SearchAlumniDirectoryDto;
import com.project.alumni.entity.User;
import com.project.alumni.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumniDirectoryServiceImpl implements AlumniDirectoryService {


    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<UserFullDetailsDto> alumniDirectorySearch(SearchAlumniDirectoryDto searchAlumniDirectoryDto) {
        {

            System.out.println(searchAlumniDirectoryDto);
            List<User> users = userRepo.findUsersInDirectory(
                    searchAlumniDirectoryDto.getGradYear(),
                    searchAlumniDirectoryDto.getCoursesIds(),
                    searchAlumniDirectoryDto.getCity(),
                    searchAlumniDirectoryDto.getState(),
                    searchAlumniDirectoryDto.getIndustry()
            );

            return users.stream().map((u) -> modelMapper.map(u, UserFullDetailsDto.class))
                    .collect(Collectors.toList());
        }


    }
}
