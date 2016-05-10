package kino.model.validation;

import kino.model.entities.Theater;

public class TheaterValidator {

    public static boolean isInvalidTheater(Theater theater) {
        if(theater.getName() == null || theater.getName().equals("") || !CommonValidators.isValidName(theater.getName())) {
            return true;
        }
        if(theater.getSizeX() == null || theater.getSizeX() <=0) {
            return true;
        }
        if(theater.getSizeY() == null || theater.getSizeY() <=0) {
            return true;
        }
        else {
            return false;
        }
    }
}
