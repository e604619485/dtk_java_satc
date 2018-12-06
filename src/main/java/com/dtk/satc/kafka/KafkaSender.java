package com.dtk.satc.kafka;

import com.alibaba.fastjson.JSONObject;
import com.dtk.satc.domain.Message;
import com.dtk.satc.param.MessageParam;
import com.dtk.satc.utils.CreatUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;

@Component
public class KafkaSender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(MessageParam param) throws Exception{
        Message message = new Message();
        message.setId(CreatUUID.getUUID());
        message.setData(param.getData());
        message.setSendTime(new Date());
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(param.getTopic(), JSONObject.toJSONString(message));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                logger.info("Produce: The message was sent successfully:");
                logger.info("Produce: _+_+_+_+_+_+_+ result: " + stringStringSendResult.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Produce: The message failed to be sent:" + throwable.getMessage());
            }
        });
    }
}
