package com.example.student_course_app.controller;

import com.example.student_course_app.entity.Course;
import com.example.student_course_app.entity.Student;
import com.example.student_course_app.service.CourseService;
import com.example.student_course_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    // ─── HOME ───────────────────────────────────────────
    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // ─── STUDENTS ───────────────────────────────────────
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getAllCourses());
        return "addStudent";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student,
                             RedirectAttributes redirectAttributes) {
        try {
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", "Student added successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("courses", courseService.getAllCourses());
        return "editStudent";
    }

    @PostMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id,
                              @ModelAttribute Student student,
                              RedirectAttributes redirectAttributes) {
        try {
            student.setId(id);
            studentService.updateStudent(student);
            redirectAttributes.addFlashAttribute("success", "Student updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/students";
    }

    // ─── COURSES ────────────────────────────────────────
    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute Course course,
                            RedirectAttributes redirectAttributes) {
        try {
            courseService.saveCourse(course);
            redirectAttributes.addFlashAttribute("success", "Course added successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "editCourse";
    }

    @PostMapping("/courses/edit/{id}")
    public String editCourse(@PathVariable Long id,
                             @ModelAttribute Course course,
                             RedirectAttributes redirectAttributes) {
        try {
            course.setId(id);
            courseService.updateCourse(course);
            redirectAttributes.addFlashAttribute("success", "Course updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/courses";
    }
}