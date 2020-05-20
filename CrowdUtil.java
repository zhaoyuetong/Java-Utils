package com.zyt.crowd.util;

import com.zyt.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * zyt
 */
public class CrowdUtil {
    /**
     * 对明文字符串加密
     * @param source
     * @return
     */
    public static String md5(String source){
        //1.判断source是否有效
        if (source==null||source.length()==0){
            //2.若无效字符串，抛异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        //3.获取MessageDiges对象
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            //4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            //5.执行加密
            byte[] output = messageDigest.digest(input);
            //6.创建BigInteget对象
            int signum=1;
            BigInteger bigInteger = new BigInteger(signum, output);
            //7.按照16进制将BigInteger的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 判断当前请求是否为Ajax请求
     * @param request
     * @return true:ajax
     * false:不是ajax
     */
    public static Boolean judjeRequestType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        return (accept!=null&&accept.contains("application/json")||
                (xRequestedWith!=null&&xRequestedWith.equals("XMLHttpRequest"))
        );
    }
}
