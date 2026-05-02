package com.example.student_course_app.repository;

import com.example.student_course_app.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Custom query — returns courses that have at least one student enrolled
    @Query("SELECT DISTINCT c FROM Course c INNER JOIN c.students s")
    List<Course> findCoursesWithStudents();

    boolean existsByTitle(String title);
}