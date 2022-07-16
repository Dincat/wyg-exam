package com.wyg.wechat.domain;

import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 微信配置对象 wx_config
 * 
 * @author WorrilessGo
 * @date 2022-07-02
 */
@ApiModel(description = "微信配置")
@Data
@ToString
public class WxConfig extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** ID */
    private Long id;

    /** 微信名称 */
        @ApiModelProperty(value = "微信名称", required =true)
            @Excel(name = "微信名称")
    private String wxName;

    /** 微信编号 */
        @ApiModelProperty(value = "微信编号", required =true)
            @Excel(name = "微信编号")
    private String wxCode;

    /** AppID */
        @ApiModelProperty(value = "AppID", required =true)
            @Excel(name = "AppID")
    private String appId;

    /** AppSecret */
        @ApiModelProperty(value = "AppSecret", required =false)
            @Excel(name = "AppSecret")
    private String appSecret;

    /** Token */
        @ApiModelProperty(value = "Token", required =false)
            @Excel(name = "Token")
    private String token;

    /** Grant Type */
        @ApiModelProperty(value = "Grant Type", required =false)
            @Excel(name = "Grant Type")
    private String grantType;

    /** 商户号 */
        @ApiModelProperty(value = "商户号", required =false)
            @Excel(name = "商户号")
    private String mchId;

    /** 支付密钥 */
        @ApiModelProperty(value = "支付密钥", required =false)
            @Excel(name = "支付密钥")
    private String payKey;

    /** 第三方开放平台（微信、支付宝）发送的EncodingAESKey */
        @ApiModelProperty(value = "第三方开放平台", required =false)
            @Excel(name = "第三方开放平台", readConverterExp = "微=信、支付宝")
    private String aesKey;

    /** 证书地址 */
        @ApiModelProperty(value = "证书地址", required =false)
            @Excel(name = "证书地址")
    private String certPath;

    /** 是否默认 */
        @ApiModelProperty(value = "是否默认", required =false)
            @Excel(name = "是否默认")
    private String isDefault;

    /** 所属用户ID */
        @ApiModelProperty(value = "所属用户ID", required =false)
            @Excel(name = "所属用户ID")
    private Long userId;


}