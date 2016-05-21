package kino.model.validation;


import kino.model.entities.Screening;

public class ScreeningValidator {

    public static boolean isInvalidScreening(Screening screening) {
        if(screening.getTimeBegin() == null || screening.getTimeBegin().equals("")) {
            return true;
        }
        if(screening.getTimeEnd() == null || screening.getTimeEnd().equals("")) {
            return true;
        }
        if(screening.getMovie() == null) {
            return true;
        }
        if(screening.getTheater() == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
