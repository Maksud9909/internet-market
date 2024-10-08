package uz.ccrew.internetmarket.exp;

import org.springframework.http.HttpStatus;

public abstract class BasicException extends RuntimeException {
    public BasicException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();
}