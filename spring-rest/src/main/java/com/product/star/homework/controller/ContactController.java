package com.product.star.homework.controller;

import com.product.star.homework.Contact;
import com.product.star.homework.service.ContactService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable long id) {
        try {
            Contact contact = contactService.getContact(id);
            return ResponseEntity.ok(contact);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(
            @RequestBody Contact contact) {

        Contact createdContact =
                contactService.createContact(contact);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdContact);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable long id,
            @RequestBody Contact contact) {

        try {
            Contact updatedContact =
                    contactService.updateContact(id, contact);

            return ResponseEntity.ok(updatedContact);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}