package kino.controller;

import kino.model.validation.TheaterValidator;
import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import kino.model.entities.Theater;
import kino.model.presentation.TheaterViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kino.model.ModelFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleRequestBodyException(Exception  exception) {
        logger.error("Error ocurred: ", exception);
        return new ResponseEntity(
                ErrorGenerator.generateError(String.format("Error ocurred: %s", exception.getMessage())), HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTheaters() {
        try {
            List<Theater> theaters  = modelFactory.TheaterRepository().findAll();
            final List<TheaterViewModel> theaterViewModels = new ArrayList<>();
            for(Theater theater : theaters) {
                theaterViewModels.add(new TheaterViewModel(theater));
            }
            logger.info("Returning theaters as JSON objects.");
            return new ResponseEntity(theaterViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create TheaterViewModel. Theater is null", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Could not create TheaterViewModel. Theater is null"), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong. Try later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getTheater(@PathVariable("id") Integer id) {
        try {
            Theater theater = modelFactory.TheaterRepository().findOne(id);
            TheaterViewModel theaterViewModel = new TheaterViewModel(theater);

            logger.info(String.format("Returning theater with ID:%d as JSON object.", id));

            return new ResponseEntity(theaterViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Theater ID parameter type is unsupported.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Theater ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (NullPointerException e) {
            logger.error(String.format("Theater not found for ID:%d.", id), e);
            return new ResponseEntity(
                    ErrorGenerator.generateError(String.format("Theater not found for ID:%d.", id)), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Theater theater) {

        if(TheaterValidator.isInvalidTheater(theater)) {
            logger.error("Theater create failed. Invalid theater parameters.");
            return new ResponseEntity(ErrorGenerator.generateError("Theater create failed. Invalid theater parameters."), HttpStatus.BAD_REQUEST);
        }
        try {
            modelFactory.TheaterRepository().saveAndFlush(theater);

            logger.info(String.format("Theater successfully created. Theater ID:%d", theater.getId()));
            return new ResponseEntity(new TheaterViewModel(theater), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create theater.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create theater."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Theater newTheater) {

        if(TheaterValidator.isInvalidTheater(newTheater)) {
            logger.error("Theater update failed. Invalid theater parameters.");
            return new ResponseEntity(ErrorGenerator.generateError("Theater update failed. Invalid theater parameters."), HttpStatus.BAD_REQUEST);
        }

        try {
            Theater theater = modelFactory.TheaterRepository().findOne(id);

            theater.setName(newTheater.getName());
            theater.setSizeX(newTheater.getSizeX());
            theater.setSizeY(newTheater.getSizeY());

            modelFactory.TheaterRepository().save(theater);

            logger.info(String.format("Theater with ID:&d successfully updated.", theater.getId()));
            return new ResponseEntity(new TheaterViewModel(theater), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update theater.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update theater."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            modelFactory.TheaterRepository().delete(id);

            logger.info(String.format("Theater with ID:%d successfully deleted", id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                            "Success", String.format("Theater with ID:%d successfully deleted", id)
                    ));
        } catch (NullPointerException e) {
            logger.error("Failed to delete theater.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete theater."), HttpStatus.NOT_FOUND
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
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }
}
