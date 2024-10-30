package com.example.contacts.controller;

import com.example.contacts.DTO.ContactDTO;
import com.example.contacts.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @Operation(summary = "List all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact list obtained succesfully",
                    content = @Content(schema = @Schema(implementation = ContactDTO.class))),
            @ApiResponse(responseCode = "204", description = "There are no Contacts",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<ContactDTO>> listContacts(){
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @Operation(summary = "Get a contact by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact by id",
                    content = @Content(schema = @Schema(implementation = ContactDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable long id) {
        if (contactService.findByIdContact(id) != null) {
            return ResponseEntity.ok(contactService.findByIdContact(id));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Contact was created",
                    content = @Content(schema = @Schema(implementation = ContactDTO.class))),
            @ApiResponse(responseCode = "400", description = "invalid request",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contact){
        ContactDTO newContact = contactService.saveContact(contact);
        System.out.println(newContact.toString());
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact updated",
                    content = @Content(schema = @Schema(implementation = ContactDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @RequestBody ContactDTO contact){
        ContactDTO updatedContact = contactService.updateContact(id,contact);
        if(updatedContact == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(updatedContact);
        }
    }

    @Operation(summary = "Delete contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contact deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }



/*
    @GetMapping("/")
    public List<Contact> listContacts(){
        return contactService.findAll();
    }
    @GetMapping("/{id}")
    public Contact getById(@PathVariable Long id){
        return contactService.findByIdContact(id);
    }
    @PostMapping("/")
    public Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return "contact with id "+id+" was deleted";
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact){
        return contactService.updateContact(id,contact);
    }

 */
}
