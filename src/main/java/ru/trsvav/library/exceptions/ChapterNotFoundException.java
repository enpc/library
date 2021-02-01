package ru.trsvav.library.exceptions;

public class ChapterNotFoundException extends RuntimeException{
    public ChapterNotFoundException(String message){
        super(message);
    }
}
