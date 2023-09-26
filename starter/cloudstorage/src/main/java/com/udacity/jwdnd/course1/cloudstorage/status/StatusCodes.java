package com.udacity.jwdnd.course1.cloudstorage.status;

public enum StatusCodes {
    SUCCESS(0, "Operation successful"),
    DATABASE(1, "A database error has occurred."),
    DUPLICATE_FILE(2, "This file name already exists."),
    UNAUTHORIZED(3,"File inaccessible");

    private final int id;
    private final String message;

    StatusCodes(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }
}
