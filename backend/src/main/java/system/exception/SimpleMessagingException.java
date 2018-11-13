package system.exception;

import system.requesthandle.StatusCode;

public class SimpleMessagingException extends RuntimeException {

    private StatusCode code = StatusCode.ERROR;

    public SimpleMessagingException() {
        super();
    }

    public SimpleMessagingException(String message) {
        super(message);
    }

    public SimpleMessagingException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }



}
