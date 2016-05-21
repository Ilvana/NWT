package kino.configuration;

import kino.model.ModelFactory;
import kino.model.entities.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        ModelFactory modelFactory = ModelFactory.getInstance();

        User user = new User();
        user.setAddress("Zmaja od Bosne bb");
        user.setName("Narcis Behlilovic");
        user.setAdmin(false);
        user.setEmail("cizi@live.com");
        user.setUsername("jhbjhsd");
        user.setPassword("hjsdbdjs");
        user.setEnable(true);
        user.setRole("konj");
        user.setNumber("033-225-883");
        modelFactory.UserRepository().saveAndFlush(user);

        Event event = new Event();

        event.setName("Brucosijada");
        event.setDescription("Loodilo, oduzimanje, mnozenje, dijeljene, rastavljanje na proste faktore.");
        event.setTimeBegin(new Date());
        event.setTimeEnd(new Date());
        modelFactory.EventRepository().saveAndFlush(event);

        Comment comment = new Comment();

        comment.setContent("Bilo je ludo i nezaboravno.");
        comment.setTime(new Date());
        comment.setEvent(event);
        comment.setUser(user);

        System.out.println(user.getId()+"   "+event.getId());

        modelFactory.CommentRepository().saveAndFlush(comment);
        System.out.println("User ID: "+user.getId());

        User fromDB = modelFactory.UserRepository().getOne(user.getId());

        if (fromDB == null)
            throw new RuntimeException("Nije ga fino spremio.");

        if(modelFactory.CommentRepository().findByUser(fromDB).isEmpty()) {
            System.out.println("No comments for user with given ID.");
        }
        System.out.println("User comments:");
        for (int i = 0; i < modelFactory.CommentRepository().findByUser(fromDB).size(); i++) {
            System.out.println(modelFactory.CommentRepository().findByUser(fromDB).get(i).getContent());
        }

        Movie movie=new Movie();
        movie.setDuration(120);
        movie.setDescription("Leonardo di Caprio. Enough said.");
        movie.setName("Catch me if you can");

        modelFactory.MovieRepository().saveAndFlush(movie);

        System.out.println(String.format("Movie id: %d", movie.getId()));

        Movie movie1= modelFactory.MovieRepository().getOne(movie.getId());
        System.out.println(String.format("Movie1 id: %d", movie1.getId()));


        Theater theater=new Theater();
        theater.setName("Cinema city");

        modelFactory.TheaterRepository().saveAndFlush(theater);
        System.out.println(String.format("Theater id: %d", theater.getId()));

        Screening screening=new Screening();
        screening.setTimeBegin(new Date());
        screening.setTimeEnd(new Date());
        screening.setMovie(movie);
        screening.setTheater(theater);

        modelFactory.ScreeningRepository().saveAndFlush(screening);

        System.out.println(String.format("Screening id: %d", screening.getId()));

        Ticket ticket=new Ticket();
        ticket.setSeatY(1);
        ticket.setSeatX(2);
        ticket.setUser(user);
        ticket.setScreening(screening);

        modelFactory.TicketRepository().saveAndFlush(ticket);

        Ticket ticket1=modelFactory.TicketRepository().getOne(ticket.getId());

        System.out.println("Ticket id: "+String.valueOf(ticket1.getId()));

        System.out.println(ticket1);

    }


}