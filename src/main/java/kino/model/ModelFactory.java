package kino.model;

import kino.configuration.PersistenceConfiguration;
import kino.model.entities.VerificationToken;
import kino.model.repositories.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ModelFactory {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;
    private final TheaterRepository theaterRepository;
    private final ScreeningRepository screeningRepository;
    private final TicketRepository ticketRepository;
    private  final MovieRepository movieRepository;
    private  final VerificationTokenRepository verificationTokenRepository;
    private  final ResetTokenRepository resetTokenRepository;

    private ModelFactory() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistenceConfiguration.class);
        userRepository = applicationContext.getBean(UserRepository.class);
        eventRepository = applicationContext.getBean(EventRepository.class);
        commentRepository = applicationContext.getBean(CommentRepository.class);
        theaterRepository = applicationContext.getBean(TheaterRepository.class);
        screeningRepository = applicationContext.getBean(ScreeningRepository.class);
        ticketRepository = applicationContext.getBean(TicketRepository.class);
        movieRepository = applicationContext.getBean(MovieRepository.class);
        verificationTokenRepository = applicationContext.getBean(VerificationTokenRepository.class);
        resetTokenRepository = applicationContext.getBean(ResetTokenRepository.class);
    }

    public static ModelFactory getInstance() {
        return new ModelFactory();
    }

    public UserRepository UserRepository() {
        return userRepository;
    }

    public EventRepository EventRepository() {
        return eventRepository;
    }

    public CommentRepository CommentRepository() {
        return commentRepository;
    }

    public TheaterRepository TheaterRepository() {
        return theaterRepository;
    }

    public ScreeningRepository ScreeningRepository(){
        return screeningRepository;
    }

    public TicketRepository TicketRepository(){
        return  ticketRepository;
    }

    public MovieRepository MovieRepository(){
        return movieRepository;
    }

    public VerificationTokenRepository VerificationTokenRepository(){
        return verificationTokenRepository;
    }

    public ResetTokenRepository ResetTokenRepository(){
        return resetTokenRepository;
    }
}
