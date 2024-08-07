package org.example.edu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity(name = "user")
    @Table(name = "user")
    public class EmployeeEntity {

        @Id
        private String id;
        private String name;
        private String email;
        private String password;
        private String role;
        private String address;


    }



