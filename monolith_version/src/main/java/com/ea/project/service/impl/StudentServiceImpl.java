package com.ea.project.service.impl;

import java.util.Optional;

import com.ea.project.dto.StudentDto;
import com.ea.project.entity.Address;
import com.ea.project.entity.Student;
import com.ea.project.entity.User;
import com.ea.project.respository.StudentRepo;
import com.ea.project.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto delete(User student) {
        Student tempStudent = findById(student.getId());
        tempStudent.setDeleted(true);
        Student newStudent = studentRepo.save(tempStudent);
        return modelMapper.map(newStudent, StudentDto.class);
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);

        if (!optionalStudent.isPresent()) {
            // TODO: throw exception
        }

        return optionalStudent.get();
    }

    @Override
    public StudentDto update(StudentDto studentDto) {
        Student tempStudent = findById(studentDto.getId());
        tempStudent.setGpa(studentDto.getGpa());
        tempStudent.setMajor(studentDto.getMajor());

        tempStudent.setFirstName(studentDto.getFirstName());
        tempStudent.setLastName(studentDto.getLastName());
        tempStudent.setEmail(studentDto.getEmail());
        tempStudent.setAddress(new Address());
        tempStudent.getAddress().setCity(studentDto.getAddress().getCity());
        tempStudent.getAddress().setState(studentDto.getAddress().getState());
        tempStudent.getAddress().setStreet(studentDto.getAddress().getStreet());
        tempStudent.getAddress().setZip(studentDto.getAddress().getZip());

        Student student = studentRepo.save(tempStudent);
        return modelMapper.map(student, StudentDto.class);
    }

}
