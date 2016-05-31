package kino.controller;

import kino.configuration.BeanConfiguration;
import kino.model.ModelFactory;
import kino.model.entities.Contact;
import kino.model.entities.Event;
import kino.service.MailService;
import kino.utils.ErrorGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

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

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public void UploadFile(@RequestParam("event") Integer eventId,
                           @RequestParam("file") MultipartFile file,
                           HttpServletResponse response) {

        String name = file.getOriginalFilename();
        if(name.contains("/")) {
            try {
                response.sendRedirect("/404");
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        if(name.contains("/")) {
            try {
                response.sendRedirect("/404");
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        if(!file.isEmpty()) {
            try {
                String path = getClass().getClassLoader().getResource("log4j.xml").getPath();
                path = path.substring(0, path.indexOf("/WEB-INF"));
                path = path.concat("/resources/upload/");
                File newFile = new File(path + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(newFile)
                );
                FileCopyUtils.copy(file.getInputStream(), stream);
                Event event = ModelFactory.getInstance().EventRepository().findOne(eventId);
                event.setPicture("/resources/upload/"+name);
                ModelFactory.getInstance().EventRepository().saveAndFlush(event);
                stream.close();
            }
            catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        try {
            response.sendRedirect("/#/admin");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/contactUs",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity contactUs(@RequestBody Contact contact){
        try {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
            MailService mailService = applicationContext.getBean(MailService.class);
            mailService.sendMail(contact.getEmail(), "mujic-m@hotmail.com", contact.getSubject(), contact.getMessage());
            return new ResponseEntity(contact, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(ErrorGenerator.generateError("Error happened during sending mail."), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403Page";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return "404";
    }
}