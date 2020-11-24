package com.tc.dto;

/** It represents Contact information for 
 *  particular person.
 *  
 * @author Sumit Khedkar
 */
public class ContactDto {

	private String id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNumber;
	private boolean status;
	
	public ContactDto() {
	}
	
	public ContactDto(String id, String firstName, String lastName, String emailId, String mobileNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.status = true;
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ContactDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + "]";
	}
	
}
