package kr.or.ddit.ranger.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RangerVo {
	private String userId;
	private String userNm;
	
	private int listIndex;
	
	// 타입 변환에 필요한 어노테이션. FormattingConversionService.
	@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date birth;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDt;
	
	public RangerVo() {
		
	}
	
	@Override
	public String toString() {
		return "RangerVo [userId=" + userId + ", userNm=" + userNm + ", birth=" + birth + ", regDt=" + regDt + "]";
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public int getListIndex() {
		return listIndex;
	}
	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}
	
	
}









