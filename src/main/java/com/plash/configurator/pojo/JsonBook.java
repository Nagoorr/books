package com.plash.configurator.pojo;


import lombok.Data;

import java.util.*;


@Data
public class JsonBook {

    private long bid;

    private long lid;

    private String title;

    private String author;

    private String version;

    private Date date;

    private Integer isActive;

    private String lName;

}
