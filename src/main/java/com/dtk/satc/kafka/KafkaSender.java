package com.dtk.satc.kafka;

import com.alibaba.fastjson.JSONObject;
import com.dtk.satc.domain.Message;
import com.dtk.satc.param.MessageParam;
import com.dtk.satc.utils.CreatUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(MessageParam param) throws Exception{
        Message message = new Message();
        message.setId(CreatUUID.getUUID());
        message.setData(param.getData());
        message.setSendTime(new Date());
        kafkaTemplate.send(param.getTopic(), JSONObject.toJSONString(message));
    }
}
