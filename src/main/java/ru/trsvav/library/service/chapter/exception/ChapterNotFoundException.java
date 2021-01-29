package ru.trsvav.library.service.chapter.exception;

public class ChapterNotFoundException extends RuntimeException{
    public ChapterNotFoundException(String message){
        super(message);
    }
}
