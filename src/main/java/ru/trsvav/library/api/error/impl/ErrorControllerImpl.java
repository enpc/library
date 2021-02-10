package ru.trsvav.library.api.error.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.trsvav.library.api.error.ErrorController;
import ru.trsvav.library.api.error.response.ErrorResponse;
import ru.trsvav.library.service.error.ErrorService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ErrorControllerImpl implements ErrorController {

    private final ErrorService errorService;

    private final ErrorResponseMapper mapper;

    @Override
    public ResponseEntity<Set<ErrorResponse>> getErrors(String book, Long chapter) {
        return ResponseEntity.ok(
                errorService.getErrors(book, chapter).stream()
                        .map(mapper::ErrorToErrorResponse)
                        .collect(Collectors.toSet())
        );
    }

}
