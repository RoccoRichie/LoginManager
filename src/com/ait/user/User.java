package com.ait.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User {

	/*
	 * USER INFO FROM DB:: 1 admin 000 System Administrator 2 rep 000 Customer
	 * Service Representative 3 seng 000 Support Engineer 4 neng 000 Network
	 * Management Engineer
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	private String userName;

	private String pWord;

	private String userRole;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getpWord() {
		return pWord;
	}

	public void setpWord(String pWord) {
		this.pWord = pWord;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [id= " + empId + ", User Name= " + userName + ", hashCode= " + hashCode() + "]";
	}

	@Override
	public int hashCode() {
		return this.empId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			if (this.empId == ((User) obj).empId) {
				return true;
			}
		}
		return false;
	}

}
