package com.tc.service;

import com.tc.dto.ContactDto;

public interface IContactService {

	String getAllContacts();
	String deleteContact(String id);
	String updateContact(ContactDto contactDto);
	String addContact(ContactDto contactDto);
}