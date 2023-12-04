package com.paulsofts.springbatch.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends MongoRepository<Developer, Integer> {

}
