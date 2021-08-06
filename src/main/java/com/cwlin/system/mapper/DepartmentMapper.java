package com.cwlin.system.mapper;

import com.cwlin.system.pojo.Department;
import com.cwlin.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentMapper {
    //模糊数据库中的数据
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer, Department>(); //创建一个部门表
        departments.put(101,new Department(101,"计财处"));
        departments.put(102,new Department(102,"科技处"));
        departments.put(103,new Department(103,"设备处"));
        departments.put(104,new Department(104,"教务处"));
        departments.put(105,new Department(105,"党政办"));
    }
    @Autowired
    EmployeeMapper employeeMapper;

    //获得所有部门信息
    public Collection<Department> getDepartments(){
        updateEmployeeNumber();
        return departments.values();
    }

    //通过id得到部门
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }

    //更新所有部门的员工人数
    public void updateEmployeeNumber(){
        int number;
        for (Department department : departments.values()) {
            number = 0;
            for (Employee employee : employeeMapper.getEmployees()) {
                if (department.getId().equals(employee.getDepartment().getId())){
                    number++;
                }
            }
            department.setEmployeeNumber(number);
        }
    }
}
