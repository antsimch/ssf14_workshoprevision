package sg.edu.nus.iss.ssf14_workshoprevision.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssf14_workshoprevision.model.Contact;
import sg.edu.nus.iss.ssf14_workshoprevision.service.ContactService;

@Controller
@RequestMapping(path = "/api")
public class ContactController {
    
    private ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping(path = "/home")
    public String getLandingPage(Model model) {
        
        model.addAttribute("contact", new Contact());
        return "home";
    }

    @PostMapping(path = "/contact")
    public String addNewContact(@Valid Contact contact,
                                BindingResult binding,
                                Model model) {

        if (binding.hasErrors()) {
            return "home";
        }

        service.saveContact(contact);
        model.addAttribute("successMsg", "Contact saved successfully " + HttpStatus.CREATED);
        return "contact";
    }

    @GetMapping(path = "/contact/{contactId}")
    public String getContact(@PathVariable String contactId, Model model) {

        model.addAttribute("contact", service.getContactById(contactId));
        return "contact";
    }

    @GetMapping(path = "/list")
    public String getAllContacts(Model model) {

        model.addAttribute("contacts", service.getAllContacts());
        return "list";
    }
}
