package com.secondeal.model.org;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goods {
    private Long id;

    private String uuid;

    private String title;

    private String tags;

    private String images;

    private Double price;

    private Double discounted;

    private String belongUuid;

    private String belongName;

    private String buyUuid;

    private String createTime;

    private String updateTime;

    private String deleteTime;

    private String mobile;

    private Integer qq;

    private Integer isDelete;

    private String intro;

    private Integer state;

    private Integer flag;

}