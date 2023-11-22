package com.ea.project.util;

import com.ea.project.dto.AddressDto;
import com.ea.project.dto.StudentDto;
import com.ea.project.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public StudentDto mapStudentToDTO(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setEmail(student.getEmail());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setGpa(student.getGpa());
        studentDto.setMajor(student.getMajor());
        studentDto.setAddress(new AddressDto());
        studentDto.getAddress().setCity(student.getAddress().getCity());
        studentDto.getAddress().setState(student.getAddress().getState());
        studentDto.getAddress().setStreet(student.getAddress().getStreet());
        studentDto.getAddress().setZip(student.getAddress().getZip());
        return studentDto;
    }
}
