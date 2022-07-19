package com.capgemini.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("admin")
@Scope("prototype")
@Entity
@Table(name="ADMIN_TBL")
public class Admin {
	@Id
	@Column(name="ADMIN_ID")
	private int adminId;
	@Column(name="ADMIN_NAME")
	private String adminName;
	@Column(name="CONTACT_NO")
	private String adminContact;
	
	public Admin(int adminId, String adminName, String adminContact) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}
	
	
}
