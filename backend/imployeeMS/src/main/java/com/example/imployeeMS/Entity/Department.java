package com.example.imployeeMS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;
    private String departmentName;
    private String departmentDescription;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;


    public Long getId() {
        return departmentId;
    }

    public long setId(long departmentId) {
        return departmentId;
    }
}
