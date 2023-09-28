package com.udacity.jwdnd.course1.cloudstorage.status;

import java.util.HashMap;
import java.util.Map;

public enum StatusCodes {
    SUCCESS(0, "Operation successful"),
    DATABASE(1, "A database error has occurred."),
    DUPLICATE_FILE(2, "This file name already exists."),
    UNAUTHORIZED(3,"File inaccessible"),
    NOTE_CREATION_ERROR(4, "Error during creating note."),
    NOTE_UPDATE_ERROR(5,"Errur during note update.");

    private static Map<Integer, StatusCodes> statusCodeByStatusNumber = new HashMap<>();

    static {
        for (StatusCodes statusCode : StatusCodes.values()) {
            statusCodeByStatusNumber.put(statusCode.getStatusCode(), statusCode);
        }
    }

    private final int statusCode;
    private final String message;

    StatusCodes(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static StatusCodes getStatusMessageFromNumber(Integer statusNumber) {
        return statusCodeByStatusNumber.get(statusNumber);
    }

    public int getStatusCode() { return statusCode; }
    public String getStatusMessage() { return message; }
}
