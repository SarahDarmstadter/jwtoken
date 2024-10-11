package com.token.connexion.contollers;

import com.token.connexion.models.Student;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
  private List<Student> students =
      new ArrayList<>(List.of(new Student("Navin", 60), new Student("Kiran", 65)));

  @GetMapping("/students")
  public List<Student> getStudents() {
    return students;
  }

  @PostMapping("/students")
  public Student addStudent(@RequestBody Student student) {
    students.add(student);
    return student;
  }

  @GetMapping("/csrf")
  public CsrfToken getToken(HttpServletRequest httpRequest) {
    return (CsrfToken) httpRequest.getAttribute("_csrf");
  }
}
