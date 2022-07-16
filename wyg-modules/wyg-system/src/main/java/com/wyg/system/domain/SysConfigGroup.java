package com.wyg.system.domain;

import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 参数分组对象 sys_config_group
 * 
 * @author WorrilessGo
 * @date 2022-07-13
 */
@ApiModel(description = "参数分组")
@Data
@ToString
public class SysConfigGroup extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** 分组ID */
    private Long id;

    /** 分组名称 */
        @ApiModelProperty(value = "分组名称", required =false)
            @Excel(name = "分组名称")
    private String groupName;

    /** 分组编码 */
        @ApiModelProperty(value = "分组编码", required =false)
            @Excel(name = "分组编码")
    private String groupCode;

    /** 显示顺序 */
        @ApiModelProperty(value = "显示顺序", required =false)
            @Excel(name = "显示顺序")
    private String orderNum;


}