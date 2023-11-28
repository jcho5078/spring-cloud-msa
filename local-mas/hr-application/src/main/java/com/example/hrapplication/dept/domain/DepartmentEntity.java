package com.example.hrapplication.dept.domain;

import com.example.hrapplication.dept.dto.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {

    public DepartmentEntity(String deptName, Date regDate){
        this.deptName = deptName;
        this.regDate = regDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPT_NO", nullable = false)
    private Long deptNo;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "REG_DATE")
    private Date regDate;

    public Department toDto(){
        return new Department(this.deptNo, this.deptName, this.regDate);
    }
}