package com.cwlin.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//部门
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String departmentName;
    private Integer EmployeeNumber;

    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
        this.EmployeeNumber = 0;
    }
}
