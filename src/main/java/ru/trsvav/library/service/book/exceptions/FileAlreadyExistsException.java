package ru.trsvav.library.service.book.exceptions;

public class FileAlreadyExistsException extends RuntimeException{
    public FileAlreadyExistsException(String message){
        super(message);
    }
}
