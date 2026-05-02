package com.example.student_course_app;

import com.example.student_course_app.entity.Course;
import com.example.student_course_app.entity.Student;
import com.example.student_course_app.repository.CourseRepository;
import com.example.student_course_app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(CourseRepository courseRepo, StudentRepository studentRepo) {
        return args -> {
            // Create 10 courses
            Course c1 = courseRepo.save(new Course(null, "Mathematics", "Dr. Smith", 4, null));
            Course c2 = courseRepo.save(new Course(null, "Physics", "Dr. Johnson", 3, null));
            Course c3 = courseRepo.save(new Course(null, "Chemistry", "Dr. Williams", 3, null));
            Course c4 = courseRepo.save(new Course(null, "Computer Science", "Dr. Brown", 4, null));
            Course c5 = courseRepo.save(new Course(null, "Biology", "Dr. Davis", 3, null));
            Course c6 = courseRepo.save(new Course(null, "History", "Dr. Miller", 2, null));
            Course c7 = courseRepo.save(new Course(null, "English", "Dr. Wilson", 2, null));
            Course c8 = courseRepo.save(new Course(null, "Economics", "Dr. Moore", 3, null));
            Course c9 = courseRepo.save(new Course(null, "Psychology", "Dr. Taylor", 3, null));
            Course c10 = courseRepo.save(new Course(null, "Philosophy", "Dr. Anderson", 2, null));

            // Create 10 students
            studentRepo.save(new Student(null, "Alice Johnson", "alice@example.com", "CS", c1));
            studentRepo.save(new Student(null, "Bob Smith", "bob@example.com", "Physics", c2));
            studentRepo.save(new Student(null, "Carol White", "carol@example.com", "Chemistry", c3));
            studentRepo.save(new Student(null, "David Brown", "david@example.com", "CS", c4));
            studentRepo.save(new Student(null, "Emma Davis", "emma@example.com", "Biology", c5));
            studentRepo.save(new Student(null, "Frank Miller", "frank@example.com", "History", c6));
            studentRepo.save(new Student(null, "Grace Wilson", "grace@example.com", "English", c7));
            studentRepo.save(new Student(null, "Henry Moore", "henry@example.com", "Economics", c8));
            studentRepo.save(new Student(null, "Isla Taylor", "isla@example.com", "Psychology", c9));
            studentRepo.save(new Student(null, "Jack Anderson", "jack@example.com", "Philosophy", c10));
        };
    }
}