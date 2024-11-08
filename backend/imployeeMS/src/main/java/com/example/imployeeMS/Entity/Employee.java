package com.example.imployeeMS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int empId;
   private String empName;
   private String empAddress;
   private  String empNumber;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "departmentId")
   private Department department;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "teamId")
   private Team team;


}
