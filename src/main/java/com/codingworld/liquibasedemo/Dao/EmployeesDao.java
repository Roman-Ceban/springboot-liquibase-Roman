package com.codingworld.liquibasedemo.Dao;

import com.codingworld.liquibasedemo.repository.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeesDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employees> findAll() {
        return jdbcTemplate.query("select * from jpa.person",
                new BeanPropertyRowMapper<>(Employees.class));
    }

    public void insert(Employees employees) {
        jdbcTemplate.update("insert into jpa.person " +
                        "(id, firstname, lastname, company, address, geo, phone, email  ) " +
                        "values (?,?,?,?)",
                employees.getId(), employees.getFirstname(), employees.getLastname(),
                employees.getCompany(), employees.getAddress(),
                employees.getPhone(), employees.getEmail());


    }
}