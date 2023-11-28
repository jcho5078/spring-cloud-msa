package com.example.dept1app.dept;

import com.example.dept1app.dept.dto.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hr-application" /*,url = "localhost:8100"*/)
public interface HrProxy {

    @GetMapping("/dept/set/{deptName}")
    public Department setDept(@PathVariable String deptName);
}
