package kino.controller;

import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import kino.model.validation.MovieValidator;
import kino.model.ModelFactory;
import kino.model.entities.Movie;
import kino.model.presentation.MovieViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getMovies() {
        try {
            List<Movie> movies = modelFactory.MovieRepository().findAll();
            List<MovieViewModel> movieViewModels = new ArrayList<>();
            for (Movie movie : movies) {
                MovieViewModel movieViewModel = new MovieViewModel(movie);
                movieViewModels.add(movieViewModel);
            }
            logger.info("Returning movies as JSON objects.");
            return new ResponseEntity(movieViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create MovieViewModel. Movie is null", e);
            return new ResponseEntity(generateError("Movie not found."), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getMovie(@PathVariable("id") Integer id) {
        try {
            Movie movie = modelFactory.MovieRepository().findOne(id);
            MovieViewModel movieViewModel = new MovieViewModel(movie);
            logger.info(String.format("Returning movie with ID:%d as JSON object.", id));
            return new ResponseEntity(movieViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Movie ID parameter type is unsupported", e);
            return new ResponseEntity(generateError("Movie ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE);
        } catch (NullPointerException e) {
            logger.error(String.format("Movie not found for ID:%d.", id), e);
            return new ResponseEntity(generateError(String.format("Movie not found for ID:%d.", id)), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity add(@RequestBody Movie movie) {

        if(MovieValidator.isInvalidMovie(movie)){
            logger.error("Movie creation failed. Invalid movie params.");
            return new ResponseEntity(ErrorGenerator.generateError("Movie creation failed. Invalid movie params."), HttpStatus.BAD_REQUEST);
        }

        try {
            Movie savedMovie = modelFactory.MovieRepository().saveAndFlush(movie);
            MovieViewModel movieViewModel = new MovieViewModel(savedMovie);
            logger.info(String.format("Movie successfuly created. Movie ID: %d", movie.getId()));
            return new ResponseEntity(movieViewModel, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create movie.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create movie."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(generateError("Movie wasn't successfully added"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Movie newMovie) {

        if(MovieValidator.isInvalidMovie(newMovie)){
            logger.error("Movie update failed. Invalid movie params.");
            return new ResponseEntity(ErrorGenerator.generateError("Movie update failed. Invalid movie params."), HttpStatus.BAD_REQUEST);
        }

        try {
            Movie movie = modelFactory.MovieRepository().findOne(id);
            movie.setName(newMovie.getName());
            movie.setDescription(newMovie.getDescription());
            movie.setDuration(newMovie.getDuration());
            movie.setDirector(newMovie.getDirector());
            movie.setGenre(newMovie.getGenre());

            modelFactory.MovieRepository().save(movie);
            logger.info(String.format("Movie with ID: %d successfully updated.", movie.getId()));
            return new ResponseEntity(new MovieViewModel(movie), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update movie.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update movie."), HttpStatus.NOT_FOUND
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
            modelFactory.MovieRepository().delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                                    "Success", String.format("Movie wth ID: %d successfully deleted.", id))
                    );
        } catch (NullPointerException e) {
            logger.error("Failed to delete movie.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete movie."), HttpStatus.NOT_FOUND
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

    private Map<String, String> generateError(String errorInfo) {
        Map error = new HashMap<String, String>();
        error.put("Error", errorInfo);
        return error;
    }
}
