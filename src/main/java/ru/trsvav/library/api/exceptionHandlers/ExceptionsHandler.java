package ru.trsvav.library.api.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.trsvav.library.exceptions.ChapterNotFoundException;
import ru.trsvav.library.service.book.exceptions.FileAlreadyExistsException;
import ru.trsvav.library.service.book.exceptions.FileNotFoundException;
import ru.trsvav.library.service.splitter.exceptions.ChapterFormatException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({FileAlreadyExistsException.class})
    public ResponseEntity<Void> fileAlreadyExistsHandler(){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler({FileNotFoundException.class, ChapterNotFoundException.class})
    public ResponseEntity<Void> notFoundHandler(){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler({ChapterFormatException.class})
    public ResponseEntity<Void> chapterFormatHandler(){
        return ResponseEntity.badRequest().build();
    }
}
