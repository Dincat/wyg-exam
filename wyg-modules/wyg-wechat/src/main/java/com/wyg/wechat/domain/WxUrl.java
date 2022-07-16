package com.wyg.wechat.domain;

public class WxUrl {

    public static final String baseUrl1="https://api.mch.weixin.qq.com/";
    public static final String baseUrl2="https://api.weixin.qq.com/";

    //统一下单
    public static final String UNIFIEDORDER="pay/unifiedorder";

    //查询订单
    public static final String ORDERQUERY="pay/orderquery";

    //关闭订单
    public static final String CLOSEORDER="pay/closeorder";

    //申请退款
    public static final String REFUND="secapi/pay/refund";

    //查询退款
    public static final String REFUNDQUERY="pay/refundquery";

    //下载交易单
    public static final String DOWNLOADBILL="pay/downloadbill";

    //下载资金账单
    public static final String DOWNLOADFUNDFLOW="pay/downloadfundflow";

    //获取用户信息
    public static final String USERINFO="sns/jscode2session";

    //调用接口凭证
    public static final String getAccessToken="cgi-bin/token";

    //付款到零钱
    public static final String transfer="mmpaymkttransfers/promotion/transfers";


    //微信公众号获取code
    public static final String connectOauth2Authorize="https://open.weixin.qq.com/connect/oauth2/authorize";

    //通过code换取网页授权 access_token
    public static final String Oauth2GetToken="sns/oauth2/access_token";


}
