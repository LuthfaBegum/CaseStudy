package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		// Load employees from CSV
		List<Employee> employees = loadEmployees("src/main/resources/employees.csv");

		// Analyze salary issues
		Map<String, String> salaryIssues = SalaryAnalyzer.findSalaryIssues(employees);
		if (salaryIssues.isEmpty()) {
			System.out.println("No Salary issues.");
		} else {
			System.out.println("Salary Issues:");
			salaryIssues.forEach((name, issue) -> System.out.println(name + ": " + issue));
		}

		// Analyze reporting line issues
		Map<String, Integer> reportingIssues = ReportingLineAnalyzer.findReportingIssues(employees);
		System.out.println();
		if (reportingIssues.isEmpty()) {
			System.out.println("No reporting line issues.");
		} else {
			System.out.println("Reporting Line Issues:");
			reportingIssues.forEach((name, extraLevels) -> System.out.println(name + ": " + extraLevels + " extra levels"));
		}
	}

	private static List<Employee> loadEmployees(String filePath) throws IOException {
		List<Employee> employees = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			// Skip the header line
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String id = parts[0];
				String firstName = parts[1];
				String lastName = parts[2];
				double salary = Double.parseDouble(parts[3]);
				String managerId = parts.length > 4 ? parts[4] : null;

				String name = firstName + " " + lastName;
				employees.add(new Employee(id, name, salary, managerId));
			}
		}
		return employees;
	}

}
