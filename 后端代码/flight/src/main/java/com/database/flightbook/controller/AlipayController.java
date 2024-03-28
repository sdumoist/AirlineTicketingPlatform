package com.database.flightbook.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.database.flightbook.config.AliPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class AlipayController {
    @Autowired
    private AliPayConfig aliPayConfig;

    @PostMapping("/pay")
    public String pay(String out_trade_no,String total_amount) throws AlipayApiException {
        //1.封装Rsa签名方式
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.URL,
                aliPayConfig.APPID, aliPayConfig.RSA_PRIVATE_KEY,
                aliPayConfig.FORMAT, aliPayConfig.CHARSET,
                aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.SIGNTYPE);

        //2.创建Request请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        //封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        model.setOutTradeNo(out_trade_no); //订单号

        model.setTotalAmount(total_amount);    //价格

        //设置请求参数
        request.setBizModel(model);
        //设置异步回调地址
        request.setNotifyUrl(aliPayConfig.notify_url);
        //设置同步回调地址
        request.setReturnUrl(aliPayConfig.return_url);

        //生成表单
        String form = client.pageExecute(request).getBody();
        //如果支付失败的补偿机制
        log.info("确认订单[{}]付款成功",out_trade_no);
        //修改订单状态 ...

        return form;
    }


}
