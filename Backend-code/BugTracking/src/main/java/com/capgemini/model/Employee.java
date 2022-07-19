package com.capgemini.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.capgemini.constants.ValidationErrorMessages;

@Component("employee")
@Scope("prototype")
@Entity
@Table(name = "EMPLOYEE_TBL", uniqueConstraints = { @UniqueConstraint(columnNames = { "EMAIL", "CONTACT_NO" }) })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ_GEN")
	@SequenceGenerator(sequenceName = "emp_seq_gen", allocationSize = 1, name = "EMP_SEQ_GEN")
	@Column(name = "EMPLOYEE_ID")
	private int employeeId;

	@NotNull(message= ValidationErrorMessages.EMP_NAME_NOT_NULL)
	@Column(name = "EMP_NAME")
	private String empName;

	@NotNull
	@Email(message = ValidationErrorMessages.EMP_EMAIL_NOT_VALID)
	@Column(name = "EMAIL")
	private String email;

	@NotNull
	@Column(name = "CONTACT_NO", length = 10)
	private String contactNo;
	

	@OneToMany(mappedBy = "employee", cascade= CascadeType.REMOVE)
	private Set<Bug> bugs;

	public Employee() {
	}

	public Employee(String empName, String email, String contactNo) {
		super();

		this.empName = empName;
		this.email = email;
		this.contactNo = contactNo;

	}

	public int getemployeeId() {
		return employeeId;
	}

	public void setemployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", empName=" + empName + ", email=" + email + ", contactNo="
				+ contactNo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (bugs == null) {
			if (other.bugs != null)
				return false;
		} else if (!bugs.equals(other.bugs))
			return false;
		if (contactNo == null) {
			if (other.contactNo != null)
				return false;
		} else if (!contactNo.equals(other.contactNo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}

}
