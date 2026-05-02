package com.example.student_course_app.repository;

import com.example.student_course_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom inner join query between students and courses
    @Query("SELECT s FROM Student s INNER JOIN s.course c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(Long courseId);

    // Find by email for duplicate checking
    boolean existsByEmail(String email);
}