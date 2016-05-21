package kino.controller;

import kino.model.validation.EventValidator;
import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import kino.model.ModelFactory;
import kino.model.entities.Event;
import kino.model.presentation.EventViewModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleRequestBodyException(Exception  exception) {
        logger.error("Error ocurred: ", exception);
        return new ResponseEntity(
                ErrorGenerator.generateError(String.format("Error ocurred: %s", exception.getMessage())), HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getEvents() {
        try {
            List<Event> events = modelFactory.EventRepository().findAll();
            final List<EventViewModel> eventViewModels = new ArrayList<>();
            for(Event event : events) {
                eventViewModels.add(new EventViewModel(event));
            }
            logger.info("Returning events as JSON objects.");
            return new ResponseEntity(eventViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create EventViewModel. Event is null", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Could not create EventViewModel. Event is null"), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong. Try later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEvent(@PathVariable("id") Integer id) {
        try {
            Event event = modelFactory.EventRepository().findOne(id);
            EventViewModel eventViewModel = new EventViewModel(event);

            logger.info(String.format("Returning event with ID:%d as JSON object.", id));

            return new ResponseEntity(eventViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Event ID parameter type is unsupported", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Event ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (NullPointerException e) {
            logger.error(String.format("Event not found for ID:%d.", id), e);
            return new ResponseEntity(
                    ErrorGenerator.generateError(String.format("Event not found for ID:%d.", id)), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Event event) {

        if(EventValidator.isInvalidEvent(event)){
            logger.error("Event creation failed. Invalid event params.");
            return new ResponseEntity(ErrorGenerator.generateError("Event creation failed. Invalid event params."), HttpStatus.BAD_REQUEST);
        }

        try {
            modelFactory.EventRepository().saveAndFlush(event);

            logger.info(String.format("Event successfuly created. Event ID: %d", event.getId()));
            return new ResponseEntity(new EventViewModel(event), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create event.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create event."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Event newEvent) {

        if(EventValidator.isInvalidEvent(newEvent)){
            logger.error("Event update failed. Invalid event params.");
            return new ResponseEntity(ErrorGenerator.generateError("Event creation failed. Invalid event params."), HttpStatus.BAD_REQUEST);
        }
        try {
            Event event = modelFactory.EventRepository().findOne(id);

            event.setName(newEvent.getName());
            event.setDescription(newEvent.getDescription());
            event.setTimeBegin(newEvent.getTimeBegin());
            event.setTimeEnd(newEvent.getTimeEnd());

            modelFactory.EventRepository().save(event);

            logger.info(String.format("Event with ID: %d successfully updated.", event.getId()));
            return new ResponseEntity(new EventViewModel(event), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update event.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update event."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            modelFactory.EventRepository().delete(id);

            logger.info(String.format("Event with ID: %d successfully deleted.", id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                            "Success", String.format("Event wth ID: %d successfully deleted.", id))
                    );
        } catch (NullPointerException e) {
            logger.error("Failed to delete event.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete event."), HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException e) {
            logger.error("The given id must not be null", e);

            return new ResponseEntity(
                    ErrorGenerator.generateError("The given id must not be null"), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());

            return new ResponseEntity(
                    ErrorGenerator.generateError(e.getMessage()), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }
}
