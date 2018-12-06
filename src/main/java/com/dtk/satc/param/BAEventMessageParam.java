package com.dtk.satc.param;

import lombok.Data;

@Data
public class BAEventMessageParam {
     // 事件名称
     private String name = "";
     // 事件操作页面地址
     private String url = "";
     // 浏览器标识
     private String cid = "";
     // 登录用户id
     private String uid = "";
     // 项目id
     private String tid = "";
     // 位置
     private String site = "";
     // 真实ip地址
     private String ip = "";
     // 收集时间
     private String sendtime = "";
}
