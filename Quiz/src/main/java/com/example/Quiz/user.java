package com.example.Quiz;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class user implements Serializable, Cloneable {

	private Long userName;
	
	private String password;

	private String firstName = "";

	private String surName = "";

	private String email = "";

	public Long getuserName() {
		return userName;
	}

	public void setuserName(Long userName) {
		this.userName = userName;
	}

	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the value of status
	 *
	 * @return the value of status
	 */

	/**
	 * Set the value of status
	 *
	 * @param status
	 *            new value of status
	 */
	
	/**
	 * Get the value of password
	 *
	 * @return the value of password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the value of password
	 *
	 * @param password
	 *            new value of password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the value of surName
	 *
	 * @return the value of surName
	 */
	public String getsurName() {
		return surName;
	}

	/**
	 * Set the value of surName
	 *
	 * @param surName
	 *            new value of surName
	 */
	public void setsurName(String surName) {
		this.surName = surName;
	}

	/**
	 * Get the value of firstName
	 *
	 * @return the value of firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the value of firstName
	 *
	 * @param firstName
	 *            new value of firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isPersisted() {
		return userName != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.userName == null) {
			return false;
		}

		if (obj instanceof user && obj.getClass().equals(getClass())) {
			return this.userName.equals(((user) obj).userName);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (userName == null ? 0 : userName.hashCode());
		return hash;
	}

	@Override
	public user clone() throws CloneNotSupportedException {
		return (user) super.clone();
	}

	@Override
	public String toString() {
		return firstName + " " + surName;
	}
}
