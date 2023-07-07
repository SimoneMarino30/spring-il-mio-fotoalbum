package org.lessons.springilmiofotoalbum.exceptions;

public class NotUniqueTitleException extends RuntimeException {
    public NotUniqueTitleException(String message) {
        super(message);
    }
}
