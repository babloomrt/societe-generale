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
/**
 * @author ravinder_rana
 *
 */
@NamedQueries(
		{
			@NamedQuery(
						name = "getEthnicGroup",
						query = "FROM EthnicGroup WHERE Ethnic_Group_ID=?1"
					),
			@NamedQuery(
					name = "getAllEthnicGroup",
					query = "FROM EthnicGroup"
				)		
		}
		)
@Entity
@Table(name = "Ethnic_Group")
public class EthnicGroup implements Serializable{
	
	private static final long serialVersionUID = 6334644500299954799L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Ethnic_Group_ID" , unique = true, nullable = false)
	private Integer Ethnic_Group_ID; 
	@Column(name = "Group_Name", nullable = false)
	private String Group_Name;
	public Integer getEthnic_Group_ID() {
		return Ethnic_Group_ID;
	}
	public void setEthnic_Group_ID(Integer ethnic_Group_ID) {
		Ethnic_Group_ID = ethnic_Group_ID;
	}
	public String getGroup_Name() {
		return Group_Name;
	}
	public void setGroup_Name(String group_Name) {
		Group_Name = group_Name;
	}
}
