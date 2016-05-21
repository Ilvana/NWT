package kino.model;

import java.util.Date;

public class ReCaptcha {
    private int responseCode;
    private boolean success;
    private Date challenge_ts;
    private String hostname;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean failed() {
        return !success;
    }

    public Date getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(Date challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "ReCaptcha{" +
                "responseCode=" + responseCode +
                ", success=" + success +
                ", challenge_ts=" + challenge_ts +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
