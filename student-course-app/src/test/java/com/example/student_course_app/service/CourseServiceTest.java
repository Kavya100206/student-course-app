package com.example.student_course_app.service;

import com.example.student_course_app.entity.Course;
import com.example.student_course_app.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(
                new Course(1L, "Math", "Dr. Smith", 4, null),
                new Course(2L, "Physics", "Dr. Jones", 3, null)
        ));
        assertEquals(2, courseService.getAllCourses().size());
    }

    @Test
    void testGetCourseById() {
        Course course = new Course(1L, "Math", "Dr. Smith", 4, null);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        assertEquals("Math", courseService.getCourseById(1L).getTitle());
    }

    @Test
    void testSaveCourse_DuplicateTitle_ThrowsException() {
        when(courseRepository.existsByTitle("Math")).thenReturn(true);
        Course course = new Course(null, "Math", "Dr. Smith", 4, null);
        assertThrows(RuntimeException.class, () -> courseService.saveCourse(course));
    }

    @Test
    void testSaveCourse_Success() {
        when(courseRepository.existsByTitle("Physics")).thenReturn(false);
        Course course = new Course(null, "Physics", "Dr. Jones", 3, null);
        courseService.saveCourse(course);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testUpdateCourse() {
        Course course = new Course(1L, "Math Updated", "Dr. Smith", 4, null);
        courseService.updateCourse(course);
        verify(courseRepository, times(1)).save(course);
    }
}