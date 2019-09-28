package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bid;

    @Column(nullable = false)
    private long lid;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Long getUniqueId;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private Date date;

    private Integer isActive;

    private String  lName;

}
