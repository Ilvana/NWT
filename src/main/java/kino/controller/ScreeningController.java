package kino.controller;

import kino.model.ModelFactory;
import kino.model.ScreeningsPerDay;
import kino.model.entities.Screening;
import kino.model.presentation.ScreeningViewModel;
import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/screening")
public class ScreeningController {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ScreeningController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleRequestBodyException(Exception  exception) {
        logger.error("Error ocurred: ", exception);
        return new ResponseEntity(
                ErrorGenerator.generateError(String.format("Error ocurred: %s", exception.getMessage())), HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getScreenings() {
        try {
            List<Screening> screenings = modelFactory.ScreeningRepository().findAll();
            final List<ScreeningViewModel> screeningViewModels = new ArrayList<>();
            for(Screening screening : screenings) {
                screeningViewModels.add(new ScreeningViewModel(screening));
            }
            logger.info("Returning screening as JSON objects.");
            return new ResponseEntity(screeningViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create ScreeningViewModel. Screening is null", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Could not create ScreeningViewModel. Screening is null"), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong. Try later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getScreening(@PathVariable("id") Integer id) {
        try {
            Screening screening = modelFactory.ScreeningRepository().findOne(id);
            ScreeningViewModel screeningViewModel = new ScreeningViewModel(screening);

            logger.info(String.format("Returning screening with ID:%d as JSON object.", id));

            return new ResponseEntity(screeningViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Screening ID parameter type is unsupported", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Screening ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (NullPointerException e) {
            logger.error(String.format("Screening not found for ID:%d.", id), e);
            return new ResponseEntity(
                    ErrorGenerator.generateError(String.format("Screening not found for ID:%d.", id)), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }
    @RequestMapping(value = "/daily", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getScreeningsPerDay() {
        try {
            List<ScreeningsPerDay> screeningsPerDays = new ArrayList<>(7);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(sdf.format(new Date())));

            Calendar calendar = Calendar.getInstance();

            for(int i=1; i <= 7; i++) {

                Date dateBegin = c.getTime();
                calendar.setTime(dateBegin);
                calendar.add(Calendar.DATE, 1);
                Date dateEnd = calendar.getTime();
                Integer numberOfScreenings = modelFactory.ScreeningRepository().getScreeningsNumberPerDay(dateBegin, dateEnd);

                ScreeningsPerDay screeningsPerDay = new ScreeningsPerDay(dateBegin, numberOfScreenings);

                screeningsPerDays.add(screeningsPerDay);

                c.add(Calendar.DATE, 1);
            }

            return new ResponseEntity(screeningsPerDays, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Failed to fetch screenings per day.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Screening screening) {

        try {
            modelFactory.ScreeningRepository().saveAndFlush(screening);

            logger.info(String.format("Screening successfuly created. Screening ID: %d", screening.getId()));
            return new ResponseEntity(new ScreeningViewModel(screening), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create screening.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create screening."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Screening newscreening) {

        try {
            Screening screening = modelFactory.ScreeningRepository().findOne(id);

            screening.setMovie(newscreening.getMovie());
            screening.setTheater(newscreening.getTheater());
            screening.setTimeBegin(newscreening.getTimeBegin());
            screening.setTimeEnd(newscreening.getTimeEnd());

            modelFactory.ScreeningRepository().save(screening);

            logger.info(String.format("Screening with ID: %d successfully updated.", screening.getId()));
            return new ResponseEntity(new ScreeningViewModel(screening), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update screening.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update screening."), HttpStatus.NOT_FOUND
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
            modelFactory.ScreeningRepository().delete(id);

            logger.info(String.format("Screening with ID: %d successfully deleted.", id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                                    "Success", String.format("Screeing wth ID: %d successfully deleted.", id))
                    );
        } catch (NullPointerException e) {
            logger.error("Failed to delete screening.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete screening."), HttpStatus.NOT_FOUND
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
