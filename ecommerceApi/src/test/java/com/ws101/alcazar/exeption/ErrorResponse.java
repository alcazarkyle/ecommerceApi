package com.ws101.alcazar.exeption;

import java.time.LocalDateTime;

public class ErrorResponse {
   
    private LocalDateTime timestamp;
    private int status; // HTTP status code (e.g., 404, 400)
    private String error; // A short description (e.g., "Not Found", "Bad Request")
    private String message; // The detailed error message
    private String path; // The request path that caused the error

    // Constructor
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters are needed for Jackson to serialize this object into JSON
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    // Optional: You can add a method to format the timestamp if you prefer a specific string format
    // public String getFormattedTimestamp() {
    //     return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    // }
}
    

