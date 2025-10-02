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
	void testNoStudents() throws IllegalArgumentException, IOException {
		StudentDataPersistenceManager.saveStudentData(new Student[0]);
		
		File inputFile = new File("data.txt");
		try(Scanner reader = new Scanner(inputFile)) {
			assertFalse(reader.hasNextLine(), "checking if file is empty (should have no lines)");
		}
	}
	
	@Test
	void testSaveOneStudent() throws IOException {
		
	}
}
