package com.example.security;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private static final List<Student> STUDENT= Arrays.asList(
            new Student(1,"James"),
            new Student(2,"Maria"),
            new Student(4,"Smith")
    );
    @GetMapping(path= "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENT.stream().filter(student -> studentId.equals(student.getStudentId())).
                findFirst().
                orElseThrow(() -> new IllegalStateException("Student" +studentId + "does not exist"));

    }
}
