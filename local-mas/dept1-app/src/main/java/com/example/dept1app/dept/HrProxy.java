package com.example.dept1app.dept;

import com.example.dept1app.dept.dto.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hrService" /*,url = "localhost:8100"*/)
public interface HrProxy {

    @GetMapping("/hrService/dept/set/{deptName}")
    public Department setDept(@PathVariable String deptName);
}
