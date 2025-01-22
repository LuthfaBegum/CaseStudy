package com.example.demo;

public class Employee {
    private final String id;
    private final String name;
    private final double salary;
    private final String managerId;

    public Employee(String id, String name, double salary, String managerId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.managerId = managerId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getManagerId() { return managerId; }
}
