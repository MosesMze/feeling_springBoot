package com.feeling.tsukkomi.mapper;

import com.feeling.tsukkomi.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
