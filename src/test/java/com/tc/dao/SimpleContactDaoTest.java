package com.tc.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tc.dto.ContactDto;


public class SimpleContactDaoTest {

	private SimpleContactDao simpleContactDao = new SimpleContactDao();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testAddContact() {

		ContactDto contactDto = createContactDto();
		String str = simpleContactDao.addContact(contactDto);

		try {

			ObjectNode responseObj =  mapper.createObjectNode();
			responseObj.put("id", contactDto.getId());

			assertTrue(str.equals(mapper.writeValueAsString(responseObj)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetAllContact() {
		
		assertNotNull(this.simpleContactDao.getAllContacts());
	}
	
	
	private ContactDto createContactDto() {
		return new ContactDto("firstname","lastname","a@gmail.com","9087345688");
	}
}
