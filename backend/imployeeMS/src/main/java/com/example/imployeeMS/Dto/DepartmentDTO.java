package com.example.imployeeMS.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String departmentDescription;
}
