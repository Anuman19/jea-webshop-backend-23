package ch.ffhs.library.library.dto;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
public class StripeResponse {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public StripeResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    public StripeResponse() {
    }
}
