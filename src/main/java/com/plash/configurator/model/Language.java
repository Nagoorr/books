package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Language {

    @Id
    @GeneratedValue
    private long lid;

    @Column(nullable = false)
    private long bid;

    @Column(nullable = false)
    private String languageName;

    private Integer isActive;

}
