package com.example.hrapplication.dept.controller;

import com.example.hrapplication.dept.domain.DepartmentEntityRepository;
import com.example.hrapplication.dept.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DepartmentEntityRepository departmentEntityRepository;

    @GetMapping("/set/{deptName}")
    public Department setDept(@PathVariable String deptName){
        Department department = new Department();
        department.setDeptName(deptName);
        department.setRegDate(new Date());

        return departmentEntityRepository.save(department.toEntity()).toDto();
    }
}
