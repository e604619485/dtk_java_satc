package com.dtk.satc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dtk.satc.common.ResponseObj;
import com.dtk.satc.config.SystemConfig;
import com.dtk.satc.kafka.KafkaSender;
import com.dtk.satc.param.BAEventMessageParam;
import com.dtk.satc.param.BAMessageParam;
import com.dtk.satc.param.MessageParam;
import com.dtk.satc.utils.Base64Utils;
import com.dtk.satc.utils.IpAddrUtil;
import com.dtk.satc.utils.SymmetricEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class KafkaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaSender kafkaSender;


    @GetMapping("/sendBAMessage")
    @Async("AsyncTaskExecutePool2")
    public ResponseObj<String> sendBAMessage(HttpServletRequest request, BAMessageParam baMessageParam) {
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
            String ip = IpAddrUtil.getIpAddr(request);
            if(!"".equals(ip) && null != ip){
                if(ip.contains(",")){
                    String[] ips = ip.split(",");
                    if(ips.length > 0){
                        baMessageParam.setIp(ips[0]);
                    }else{
                        baMessageParam.setIp(ip);
                    }
                }else{
                    baMessageParam.setIp(ip);
                }
            }
            String uid = baMessageParam.getUid();
            if(null != uid && !"".equals(uid)){
                uid = Base64Utils.decodeBase64(uid);
                baMessageParam.setUid(uid);
            }
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

    @GetMapping("/sendEvent")
    @Async("AsyncTaskExecutePool2")
    public ResponseObj<String> sendEvent(HttpServletRequest request, BAEventMessageParam param) {
        String tid = param.getTid();
        if("".equals(tid) || null == tid){
            return ResponseObj.paramError("tid不能为空");
        }
        String topic = "";
        if(tid.contains("dtk-cms")){
            topic = SystemConfig.CMS_EVENT_TOPIC;
        }else if(tid.contains("dtk-web")){
            topic = SystemConfig.WEB_EVENT_TOPIC;
        }
        if("".equals(topic) || null == topic){
            return ResponseObj.paramError("tid信息有误，无法匹配");
        }
        try{
            String ip = IpAddrUtil.getIpAddr(request);
            if(!"".equals(ip) && null != ip){
                if(ip.contains(",")){
                    String[] ips = ip.split(",");
                    if(ips.length > 0){
                        param.setIp(ips[0]);
                    }else{
                        param.setIp(ip);
                    }
                }else{
                    param.setIp(ip);
                }
            }
            String uid = param.getUid();
            if(null != uid && !"".equals(uid)){
                uid = Base64Utils.decodeBase64(uid);
                param.setUid(uid);
            }
            MessageParam mParam = new MessageParam();
            mParam.setTopic(topic);
            mParam.setData(JSONObject.toJSONString(param));
            kafkaSender.send(mParam);
        }catch (Exception e){
            logger.error("send-message失败", e);
            return ResponseObj.fail("send-message失败");
        }
        return ResponseObj.ok();
    }

    /*public static void main(String[] args) {
     *//*String temp = SymmetricEncoder.aesEncrypt("dtkcmsuser123456", "100");
        temp = Base64Utils.ecodeBase64(temp);
        System.out.println(temp);*//*


        String uid = Base64Utils.decodeBase64("MTAw");
        //uid = SymmetricEncoder.aesDecrypt("dtkcmsuser123456", uid);
        System.out.println(uid);
    }*/
}
