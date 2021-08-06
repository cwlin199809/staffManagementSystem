package com.cwlin.system.controller;

import com.cwlin.system.mapper.DepartmentMapper;
import com.cwlin.system.mapper.EmployeeMapper;
import com.cwlin.system.pojo.Department;
import com.cwlin.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/list")
    public String list(Model model){
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "dep/list";
    }
}
