package com.houseWork.controller.pay;

import com.houseWork.utils.XmlToMapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api("支付回调模块")
@RequestMapping("/pay/callBack")
@Slf4j
public class PayCallBackController {
    /**
     * 支付回调
     * @throws IOException
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        Map<String, Object> resultMap = XmlToMapUtils.getResult(notityXml);
        //每次支付回调都打日志
        log.debug("支付回调："+notityXml);
        String returnCode=(String) resultMap.get("return_code");
        System.out.println(returnCode);
        if(returnCode.equals("SUCCESS")) {
            //校验签名
            Map<String, Object> checkMap= new HashMap<>();
            //	/变更订单状态 业务逻辑 保存微信支付记录
         //   boolean flag = payProcess.wxNotify(resultMap);
            boolean flag =false;
            if(flag) {
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
            else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }


        }
        else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }


        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }
}
