package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReportingLineAnalyzerTest {

    @Test
    void testFindReportingIssues_NoIssues() {
        List<Employee> employees = List.of(
            new Employee("123", "Joe Doe", 60000, null),  // CEO
            new Employee("124", "Martin Chekov", 45000, "123"),
            new Employee("125", "Bob Ronstad", 47000, "123"),
            new Employee("300", "Alice Hasacat", 50000, "124"),
            new Employee("305", "Brett Hardleaf", 34000, "300")
        );

        Map<String, Integer> reportingIssues = ReportingLineAnalyzer.findReportingIssues(employees);
        assertTrue(reportingIssues.isEmpty());
    }

    @Test
    void testFindReportingIssues_WithIssues() {
        List<Employee> employees = List.of(
            new Employee("123", "Joe Doe", 60000, null),
            new Employee("124", "Martin Chekov", 45000, "123"),
            new Employee("125", "Bob Ronstad", 47000, "123"),
            new Employee("300", "Alice Hasacat", 50000, "125"),
            new Employee("305", "Brett Hardleaf", 34000, "300"),
            new Employee("400", "Chris Stone", 33000, "305"),
            new Employee("500", "Jane Smith", 29000, "400")
        );

        Map<String, Integer> reportingIssues = ReportingLineAnalyzer.findReportingIssues(employees);

        assertFalse(reportingIssues.isEmpty());
        assertEquals(1, reportingIssues.size());
    }
}
