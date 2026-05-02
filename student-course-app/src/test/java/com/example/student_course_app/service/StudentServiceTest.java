package com.example.student_course_app.service;

import com.example.student_course_app.entity.Course;
import com.example.student_course_app.entity.Student;
import com.example.student_course_app.repository.StudentRepository;
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
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(
                new Student(1L, "Alice", "alice@example.com", "CS", null),
                new Student(2L, "Bob", "bob@example.com", "Physics", null)
        ));
        assertEquals(2, studentService.getAllStudents().size());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student(1L, "Alice", "alice@example.com", "CS", null);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        assertEquals("Alice", studentService.getStudentById(1L).getName());
    }

    @Test
    void testSaveStudent_DuplicateEmail_ThrowsException() {
        when(studentRepository.existsByEmail("alice@example.com")).thenReturn(true);
        Student student = new Student(null, "Alice", "alice@example.com", "CS", null);
        assertThrows(RuntimeException.class, () -> studentService.saveStudent(student));
    }

    @Test
    void testSaveStudent_Success() {
        when(studentRepository.existsByEmail("new@example.com")).thenReturn(false);
        Student student = new Student(null, "New Student", "new@example.com", "CS", null);
        studentService.saveStudent(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(1L, "Alice Updated", "alice@example.com", "CS", null);
        studentService.updateStudent(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentsByCourse() {
        when(studentRepository.findStudentsByCourseId(1L)).thenReturn(List.of(
                new Student(1L, "Alice", "alice@example.com", "CS", null)
        ));
        assertEquals(1, studentService.getStudentsByCourse(1L).size());
    }
}