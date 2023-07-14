package com.solvd.ebayweb.exception;

public class NotClickedException extends Exception {
    public NotClickedException(String message) {
        super(message);
    }
      public NotClickedException(String message, Throwable cause) {
          super(message, cause);
      }
}
