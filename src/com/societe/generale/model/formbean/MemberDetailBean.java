package com.societe.generale.model.formbean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @author ravinder_rana
 *
 */
@Component
public class MemberDetailBean implements Serializable{

	private static final long serialVersionUID = 6411663215471241824L;
	private Integer memberID; 	
	private String status;
	private String race;
	private Integer weightInKG;
	private Integer heightInCM;
	private String isVeg;
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public Integer getWeightInKG() {
		return weightInKG;
	}
	public void setWeightInKG(Integer weightInKG) {
		this.weightInKG = weightInKG;
	}
	public Integer getHeightInCM() {
		return heightInCM;
	}
	public void setHeightInCM(Integer heightInCM) {
		this.heightInCM = heightInCM;
	}
	public String getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}
	
}
