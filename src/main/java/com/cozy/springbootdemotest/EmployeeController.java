package com.cozy.springbootdemotest;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    public Mono<Employee> save(Employee user) {
        return this.employeeService.save(user);
    }

    @DeleteMapping("/{name}")
    public Mono<Long> deleteByUsername(@PathVariable String name) {
        return this.employeeService.deleteByUsername(name);
    }

    @GetMapping("/{name}")
    public Mono<Employee> findByUsername(@PathVariable String name) {
        return this.employeeService.findByUsername(name);
    }

    @GetMapping("/department/{department}")
    public Flux<Employee> findByDepartment(@PathVariable String department) {
        return this.employeeService.findByDepartment(department);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Employee> findAll() {
        return this.employeeService.findAll().delayElements(Duration.ofSeconds(1));
    }


}

