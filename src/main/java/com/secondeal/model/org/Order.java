package com.secondeal.model.org;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Order {
    private Long id;

    private String uuid;

    private String goodTitle;

    private String goodUuid;

    private Double goodDiscounted;

    private String goodIntro;

    private String buyUuid;

    private Date createTime;

    private Date updateTime;

    private Integer state;
}