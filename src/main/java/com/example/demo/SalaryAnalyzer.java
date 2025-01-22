package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class SalaryAnalyzer {
    public static Map<String, String> findSalaryIssues(List<Employee> employees) {
        Map<String, List<Employee>> managerToSubordinates = employees.stream()
            .filter(e -> e.getManagerId() != null)
            .collect(Collectors.groupingBy(Employee::getManagerId));

        Map<String, String> salaryIssues = new HashMap<>();

        for (Employee manager : employees) {
            List<Employee> subordinates = managerToSubordinates.get(manager.getId());
            if (subordinates != null && !subordinates.isEmpty()) {
                double avgSubordinateSalary = subordinates.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);

                double minSalary = avgSubordinateSalary * 1.2;
                double maxSalary = avgSubordinateSalary * 1.5;

                if (manager.getSalary() < minSalary) {
                    salaryIssues.put(manager.getName(),
                            "Underpaid by " + (minSalary - manager.getSalary()));
                } else if (manager.getSalary() > maxSalary) {
                    salaryIssues.put(manager.getName(),
                            "Overpaid by " + (manager.getSalary() - maxSalary));
                }
            }
        }

        return salaryIssues;
    }
}
