package com.paulsofts.springbatch.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.paulsofts.springbatch.primary.Employee;

@Component
public class Reader extends JdbcCursorItemReader<Employee> implements ItemReader<Employee> {
	
	public Reader(@Autowired DataSource primaryDataSource) {
		setDataSource(primaryDataSource);
		setSql("SELECT * FROM employee_db.employee_info");
		setFetchSize(100);
		setRowMapper(new EmployeeRowMapper());
	}
	
	public class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			//create Employee object
			Employee emp  = new Employee();
			//reading data from ResultSet and write to Employee object
			emp.setEmp_id(rs.getInt("emp_id"));
			emp.setEmp_name(rs.getString("emp_name"));
			emp.setEmp_salary(rs.getDouble("emp_salary"));
			emp.setEmp_role(rs.getString("emp_role"));
			
			return emp;
		}
	}
}
