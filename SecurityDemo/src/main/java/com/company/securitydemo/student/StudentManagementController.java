package com.company.securitydemo.student;

import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("management/api/students")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Rinat Hajiyev"),
            new Student(2, "Yusif Hasani"),
            new Student(3, "Farid Asadli")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:write')")
    public void addNewStudent(@RequestBody Student student){
        System.out.println("addNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", studentId, student));
    }
}
