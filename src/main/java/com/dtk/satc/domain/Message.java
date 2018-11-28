package com.dtk.satc.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message<T> {
    private String id;    //id

    private T data; //消息

    private Date sendTime;  //时间戳
}
