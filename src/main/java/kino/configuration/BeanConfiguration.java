package kino.configuration;

import kino.service.ResetTokenService;
import kino.service.VerificationTokenService;
import kino.service.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanConfiguration {
    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername("cinema.nwt@gmail.com");
        mailSender.setPassword("cinema2016");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    @Bean
    public MailService mailMail() {
        MailService mailMail = new MailService(mailSender());

        return mailMail;
    }

    @Bean
    public VerificationTokenService VerificationTokenService() {
        return new VerificationTokenService();
    }

    @Bean
    public ResetTokenService ResetTokenService() {
        return new ResetTokenService();
    }
}
