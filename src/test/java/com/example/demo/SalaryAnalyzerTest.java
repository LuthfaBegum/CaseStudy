package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalaryAnalyzerTest {

    @Test
    void testFindSalaryIssues_UnderpaidManagers() {
        List<Employee> employees = List.of(
            new Employee("123", "Joe Doe", 60000, null),
            new Employee("124", "Martin Chekov", 40000, "123"),
            new Employee("125", "Bob Ronstad", 52000, "123"),
            new Employee("300", "Alice Hasacat", 47000, "124")
        );

        Map<String, String> salaryIssues = SalaryAnalyzer.findSalaryIssues(employees);

        assertFalse(salaryIssues.isEmpty(), "Salary issues should be found.");
        assertTrue(salaryIssues.containsKey("Martin Chekov"));
    }
}
