package com.example.hrapplication.dept.dto;

import com.example.hrapplication.dept.domain.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Long deptNo;
    private String deptName;
    private Date regDate;

    public DepartmentEntity toEntity(){
        return new DepartmentEntity(this.deptName, this.regDate);
    }
}
