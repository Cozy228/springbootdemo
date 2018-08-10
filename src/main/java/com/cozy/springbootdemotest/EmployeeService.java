package com.cozy.springbootdemotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 保存或更新。 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错， 这时找到以保存的user记录用传入的user更新它。
     */
    public Mono<Employee> save(Employee employee) {
        return employeeRepository.save(employee)
            .onErrorResume(e ->     // 1
                employeeRepository.findByName(employee.getName())   // 2
                    .flatMap(originalUser -> {      // 4
                        employee.setId(originalUser.getId());
                        return employeeRepository.save(employee);   // 3
                    }));
    }

    public Mono<Long> deleteByUsername(String name) {
        return employeeRepository.deleteByName(name);
    }

    public Mono<Employee> findByUsername(String name) {
        return employeeRepository.findByName(name);
    }

    public Flux<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }
}

