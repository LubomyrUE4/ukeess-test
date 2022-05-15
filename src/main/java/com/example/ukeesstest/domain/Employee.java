package com.example.ukeesstest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tblEmployees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private String empName;
    private boolean empActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_dpId", insertable = false, updatable = false)
    @JsonIgnore
    private Department emp_dp;

    @Column(name = "emp_dpId")
    private Long emp_dpId;
}
