package com.company.securitydemo.student;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController()
@RequestMapping("api/students")
public class StudentController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Rinat Hajiyev"),
            new Student(2, "Yusif Hasani"),
            new Student(3, "Farid Asadli")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return students.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student " + studentId + " does not exist"));
    }
}
