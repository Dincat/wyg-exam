package com.wyg.exam.domain.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.utils.SnowflakeIdUtils;
import com.wyg.common.security.utils.SecurityUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExamBaseEntity <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected Long id;

    /**
     * 创建者
     */
    protected String creator;

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 更新者
     */
    protected String modifier;

    /**
     * 更新日期
     */
    protected Date modifyDate;

    /**
     * 删除标记 0:正常，1-删除
     */
    protected String delFlag = Constants.NORMAL;

    /**
     * 系统编号
     */
    protected String applicationCode;

    /**
     * 租户编号
     */
    protected String tenantCode;

    /**
     * 是否为新记录
     */
    protected boolean isNewRecord;

    /**
     * 扩展字段
     */
    protected String ext;

    public ExamBaseEntity(Long id) {
        this();
        this.id = id;
    }

    /** 请求参数 */
    @ApiModelProperty(hidden = true)
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if(params==null) params=new HashMap<>();
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * 是否为新记录
     *
     * @return boolean
     */
    public boolean isNewRecord() {
        return this.isNewRecord || this.getId() == null;
    }

    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编号
     * @param tenantCode      租户编号
     */
    public void setCommonValue(String userCode, String applicationCode, String tenantCode) {
        Date currentDate = DateUtils.asDate(LocalDateTime.now());
        if (this.isNewRecord()) {
            this.setId(SnowflakeIdUtils.getSnowflakeId());
            this.setNewRecord(true);
            this.creator = userCode;
            this.createDate = currentDate;
        }
        this.modifier = userCode;
        this.modifyDate = currentDate;
        this.delFlag = Constants.NORMAL;
        this.applicationCode = applicationCode;
        this.tenantCode = tenantCode;
    }

    public void setCommonValue(){
        this.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode()+"",SecurityUtils.getTenantCode());
    }

    /**
     * 置空属性
     */
    public void clearCommonValue() {
        this.creator = null;
        this.createDate = null;
        this.modifier = null;
        this.modifyDate = null;
        this.delFlag = null;
        this.applicationCode = null;
        this.tenantCode = null;
    }
}
