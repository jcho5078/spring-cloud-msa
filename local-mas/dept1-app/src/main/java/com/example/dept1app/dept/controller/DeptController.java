package com.example.dept1app.dept.controller;

import com.example.dept1app.dept.HrProxy;
import com.example.dept1app.dept.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DeptController {

    @Autowired
    private HrProxy hrProxy;

    @GetMapping("/set/{deptName}")
    public Department setDept(@PathVariable String deptName){
        Department department = new Department();

        Map<String, String> uriValues = new HashMap<>();
        uriValues.put("deptName", deptName);

        ResponseEntity<Department> response = new RestTemplate()
                .getForEntity("http://localhost:8100/dept/set/{deptName}", Department.class, uriValues);

        department = response.getBody();

        return department;
    }

    @GetMapping("/set-Feign/{deptName}")
    public Department setDeptFeign(@PathVariable String deptName){
        Department department = hrProxy.setDept(deptName);

        return department;
    }
}
