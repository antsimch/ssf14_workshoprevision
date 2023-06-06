package sg.edu.nus.iss.ssf14_workshoprevision.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ssf14_workshoprevision.model.Contact;

@Repository
public class ContactsRedis {

    RedisTemplate<String, Object> template;

    private static final String CONTACT_LIST = "contactList";

    public ContactsRedis(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public void saveContactToRedis(Contact contact) {

        template.opsForList().leftPush(CONTACT_LIST, contact.getId());
        template.opsForHash().put(CONTACT_LIST + "_HASH", 
                                        contact.getId(), contact);        
    }

    public Contact getContactById(String contactId) {

        Contact contact = (Contact) template.opsForHash()
                                .get(CONTACT_LIST + "_HASH", contactId);

        return contact;
    }
}
