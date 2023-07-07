package org.lessons.springilmiofotoalbum.exceptions;

public class PhotoNotFoundExceptions extends RuntimeException {
    public PhotoNotFoundExceptions(String message) {
        super(message);
    }
}
