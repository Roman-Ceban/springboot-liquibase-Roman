package com.codingworld.liquibasedemo;

import com.codingworld.liquibasedemo.Dao.EmployeesDao;
import com.codingworld.liquibasedemo.repository.entity.Employees;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiquibaseDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseDemoApplication.class, args);
	}
	EmployeesDao employeesDao;
	public void run(String...args) throws Exception {
		Employees person = new Employees(1, "Leanne Graham","Bret", "Romaguera-Crona", "Gwenborough",
				"Sincere@april.biz");
		employeesDao.insert(person);
		System.out.println("<<<<<<<<<<<<"+ employeesDao.findAll());
	}
}
