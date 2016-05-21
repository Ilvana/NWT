package kino.utils;

import kino.model.ReCaptcha;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReCaptchaHttpRequest {

    private final static String USER_AGENT = "Mozilla/5.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaHttpRequest.class);

    public static ReCaptcha post(String userResponse) throws IOException {

        String url = "https://www.google.com/recaptcha/api/siteverify";

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = String.format("secret=6Le89x4TAAAAAKuXgU9b7VbO-ZVaIidlM3jcFFVf&response=%s", userResponse);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return mapResponse(response.toString(), responseCode);
    }

    private static ReCaptcha mapResponse(String response, int responseCode) {
        ObjectMapper mapper = new ObjectMapper();

        ReCaptcha reCaptcha = null;
        try {
            reCaptcha = mapper.readValue(response, ReCaptcha.class);
            reCaptcha.setResponseCode(responseCode);
        } catch (IOException e) {
            LOGGER.error("Error occurred while deserializing JSON response from Google recaptcha.", e);
        }

        return  reCaptcha;
    }
}
