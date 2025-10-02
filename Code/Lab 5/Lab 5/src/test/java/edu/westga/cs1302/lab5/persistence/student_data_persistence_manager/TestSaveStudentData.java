package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

class TestSaveStudentData {

	@Test
	void testSaveNullStudentsThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        StudentDataPersistenceManager.saveStudentData(null);
	    });
	}
	
	@Test
	void testNoStudents() throws IllegalArgumentException, IOException {
		StudentDataPersistenceManager.saveStudentData(new Student[0]);
		
		File inputFile = new File(StudentDataPersistenceManager.FILE_LOCATION);
		try (Scanner reader = new Scanner(inputFile)) {
			assertFalse(reader.hasNextLine(), "checking if file is empty (should have no lines)");
		}
	}
	
	@Test
	void testSaveOneStudent() throws IOException {
		Student[] students = {new Student("Tobi", 99)};
		
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.FILE_LOCATION);
		try (Scanner reader = new Scanner(inputFile)) {
			assertTrue(reader.hasNextLine(), "expecting one line in the file");
			assertEquals("Tobi,99", reader.nextLine());
			assertFalse(reader.hasNextLine(), "checking if file only has one line");
		}
	}
	
	@Test
	void testSaveMultipleStudents() throws IOException {
		Student[] students = {
			new Student("Tobi", 99),
			new Student("Denise", 89),
			new Student("Tim", 76)
		};
		
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.FILE_LOCATION);
		try (Scanner reader = new Scanner(inputFile)) {
			assertTrue(reader.hasNextLine(), "expecting three line in the file");
			assertEquals("Tobi,99", reader.nextLine());
			
			assertTrue(reader.hasNextLine());
			assertEquals("Denise,89", reader.nextLine());
			
			assertTrue(reader.hasNextLine());
			assertEquals("Tim,76", reader.nextLine());
			assertFalse(reader.hasNextLine(), "checking if file only has three lines");
		}
	}
}
