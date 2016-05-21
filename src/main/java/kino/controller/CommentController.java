package kino.controller;

import kino.model.ModelFactory;
import kino.model.entities.Comment;
import kino.model.presentation.CommentViewModel;
import kino.utils.ErrorGenerator;
import kino.utils.JsonMessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleRequestBodyException(Exception  exception) {
        logger.error("Error ocurred: ", exception);
        return new ResponseEntity(
                ErrorGenerator.generateError(String.format("Error ocurred: %s", exception.getMessage())), HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getComments() {
        try {
            List<Comment> comments = modelFactory.CommentRepository().findAll();
            final List<CommentViewModel> commentViewModels = new ArrayList<>();
            for(Comment comment : comments) {
                commentViewModels.add(new CommentViewModel(comment));
            }
            logger.info("Returning comments as JSON objects.");
            return new ResponseEntity(commentViewModels, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Could not create CommentViewModel. Comment is null", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Could not create CommentViewModel. Comment is null"), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Unknown exception.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong. Try later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getComment(@PathVariable("id") Integer id) {
        try {
            Comment comment = modelFactory.CommentRepository().findOne(id);
            CommentViewModel commentViewModel = new CommentViewModel(comment);

            logger.info(String.format("Returning comment with ID:%d as JSON object.", id));

            return new ResponseEntity(commentViewModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Comment ID parameter type is unsupported", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Comment ID parameter type is unsupported."), HttpStatus.NOT_ACCEPTABLE
            );
        } catch (NullPointerException e) {
            logger.error(String.format("Comment not found for ID:%d.", id), e);
            return new ResponseEntity(
                    ErrorGenerator.generateError(String.format("Comment not found for ID:%d.", id)), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Something unusual happened. Please try again later.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something unusual happened. Please try again later."), HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Comment comment) {

        try {
            modelFactory.CommentRepository().saveAndFlush(comment);

            logger.info(String.format("Comment successfuly created. Comment ID: %d", comment.getId()));
            return new ResponseEntity(new CommentViewModel(comment), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to create comment.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to create comment."), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Something went wrong."), HttpStatus.NOT_FOUND
            );
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Comment newComment) {

        try {
            Comment comment = modelFactory.CommentRepository().findOne(id);

            comment.setContent(newComment.getContent());
            comment.setEvent(newComment.getEvent());
            comment.setTime(newComment.getTime());
            comment.setUser(newComment.getUser());

            modelFactory.CommentRepository().save(comment);

            logger.info(String.format("Comment with ID: %d successfully updated.", comment.getId()));
            return new ResponseEntity(new CommentViewModel(comment), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("Failed to update comment.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to update comment."), HttpStatus.NOT_FOUND
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
            modelFactory.CommentRepository().delete(id);

            logger.info(String.format("Comment with ID: %d successfully deleted.", id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(JsonMessageGenerator.generateMessage(
                                    "Success", String.format("Comment wth ID: %d successfully deleted.", id))
                    );
        } catch (NullPointerException e) {
            logger.error("Failed to delete comment.", e);
            return new ResponseEntity(
                    ErrorGenerator.generateError("Failed to delete comment."), HttpStatus.NOT_FOUND
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
