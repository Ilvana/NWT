package kino.model.validation;

import kino.model.entities.User;
import kino.model.validation.CommonValidators;

public class UserValidator {

    public static boolean isInvalidUser(User user) {

        if(user.getName() == null || user.getName().equals("") || !CommonValidators.isValidName(user.getName())) {
            return true;
        }

        if(user.getAddress() == null || user.getAddress().equals("") || !CommonValidators.isValidAddress(user.getAddress())) {
            return true;
        }

        if(user.getEmail() == null || user.getEmail().equals("") || !CommonValidators.isValidEmail(user.getEmail())) {
            return true;
        }

        if(user.getNumber() == null || user.getNumber().equals("") || !CommonValidators.isValidPhoneNumber(user.getNumber())) {
            return true;
        }

        return false;
    }

}
