package com.lms.Iservice;

import java.util.List;

import com.lms.dto.ContactDto;

public interface IContactService {

	  ContactDto sendMessage(ContactDto contactDto);

	    ContactDto getMessageById(Integer contactId);

	    List<ContactDto> getAllMessages();

	    void deleteMessage(Integer contactId);
}
