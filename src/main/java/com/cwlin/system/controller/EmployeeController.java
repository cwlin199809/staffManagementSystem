package com.cwlin.system.controller;

import com.cwlin.system.mapper.DepartmentMapper;
import com.cwlin.system.mapper.EmployeeMapper;
import com.cwlin.system.pojo.Department;
import com.cwlin.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/list")
    public String list(Model model){
        Collection<Employee> employees = employeeMapper.getEmployees();
        model.addAttribute("employees",employees);
        return "emp/list";
    }

    //跳转到emp/add.html
    @GetMapping("/add")
    public String add(Model model){
        //查询所有部门信息
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //获取form提交的数据，新增员工
    @PostMapping("/add")
    public String added(Employee employee){
        //新增员工操作
        employeeMapper.addEmployee(employee);
        return "redirect:/employees/list";
    }

    //跳转到emp/edit.html
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        //查询员工的原有信息
        Employee employee = employeeMapper.getEmployeeById(id);
        model.addAttribute("employee",employee);
        //查询所有部门信息
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/edit";
    }

    //获取form提交的数据，修改员工
    @PostMapping("/edit/{id}")
    public String edited(Employee employee){
        //修改员工操作，即等同于添加员工操作
        employeeMapper.addEmployee(employee);
        return "redirect:/employees/list";
    }

    //删除员工
    @GetMapping("/remove/{id}")
    public String removed(@PathVariable("id") Integer id){
        //修改员工操作，即等同于添加员工操作
        employeeMapper.removeEmployeeById(id);
        return "redirect:/employees/list";
    }
}
