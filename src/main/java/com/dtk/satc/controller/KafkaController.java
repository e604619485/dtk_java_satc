package com.dtk.satc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dtk.satc.common.ResponseObj;
import com.dtk.satc.config.SystemConfig;
import com.dtk.satc.kafka.KafkaSender;
import com.dtk.satc.param.BAMessageParam;
import com.dtk.satc.param.MessageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaSender kafkaSender;


    @GetMapping("/sendBAMessage")
    public ResponseObj<String> sendBAMessage(BAMessageParam baMessageParam) {
        String tid = baMessageParam.getTid();
        if("".equals(tid) || null == tid){
            return ResponseObj.paramError("tid不能为空");
        }
        String topic = "";
        if(tid.contains("dtk-cms")){
            topic = SystemConfig.CMS_TOPIC;
        }else if(tid.contains("dtk-web")){
            topic = SystemConfig.WEB_TOPIC;
        }
        if("".equals(topic) || null == topic){
            return ResponseObj.paramError("tid信息有误，无法匹配");
        }
        try{
            MessageParam param = new MessageParam();
            param.setTopic(topic);
            param.setData(JSONObject.toJSONString(baMessageParam));
            kafkaSender.send(param);
        }catch (Exception e){
            logger.error("send-message失败", e);
            return ResponseObj.fail("send-message失败");
        }
        return ResponseObj.ok();
    }

}
