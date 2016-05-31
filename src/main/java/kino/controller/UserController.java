package kino.controller;

import kino.configuration.BeanConfiguration;
import kino.model.ReCaptcha;
import kino.model.UserResponseWrapper;
import kino.model.entities.ResetToken;
import kino.model.entities.VerificationToken;
import kino.model.validation.CommonValidators;
import kino.service.ResetTokenService;
import kino.service.VerificationTokenService;
import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import kino.model.validation.UserValidator;
import kino.model.ModelFactory;
import kino.model.entities.User;
import kino.model.presentation.UserViewModel;
import kino.service.MailService;
import kino.utils.ReCaptchaHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleRequestBodyException(Exception  exception) {
        logger.error("Error ocurred: ", exception);
        return new ResponseEntity(
                ErrorGenerator.generateError(String.format("Error ocurred: %s", exception.getMessage())), HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUsers() {
        try {
            List<User> users = modelFactory.UserRepository().findAll();
            List<UserViewModel> userViewModels = new ArrayList<>();
            for (User user : users) {
                userViewModels.add(new UserViewModel(user));
            }
            logger.info("Returning users as JSON objects.");
            return new ResponseEntity(userViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create UserViewModel. User is null", e);
            return new ResponseEntity(ErrorGenerator.generateError("User not found."), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser(@PathVariable("id") Integer id) {
        try {
            User user = modelFactory.UserRepository().findOne(id);
            UserViewModel userViewModel = new UserViewModel(user);
            logger.info(String.format("Returning user with ID:%d as JSON object.", id));
            return new ResponseEntity(userViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("User ID parameter type is unsupported", e);
            return new ResponseEntity(ErrorGenerator.generateError("User ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE);
        } catch (NullPointerException e) {
            logger.error(String.format("User not found for ID:%d.", id), e);
            return new ResponseEntity(ErrorGenerator.generateError(String.format("User not found for ID:%d.", id)), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/registration/{token}", method = RequestMethod.GET)
    public void verifyRegistration(@PathVariable("token") String token, HttpServletResponse response) {
        List<VerificationToken> verificationTokens = ModelFactory
                                                        .getInstance()
                                                        .VerificationTokenRepository()
                                                        .findByToken(token);

        if(verificationTokens.size()==1) {
            VerificationToken verificationToken = verificationTokens.get(0);
            User user = ModelFactory.getInstance().UserRepository().findOne(verificationToken.getUser().getId());
            user.setEnable(true);

            ModelFactory.getInstance().UserRepository().saveAndFlush(user);

            try {
                response.sendRedirect("/#/login");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            try {
                response.sendRedirect("/404");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }


    }
    @RequestMapping("/logged")
    public void isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
            response.setStatus(200);
        } else {
            response.setStatus(404);
        }
    }
    @RequestMapping(value = "/newPassword/{token}", method = RequestMethod.POST)
    public ResponseEntity createNewPassword(@PathVariable("token") String token, @RequestBody String password) {
        List<ResetToken> resetTokens = ModelFactory
                                            .getInstance()
                                            .ResetTokenRepository()
                                            .findByToken(token);

        if(resetTokens.size()==1) {
            ResetToken resetToken = resetTokens.get(0);
            User user = ModelFactory.getInstance().UserRepository().findOne(resetToken.getUser().getId());

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(password));

            ModelFactory.getInstance().UserRepository().saveAndFlush(user);

            return new ResponseEntity("Password reset success.", HttpStatus.OK);
        } else {
            return new ResponseEntity("Password reset failure.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping( method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity add(@RequestBody UserResponseWrapper userResponseWrapper, HttpServletRequest request) {

        User user = userResponseWrapper.user();

        if(UserValidator.isInvalidUser(user)) {
            logger.error("User creation failed. Invalid user params.");
            return new ResponseEntity(ErrorGenerator.generateError("User creation failed. Invalid user params."), HttpStatus.BAD_REQUEST);
        }

        if(!request.isUserInRole("ROLE_ADMIN")) {

            ReCaptcha reCaptcha;
            try {
                reCaptcha = ReCaptchaHttpRequest.post(userResponseWrapper.getRecaptcha());
            } catch (IOException e) {
                logger.error("ReCaptcha failed.", e);
                return new ResponseEntity(ErrorGenerator.generateError("ReCaptcha failed."), HttpStatus.FORBIDDEN);
            }
            if (reCaptcha.failed()) {
                return new ResponseEntity(ErrorGenerator.generateError("ReCaptcha failed. You might be a robot."), HttpStatus.FORBIDDEN);
            }

        }

        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = modelFactory.UserRepository().saveAndFlush(user);
            UserViewModel userViewModel = new UserViewModel(savedUser);
            logger.info(String.format("User successfully created. User ID: %d", user.getId()));

            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

            VerificationTokenService verificationTokenService = applicationContext.getBean(VerificationTokenService.class);
            MailService mailService = applicationContext.getBean(MailService.class);

            String url = String.format("localhost:8080/user/registration/%s", verificationTokenService.createToken(savedUser));
            String message = String.format("Please visit %s to complete your registration.", url);

            mailService.sendMail("no-reply@cinema-nwt.com", savedUser.getEmail(), "Registration confirmation", message);

            return new ResponseEntity(userViewModel, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create user.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create user."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(ErrorGenerator.generateError("User wasn't successfully added"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping( value = "/reset", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity reset(@RequestBody String email) {

        if(!CommonValidators.isValidEmail(email)) {
            logger.error("Password reset failed. Wrong email format.");
            return new ResponseEntity(ErrorGenerator.generateError("Password reset failed. Wrong email format."), HttpStatus.BAD_REQUEST);
        }

        List<User> users = modelFactory.UserRepository().findByEmail(email);

        if(users.size() != 1) {
            logger.error("Password reset failed. User with given email doesn't exist.");
            logger.error(String.format("Email: %s", email));
            return new ResponseEntity(ErrorGenerator.generateError("Password reset failed. User with given email doesn't exist."), HttpStatus.NOT_FOUND);
        }

        User user = users.get(0);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        ResetTokenService resetTokenService = applicationContext.getBean(ResetTokenService.class);
        MailService mailService = applicationContext.getBean(MailService.class);

        String url = String.format("localhost:8080/#/newPassword/%s", resetTokenService.createToken(user));
        String message = String.format("Please visit %s to reset your password.", url);

        mailService.sendMail("no-reply@cinema-nwt.com", user.getEmail(), "Password reset", message);

        return new ResponseEntity(JsonMessageGenerator.generateMessage("Requested new password", "true"), HttpStatus.OK);
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST)
    public void checkLogin(@RequestBody User userCredentials, HttpServletResponse response) {

        List<User> users = modelFactory
                                .UserRepository()
                                .findByUsername(userCredentials.getUsername());

        Integer httpStatus = isValidUser(userCredentials, users) ? 200 : 404;

        response.setStatus(httpStatus);
    }

    private boolean isValidUser(User userCredentials, List<User> users) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return users.size() == 1 && passwordEncoder.matches(userCredentials.getPassword(), users.get(0).getPassword());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody User newUser) {

        if(UserValidator.isInvalidUser(newUser)) {
            logger.error("User update failed. Invalid user params.");
            return new ResponseEntity(ErrorGenerator.generateError("User update failed. Invalid user params."), HttpStatus.BAD_REQUEST);
        }

        try {
            User user = modelFactory.UserRepository().findOne(id);
            user.setName(newUser.getName());
            user.setAddress(newUser.getAddress());
            user.setEmail(newUser.getEmail());
            user.setNumber(newUser.getNumber());
            user.setEnable(newUser.isEnabled());
            user.setAdmin(newUser.isAdmin());
            user.setRole(newUser.getRole());
            user.setUsername(newUser.getUsername());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));

            modelFactory.UserRepository().save(user);
            logger.info(String.format("User with ID: %d successfully updated.", user.getId()));
            return new ResponseEntity(new UserViewModel(user), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update user.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update user."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
        try {
            modelFactory.UserRepository().delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                                    "Success", String.format("User wth ID: %d successfully deleted.", id))
                    );
        } catch (NullPointerException e) {
            logger.error("Failed to delete user.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete user."), HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException e) {
            logger.error("The given id must not be null", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("The given id must not be null"), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return new ResponseEntity(
                    ErrorGenerator.generateError(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }
}
