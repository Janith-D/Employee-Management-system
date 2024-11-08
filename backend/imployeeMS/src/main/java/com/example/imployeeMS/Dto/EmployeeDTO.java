package com.example.imployeeMS.Dto;

import com.example.imployeeMS.Entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private int empId;
    private String empName;
    private String empAddress;
    private  String empNumber;
    private Long departmentId;
    private Long teamId;

    public void setDepartment(Department department) {

    }

    public Department getDepartment() {

        return null;
    }
}
