package ru.trsvav.library.api.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trsvav.library.api.error.response.ErrorResponse;

import java.util.Set;

@RequestMapping("/api/error")
public interface ErrorController {

    @GetMapping("/{book}/{chapter}")
    ResponseEntity<Set<ErrorResponse>> getErrors(
            @PathVariable("book") String book,
            @PathVariable("chapter") Long chapter
    );
}
