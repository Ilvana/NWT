package kino.model.validation;

import kino.model.entities.Event;

public class EventValidator {

    public static boolean isInvalidEvent(Event event) {
        if (event.getName() == null || event.getName().equals("") || !CommonValidators.isValidName(event.getName())) {
            return true;
        }
        if (event.getDescription() == null || event.getDescription().equals("") || !CommonValidators.isValidName(event.getName())) {
            return true;
        }

        if (event.getTimeBegin() == null || event.getTimeBegin().equals("")) {
            return true;
        }


        if (event.getTimeEnd() == null || event.getTimeEnd().equals("")) {
            return true;
        }

        return false;
    }
}
