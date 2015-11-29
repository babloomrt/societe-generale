package com.societe.generale.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@NamedQueries(
		{
			
			@NamedQuery(
					name = "getMemberDetails",
					query = "FROM MemberDetail WHERE status like ?1 OR race like ?2 ORDER BY memberID ASC"
					),
					
			@NamedQuery(
						name = "getMemberDetail",
						query = "FROM MemberDetail WHERE memberID=?1"
					)		
		}
		)
@Component
@Entity
@Table(name = "Member_Detail")
public class MemberDetail implements Serializable{
	
	private static final long serialVersionUID = 264333030845445342L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Member_ID" , unique = true, nullable = false)
	private Integer memberID; 
	@Column(name = "Status", nullable = true)
	private String status;
	@Column(name = "Race", nullable = false)
	private String race;
	@Column(name = "Weight", nullable = false)
	private Integer weight;
	@Column(name = "Height", nullable = false)
	private Integer height;
	@Column(name = "Is_Veg", nullable = false)
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}
	
}
