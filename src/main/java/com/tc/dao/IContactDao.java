package com.tc.dao;

import com.tc.dto.ContactDto;

public interface IContactDao {

	String getAllContacts();
	String deleteContact(String id);
	String updateContact(ContactDto contactDto);
	String addContact(ContactDto contactDto);
}