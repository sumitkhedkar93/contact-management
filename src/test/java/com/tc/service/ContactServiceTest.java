package com.tc.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tc.dao.SimpleContactDao;
import com.tc.dao.SimpleContactDaoTest;
import com.tc.dto.ContactDto;

public class ContactServiceTest {

	private ContactService service = new ContactService();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testAddContact() throws Exception {
		
		ContactDto contactDto = SimpleContactDaoTest.createContactDto();
		
		try {
			
			Field contactDao = service.getClass().getDeclaredField("contactDao");
			contactDao.setAccessible(true);
			contactDao.set(service, new SimpleContactDao());
			
			String response = service.addContact(contactDto);

			ObjectNode responseObj =  mapper.createObjectNode();
			responseObj.put("id", contactDto.getId());

			assertTrue(response.equals(mapper.writeValueAsString(responseObj)));
			
			service.deleteContact(contactDto.getId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
