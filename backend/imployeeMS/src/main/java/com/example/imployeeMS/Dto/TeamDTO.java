package com.example.imployeeMS.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamDTO {
    private Long teamId;
    private String teamName;
    private int departmentId;
    private String description;

}
