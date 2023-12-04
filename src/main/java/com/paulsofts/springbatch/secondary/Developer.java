package com.paulsofts.springbatch.secondary;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="employee_info")
@Data
public class Developer {
	
	private int emp_id;
	private String emp_name;
	private double emp_salary;
	private String emp_role;

}
