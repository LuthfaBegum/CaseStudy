package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testFindSalaryIssues() {
		List<Employee> employees = List.of(
				new Employee("123", "Joe Doe", 60000, null),
				new Employee("124", "Martin Chekov", 45000, "123"),
				new Employee("125", "Bob Ronstad", 47000, "123"),
				new Employee("300", "Alice Hasacat", 50000, "124"),
				new Employee("305", "Brett Hardleaf", 34000, "300")
		);

		Map<String, String> salaryIssues = SalaryAnalyzer.findSalaryIssues(employees);

		assertNotNull(salaryIssues);
		assertTrue(salaryIssues.containsKey("Martin Chekov"));
	}

	@Test
	void testFindReportingIssues() {
		List<Employee> employees = List.of(
				new Employee("123", "Joe Doe", 60000, null),
				new Employee("124", "Martin Chekov", 45000, "123"),
				new Employee("125", "Bob Ronstad", 47000, "123"),
				new Employee("300", "Alice Hasacat", 50000, "124"),
				new Employee("305", "Brett Hardleaf", 34000, "300"),
				new Employee("308", "Josh Kelly", 35000, "305"),
				new Employee("310", "June Webster", 24000, "308")
		);

		Map<String, Integer> reportingIssues = ReportingLineAnalyzer.findReportingIssues(employees);

		assertNotNull(reportingIssues);
		assertTrue(reportingIssues.containsKey("June Webster"));
		assertEquals(1, reportingIssues.get("June Webster"));
	}
}
