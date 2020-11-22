package com.tc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.dto.ContactDto;
import com.tc.service.IContactService;

/** 
 * 
 * @author Sumit Khedkar
 *
 */
@RestController
public class ContactController {
	
	@Autowired
	private IContactService contactService;
	
	@GetMapping("contact")
	public String sendAllContacts() {
		
		return this.contactService.getAllContacts();
	}

	@PostMapping(value = "contact")
	public String addContact(@RequestBody ContactDto newContact) {
		return this.contactService.addContact(newContact);
	}
	
	@DeleteMapping("contact")
	public String deleteContact(@RequestParam(name = "id") String contactId) {
		return this.contactService.deleteContact(contactId);
	}
	
	@PutMapping("contact")
	public String updateContact(@RequestBody ContactDto updatedContact) {
		return this.contactService.updateContact(updatedContact);
	}
}
