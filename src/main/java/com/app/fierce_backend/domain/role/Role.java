package com.app.fierce_backend.domain.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_role")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Getter @Setter
    private String name;
}
