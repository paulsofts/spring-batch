package com.paulsofts.springbatch.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paulsofts.springbatch.secondary.Developer;
import com.paulsofts.springbatch.secondary.DeveloperRepository;

@Component
public class Writer implements ItemWriter<Developer>{
	
	@Autowired
	private DeveloperRepository developerRepository;

	@Override
	public void write(Chunk<? extends Developer> chunk) throws Exception {
		
		for (Developer dev : chunk) {
			System.out.println("Writer    : Writing data    : " + dev.getEmp_id()+" : "+dev.getEmp_name()+" : "+dev.getEmp_salary()
			+" : "+dev.getEmp_role());
			developerRepository.save(dev);
		}
		
	}

}
