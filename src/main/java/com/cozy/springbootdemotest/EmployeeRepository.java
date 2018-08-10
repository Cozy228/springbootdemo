package com.cozy.springbootdemotest;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long> {

    Mono<Employee> findByEid(int eid);

    Mono<Employee> findByName(String name);

    Flux<Employee> findByDepartment(String department);

    Flux<Employee> findAll();

    Mono<Long> deleteByName(String name);
}
