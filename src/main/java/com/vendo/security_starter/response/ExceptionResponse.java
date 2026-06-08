package com.vendo.security_starter.response;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class ExceptionResponse {

    private String message;
    private Map<String, String> errors;
    private int code;
    private String path;
    private final Instant timestamp = Instant.now();

    public ExceptionResponse() {}

    public ExceptionResponse(String message, Map<String, String> errors, int code, String path) {
        this.message = message;
        this.errors = errors;
        this.code = code;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String message;
        private Map<String, String> errors;
        private int code;
        private String path;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ExceptionResponse build() {
            return new ExceptionResponse(this.message, this.errors, this.code, this.path);
        }
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                ", code=" + code +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return code == that.code && Objects.equals(message, that.message) && Objects.equals(errors, that.errors) && Objects.equals(path, that.path) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, errors, code, path, timestamp);
    }
}
