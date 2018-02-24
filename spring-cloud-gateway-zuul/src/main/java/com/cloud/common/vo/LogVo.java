package com.cloud.common.vo;


import com.cloud.common.entity.SysLog;

import java.io.Serializable;

/**
 * @author summer
 * @date 2017/11/20
 */
public class LogVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private SysLog sysLog;
    private String token;

    public SysLog getSysLog() {
        return sysLog;
    }

    public void setSysLog(SysLog sysLog) {
        this.sysLog = sysLog;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
