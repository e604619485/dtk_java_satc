package com.dtk.satc.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

/**
 * base64 编码、解码util
 *
 * @author lifq
 * @date 2015-3-4 上午09:23:33
 */
public class Base64Utils {
    /**
     * 加密
     * @param decodeStr
     * @return
     */
    public static String ecodeBase64(String decodeStr){
//        Assert.assertNotNull("解密参数不能为空!", decodeStr);
        byte[] bytes = Base64.encodeBase64(decodeStr.getBytes(),false,true);
        return StringUtils.toEncodedString(bytes, Charset.forName("utf-8"));
    }

    /**
     * 解密
     * @param encodeStr
     * @return
     */
    public static String decodeBase64(String encodeStr){
//        Assert.assertNotNull("解密参数不能为空!", encodeStr);
        byte[] b = Base64.decodeBase64(encodeStr);
        return StringUtils.toEncodedString(b, Charset.forName("utf-8"));
    }
}
