package com.example.contacts.service;
import com.example.contacts.DTO.ContactDTO;
import com.example.contacts.models.Contact;
import com.example.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    private ContactDTO convertToDTO(Contact contact){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setMail(contact.getMail());
        contactDTO.setAddress(contact.getAddress());
        return contactDTO;
    }
    private Contact convertToEntity(ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        contact.setMail(contactDTO.getMail());
        contact.setAddress(contactDTO.getAddress());
        return contact;
    }

    public List<ContactDTO> getAllContacts(){

        return contactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public ContactDTO saveContact(ContactDTO contactDTO){
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    public ContactDTO findByIdContact(Long id){
        Optional<Contact>  optionalContact = contactRepository.findById(id);
        //actualizar para lanzar exepcion si el usuario no se encuentra
        //return optionalContact.orElse(null);
        return optionalContact.map(this::convertToDTO).orElse(null);
    }
    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }
    public ContactDTO updateContact(Long id, ContactDTO contactDTO){
        Optional<Contact> optionalContactToUpdate = contactRepository.findById(id);
        if (optionalContactToUpdate.isPresent()) {
            Contact contactToUpdate = optionalContactToUpdate.get();
            contactToUpdate.setFirstName(contactDTO.getFirstName());
            contactToUpdate.setLastName(contactDTO.getLastName());
            contactToUpdate.setMail(contactDTO.getMail());
            contactToUpdate.setPhone(contactDTO.getPhone());
            contactToUpdate.setAddress(contactDTO.getAddress());
            Contact updatedContact = contactRepository.save(contactToUpdate);
            return convertToDTO(updatedContact);
        }else{
            return null; //actualizar para lanzar exepcion si el usuario no se encuentra
        }

    }
}
