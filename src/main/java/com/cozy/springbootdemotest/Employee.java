package com.cozy.springbootdemotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {

    @Id
    private String id;
    private String name;
    private Integer eid;
    private String department;
    private String manager;
    private String title;

}
