package com.paulsofts.springbatch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.paulsofts.springbatch.primary.Employee;
import com.paulsofts.springbatch.secondary.Developer;

@Component
public class Processor implements ItemProcessor<Employee, Developer> {

	@Override
	public Developer process(Employee item) throws Exception {
		
		Developer dev = new Developer();
		
		dev.setEmp_id(item.getEmp_id());
		dev.setEmp_name(item.getEmp_name());
		dev.setEmp_salary(item.getEmp_salary());
		dev.setEmp_role(item.getEmp_role());
		
		return dev;
	}

}
