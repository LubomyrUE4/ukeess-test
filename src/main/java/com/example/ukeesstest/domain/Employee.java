package com.example.ukeesstest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long empID;
    private String empName;
    private Boolean empActive;
    private Long emp_dpID;
}
