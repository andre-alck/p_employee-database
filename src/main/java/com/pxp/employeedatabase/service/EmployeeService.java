package com.pxp.employeedatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxp.employeedatabase.entity.EmployeeEntity;
import com.pxp.employeedatabase.model.Employee;
import com.pxp.employeedatabase.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployess() {
        try {
            List<EmployeeEntity> employees = employeeRepository.findAll();
            List<Employee> customEmployees = new ArrayList<>();
            employees.stream().forEach(e -> {
                Employee employee = new Employee();
                BeanUtils.copyProperties(e, employee);
                customEmployees.add(employee);
            });
            return customEmployees;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public String addEmployee(EmployeeEntity employee) {
        try {
            if (!employeeRepository.existsByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())) {
                employeeRepository.save(employee);
                return "Funcionário salvo com sucesso.";
            } else {
                return "Este funcionário já existe no banco de dados.";
            }
        } catch (Exception exception) {
            throw exception;
        }
    }

    public String removeEmployee(EmployeeEntity employee) {
        try {
            if (employeeRepository.existsByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())) {
                employeeRepository.delete(employee);
                return "Funcionário removido com sucesso.";
            } else {
                return "Este funcionário não existe no banco de dados.";
            }
        } catch (Exception exception) {
            throw exception;
        }
    }

    public String updateEmployee(EmployeeEntity employee) {
        try {
            if (employeeRepository.existsById(employee.getId())) {
                employeeRepository.save(employee);
                return "Funcionário atualizado com sucesso.";
            } else {
                return "Este funcionário não existe no banco de dados.";
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
}
