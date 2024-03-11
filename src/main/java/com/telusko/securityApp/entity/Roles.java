package com.telusko.securityApp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String role;

}
