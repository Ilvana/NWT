package kino.service;

import kino.model.ModelFactory;
import kino.model.entities.ResetToken;
import kino.model.entities.User;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ResetTokenService {

    private final SecureRandom random = new SecureRandom();

    public String createToken(User user) {
        ResetToken resetToken = new ResetToken();

        String token = new BigInteger(130, random).toString(32);

        resetToken.setUser(user);
        resetToken.setToken(token);

        ModelFactory.getInstance().ResetTokenRepository().saveAndFlush(resetToken);

        return token;
    }
}
