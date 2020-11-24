package com.tc.service;

import java.util.Random;
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
	
	private static final Random random = new Random();

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

		String id = contactDto.getId() == null ? "" + random.nextLong() : contactDto.getId();
		
		ContactDto updatedContactDto = new ContactDto(id, contactDto.getFirstName(),
				contactDto.getLastName(),contactDto.getEmailId(),contactDto.getMobileNumber());
		return contactDao.addContact(updatedContactDto);
	}

	/** Validation on email and mobile number.*/
	private void validateContact(ContactDto contactDto) {

		Pattern mobilePattern = Pattern.compile("[0-9]{10}");

		if (!mobilePattern.matcher(contactDto.getMobileNumber()).matches()) {
			throw new ValidationException("Invalid mobile number received. It must contains only 10 digits.");
		}

		if (!contactDto.getEmailId().contains("@")) {
			throw new ValidationException("Invalid emailId received.");
		}
	}

}
