package com.datastax.oss.sga.api.codestorage;

public class CodeStorageException extends Exception{
    public CodeStorageException(Throwable cause) {
        super(cause);
    }

    public CodeStorageException(String message) {
        super(message);
    }

    public CodeStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
