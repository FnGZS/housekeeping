package com.houseWork.controller.pay;

import com.houseWork.dao.pay.OrderDao;
import com.houseWork.entity.pay.PayOrder;
import com.houseWork.service.pay.PayService;
import com.houseWork.utils.OrderUtils;
import com.houseWork.utils.XmlToMapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzc
 */
@RestController
@Api(tags="支付回调接口",description="支付回调接口")
@RequestMapping("/pay/callBack")
@Slf4j
public class PayCallBackController {
    /**
     * 支付回调
     *
     * @throws IOException
     */
    @Autowired
    private PayService payService;
    @Autowired
    private OrderDao orderDao;
    @ApiOperation(value = "支付回调",notes = "支付回调")
    @GetMapping (value = "/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        Map<String, Object> resultMap = XmlToMapUtils.getResult(notityXml);
        //每次支付回调都打日志

        String returnCode = (String) resultMap.get("return_code");
        if (returnCode.equals("SUCCESS")) {
            //校验签名
            Map<String, Object> checkMap = new HashMap<>();
            //	/变更订单状态 业务逻辑 保存微信支付记录
            boolean flag = false;
            flag = updateOrderByWxNotify(resultMap);
            if (flag) {
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }


        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        log.debug("支付回调：" + notityXml+":"+resXml);

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }
    @Transactional
    public Boolean updateOrderByWxNotify(Map map){
        PayOrder order =  payService.getPayOrderById(map.get("out_trade_no").toString());
        if(order==null){
            return false;
        }
        order.setOrderState(1);
        payService.updatePayOrder(order);
        //如果为开荒定金订单自动生成插入开荒尾款订单
        if(order.getGoodsType()==1){
            order.setId(OrderUtils.getOrderCode(order.getEmployerId().longValue()));
            order.setOrderState(0);
            order.setGoodsType(2);
            payService.insertPayOrder(order);
        }
        orderDao.insertOrder(map);
        return true;
    }
}
