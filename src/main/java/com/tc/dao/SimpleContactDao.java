package com.tc.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tc.dto.ContactDto;

/** This class has CRUD methods related to work with json database.
 * 
 * @author Sumit Khedkar.
 *
 */
@Component
public class SimpleContactDao implements IContactDao {

	private final Logger log = LoggerFactory.getLogger(SimpleContactDao.class); 
	private Map<String, ContactDto> contactDtoMap;
	private ObjectMapper mapper = new ObjectMapper();
	private static final String DB_FILE_NAME = "contact.json";
	
	
	public SimpleContactDao() {
		try {
			File contactFile = new File(DB_FILE_NAME);
			if (!contactFile.exists()) {
				if (contactFile.createNewFile()) {
					FileWriter fwriter = new FileWriter(contactFile);
					fwriter.write("{}");
					fwriter.close();
					contactDtoMap = new HashMap<>();
				} else {
					log.error("File could not created.");
				}

				log.info("created file : {}", contactFile.getAbsolutePath());
			} else {
				log.info("file exists : {}", contactFile.getAbsolutePath());
				loadFile(contactFile);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			log.error("something went wrong while initializong contact information.");
		}
	}
	
	/** This method loads information from existing contact json file.
	 * 
	 * @param contactFile
	 * @throws IOException 
	 */
	private void loadFile(File contactFile) throws IOException {
		
		String jsonData = Files.readString(Paths.get(contactFile.toURI()));
	
		contactDtoMap = mapper.readValue(jsonData, new TypeReference<Map<String, ContactDto>>(){});
		log.info("contact info populated successfully.");
		log.info("contact info : {}", contactDtoMap);
	}

	private void writeDataToFile(File contactFile, Map<String, ContactDto> contactDtoMap) throws IOException {
		FileWriter fwriter = new FileWriter(contactFile);
		fwriter.write(mapper.writeValueAsString(contactDtoMap));
		fwriter.close();
	}

	@Override
	public String getAllContacts() {
		try {
			return mapper.writeValueAsString(contactDtoMap);
		} catch (JsonProcessingException e) {
			log.error("Error occurred while getting contact details.");
			return null;
		}
		
	}

	@Override
	public String deleteContact(String id) {
		ObjectNode responseObj = null;
		try {
			contactDtoMap.remove(id);
			writeDataToFile(new File(DB_FILE_NAME), contactDtoMap);
			responseObj =  mapper.createObjectNode();
			responseObj.put("id", id);
			return mapper.writeValueAsString(responseObj);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("Error occurred while deleting contact.");
			return null;
		}
	}

	@Override
	public String updateContact(ContactDto contactDto) {
		ObjectNode responseObj = null;
		try {
			contactDtoMap.put(contactDto.getId(), contactDto);
			writeDataToFile(new File(DB_FILE_NAME), contactDtoMap);
			responseObj =  mapper.createObjectNode();
			responseObj.put("id", contactDto.getId());
			return mapper.writeValueAsString(responseObj);
		} catch(IOException ioe) {
			log.error("Error occurred while updating json database file.");
			return null;
		}
	}

	@Override
	public String addContact(ContactDto contactDto) {
		return updateContact(contactDto);
	}
}
