package com.tc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.dao.IContactDao;
import com.tc.dto.ContactDto;

@Service
public class ContactService implements IContactService {

	@Autowired
	private IContactDao contactDao;

	@Override
	public String getAllContacts() {
		return contactDao.getAllContacts();
	}

	@Override
	public String deleteContact(String id) {
		return contactDao.deleteContact(id);
	}

	@Override
	public String updateContact(ContactDto contactDto) {
		return contactDao.updateContact(contactDto);
	}

	@Override
	public String addContact(ContactDto contactDto) {
		return contactDao.addContact(contactDto);
	}

}
