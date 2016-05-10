package kino.service;

import java.security.SecureRandom;
import java.math.BigInteger;

import kino.model.ModelFactory;
import kino.model.entities.User;
import kino.model.entities.VerificationToken;

public class VerificationTokenService {

    private final SecureRandom random = new SecureRandom();

    public String createToken(User user) {
        VerificationToken verificationToken = new VerificationToken();

        String token = new BigInteger(130, random).toString(32);

        verificationToken.setUser(user);
        verificationToken.setToken(token);

        ModelFactory.getInstance().VerificationTokenRepository().saveAndFlush(verificationToken);

        return token;
    }
}
