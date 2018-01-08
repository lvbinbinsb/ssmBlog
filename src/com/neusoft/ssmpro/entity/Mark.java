package com.neusoft.ssmpro.entity;

import java.util.Date;

public class Mark {
    private Integer markId;

    private String markName;

    private String markStatus;

    private Date markCtime;

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName == null ? null : markName.trim();
    }
    public String getMarkStatus() {
        return markStatus;
    }

    public void setMarkStatus(String markStatus) {
        this.markStatus = markStatus == null ? null : markStatus.trim();
    }

    public Date getMarkCtime() {
        return markCtime;
    }

    public void setMarkCtime(Date markCtime) {
        this.markCtime = markCtime;
    }

	@Override
	public String toString() {
		return "Mark [markId=" + markId + ", markName=" + markName + ", markStatus=" + markStatus + ", markCtime="
				+ markCtime + "]";
	}
    
}