package kino.controller;

import kino.utils.SerializableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Properties;

@RestController
@RequestMapping("/messageBundle")
public class SerializableMessageBundleController {

    @Autowired
    SerializableResourceBundleMessageSource messageBundle;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Properties list(@RequestParam String lang) {
        return messageBundle.getAllProperties(new Locale(lang));
    }
}
