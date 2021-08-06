package com.cwlin.system.mapper;

import com.cwlin.system.pojo.Department;
import com.cwlin.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeMapper {
    //模糊数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentMapper departmentMapper;
    static {
        employees = new HashMap<Integer, Employee>(); //创建一个部门表
        employees.put(1001,new Employee(1001,"AA","123456@qq.com",1,new Department(101,"计财处")));
        employees.put(1002,new Employee(1002,"BB","234567@qq.com",0,new Department(102,"科技处")));
        employees.put(1003,new Employee(1003,"CC","345678@qq.com",0,new Department(103,"设备处")));
        employees.put(1004,new Employee(1004,"DD","456789@qq.com",1,new Department(104,"教务处")));
        employees.put(1005,new Employee(1005,"EE","567890@qq.com",0,new Department(105,"党政办")));
    }

    //主键自增
    private static Integer initId = 1006;

    //增加一个员工
    public void addEmployee(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentMapper.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
        departmentMapper.updateEmployeeNumber();
    }

    //查询全部员工信息
    public Collection<Employee> getEmployees(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void removeEmployeeById(Integer id){
        employees.remove(id);
        departmentMapper.updateEmployeeNumber();
    }
}
