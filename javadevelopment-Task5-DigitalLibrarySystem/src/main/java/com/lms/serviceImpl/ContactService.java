package com.lms.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IContactService;
import com.lms.dto.ContactDto;
import com.lms.entity.Contact;
import com.lms.entity.User;
import com.lms.exception.ContactNotFoundException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.ContactRepository;
import com.lms.repository.UserRepository;

@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    
    public ContactService(ContactRepository contactRepository,UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;

    }
    @Override
    public ContactDto sendMessage(ContactDto contactDto) {
        User user = userRepository.findById(contactDto.getUserId()).orElseThrow(() -> new UserNotFoundException( "User Not Found"));
        Contact contact = new Contact();
        contact.setSubject(contactDto.getSubject());
        contact.setMessage(contactDto.getMessage());
        contact.setCreatedDate(LocalDate.now());
        contact.setUser(user);
        Contact saved =contactRepository.save(contact);
        return convertToDto(saved);

    }


    @Override
    public ContactDto getMessageById(Integer contactId) {
        Contact contact =contactRepository.findById(contactId).orElseThrow(() ->new ContactNotFoundException("Message Not Found"));
        return convertToDto(contact);

    }



    @Override
    public List<ContactDto> getAllMessages() {
    return convertList(contactRepository.findAll());
    }


    @Override
    public void deleteMessage(Integer contactId) {
        Contact contact =contactRepository.findById(contactId) .orElseThrow(() ->new ContactNotFoundException("Message Not Found"));
        contactRepository.delete(contact);

    }
    
    private ContactDto convertToDto(Contact contact){
        ContactDto dto =new ContactDto();
        dto.setContactId(contact.getContactId());
        dto.setSubject(contact.getSubject());
        dto.setMessage(contact.getMessage());
        dto.setCreatedDate(contact.getCreatedDate());
        if(contact.getUser()!=null){
            dto.setUserId(contact.getUser().getUserId());

        }

        return dto;
    }
    
    private List<ContactDto> convertList(List<Contact> contacts){
        List<ContactDto> list =new ArrayList<>();
        for(Contact contact:contacts){
        list.add(convertToDto(contact));
        }
        return list;

    }


}
