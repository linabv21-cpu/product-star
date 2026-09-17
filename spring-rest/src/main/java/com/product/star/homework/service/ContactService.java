package com.product.star.homework.service;

import com.product.star.homework.Contact;
import com.product.star.homework.ContactDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public Contact getContact(long id) {
        return contactDao.getContact(id);
    }

    public Contact createContact(Contact contact) {
        long id = contactDao.addContact(contact);
        contact.setId(id);

        return contact;
    }

    public Contact updateContact(long id, Contact contact) {
        getContact(id);

        contactDao.updateEmail(id, contact.getEmail());
        contactDao.updatePhoneNumber(id, contact.getPhone());

        return contactDao.getContact(id);
    }
}