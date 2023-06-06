package sg.edu.nus.iss.ssf14_workshoprevision.service;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf14_workshoprevision.model.Contact;
import sg.edu.nus.iss.ssf14_workshoprevision.repository.ContactsRedis;

@Service
public class ContactService {

    private ContactsRedis repo;

    public ContactService(ContactsRedis repo) {
        this.repo = repo;
    }

    public void saveContact(Contact contact) {
        repo.saveContactToRedis(contact);
    }

    public Contact getContactById(String contactId) {
        return repo.getContactById(contactId);
    }
}
