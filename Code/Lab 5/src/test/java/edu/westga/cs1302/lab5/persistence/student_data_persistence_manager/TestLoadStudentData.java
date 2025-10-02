package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;


import java.io.FileWriter;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

class TestLoadStudentData {

	@Test
	void testNoGrades() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
			writer.write("Tobi");
		}

	    assertThrows(IOException.class, () -> {
	        StudentDataPersistenceManager.loadStudentData();
	    });
	}
	
	@Test
	void testLoadNonIntGrade() throws IOException {
	    try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
	        writer.write("Tobi\n");
	        writer.write("seven\n");
	    }

	    assertThrows(IOException.class, () -> {
	        StudentDataPersistenceManager.loadStudentData();
	    });
	}
	
	@Test
	void testLoadInvalidStudent() throws IOException {
	    try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
	        writer.write("");
	        writer.write("99");
	    }

	    assertThrows(IOException.class, () -> {
	        StudentDataPersistenceManager.loadStudentData();
	    });
	}

	@Test
	void testNoStudents() throws IOException {
	    try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {}

	    Student[] students = StudentDataPersistenceManager.loadStudentData();
	    assertEquals(0, students.length);
	}
	
	@Test
	void testLoadOneStudent() throws IOException {
	    try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
	        writer.write("Tobi\n");
	        writer.write("90\n");
	    }

	    Student[] students = StudentDataPersistenceManager.loadStudentData();
	    assertEquals(1, students.length);
	    assertEquals("Tobi", students[0].getName());
	    assertEquals(90, students[0].getGrade());
	}
	
	@Test
	void testLoadMultipleStudents() throws IOException {
	    try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
	        writer.write("Tobi\n");
	        writer.write("99\n");
	        writer.write("Denise\n");
	        writer.write("85\n");
	        writer.write("Tim\n");
	        writer.write("78\n");
	    }

	    Student[] students = StudentDataPersistenceManager.loadStudentData();
	    assertEquals(3, students.length);

	    assertEquals("Tobi", students[0].getName());
	    assertEquals(99, students[0].getGrade());

	    assertEquals("Denise", students[1].getName());
	    assertEquals(85, students[1].getGrade());

	    assertEquals("Tim", students[2].getName());
	    assertEquals(78, students[2].getGrade());
	}
	
	
}
