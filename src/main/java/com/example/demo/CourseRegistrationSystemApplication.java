package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Course;
import com.example.demo.model.CourseRepository;
import com.example.demo.model.Section;
import com.example.demo.model.Student;
import com.example.demo.model.StudentRepository;

@SpringBootApplication
public class CourseRegistrationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseRegistrationSystemApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CourseRepository courseRepository, 
			StudentRepository studentRepository) {
		return args -> {
//			courseRepository.save(new Course("CSIS2175", "ADVANCED INTEGRATED SOFTWARE DEVELOPMENT"));
//			courseRepository.save(new Course("CSIS3275", "SOFTWARE ENGINEERING"));
//			courseRepository.save(new Course("CSIS1190", "EXCEL FOR BUSINESS"));
//			courseRepository.findAll().forEach(System.out::println);

			Course[] courses = { 
					new Course("CSIS2175", "Advanced integrated software development"),
					new Course("CSIS3275", "Software engineering"), 
					new Course("CSIS1190", "Excel for business") };

			courses[0].addSection(new Section(1));
			courses[0].addSection(new Section(2));
			courses[2].addSection(new Section(1));
						
			Student[] students = { 
					new Student("03123456", "Simon Li", "123456"),
					new Student("03456789", "Ivan Wong", "123456") };

			students[0].addCourse(courses[0]);
			students[0].addCourse(courses[1]);

			for (int i = 0; i < courses.length; i++) {
				courseRepository.save(courses[i]);
			}
			for (int i = 0; i < students.length; i++) {
				studentRepository.save(students[i]);
			}

			courseRepository.findAll().forEach(System.out::println);
			studentRepository.findAll().forEach(System.out::println);

		};

	}

}
