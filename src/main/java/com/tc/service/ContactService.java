package com.tc.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.dao.IContactDao;
import com.tc.dto.ContactDto;
import com.tc.exception.ValidationException;

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
		
		validateContact(contactDto);
		return contactDao.addContact(contactDto);
	}

	private void validateContact(ContactDto contactDto) {
		
		Pattern PATTERN = Pattern.compile("[0-9]{10}");
		if (!PATTERN.matcher(contactDto.getMobileNumber()).matches()) {
			throw new ValidationException("Invalid mobile number received. It must contains only 10 digits.");
		}
		
		if (!contactDto.getEmailId().contains("@")) {
			throw new ValidationException("Invalid emailId received.");
		}
	}

}
