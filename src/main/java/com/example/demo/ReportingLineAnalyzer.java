package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class ReportingLineAnalyzer {
    public static Map<String, Integer> findReportingIssues(List<Employee> employees) {
        Map<String, String> employeeToManager = employees.stream()
            .filter(e -> e.getManagerId() != null)
            .collect(Collectors.toMap(Employee::getId, Employee::getManagerId));

        Map<String, Integer> reportingIssues = new HashMap<>();

        for (Employee employee : employees) {
            int levels = countLevels(employee.getId(), employeeToManager);
            if (levels > 4) {
                reportingIssues.put(employee.getName(), levels - 4);
            }
        }

        return reportingIssues;
    }

    private static int countLevels(String employeeId, Map<String, String> employeeToManager) {
        int levels = 0;
        String current = employeeId;

        while (employeeToManager.containsKey(current)) {
            levels++;
            current = employeeToManager.get(current);
        }

        return levels;
    }
}
