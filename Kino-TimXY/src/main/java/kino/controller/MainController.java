package kino.controller;

import kino.configuration.BeanConfiguration;
import kino.model.entities.Contact;
import kino.service.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value = "/welcome" , method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin");
        model.addAttribute("message", "Admin Page - This is protected page!");
        return "adminPage";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loginPage(Model model, Principal principal) {
        String userName = principal.getName();
        model.addAttribute("message", "Welcome " + userName);
        return "index";
    }

    @RequestMapping(value = "/contactUs",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity contactUs(@RequestBody Contact contact){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        MailService mailService = applicationContext.getBean(MailService.class);
        mailService.sendMail(contact.getEmail(),"mujic-m@hotmail.com",contact.getSubject(),contact.getMessage());
        return new ResponseEntity(contact, HttpStatus.OK);
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403Page";
    }
}