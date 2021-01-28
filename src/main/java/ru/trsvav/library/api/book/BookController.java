package ru.trsvav.library.api.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.trsvav.library.api.book.response.SplitBookResponse;

@RequestMapping("/api/book")
public interface BookController {

    @PutMapping
    ResponseEntity<Void> uploadBook(@RequestParam String name, @RequestParam("file") MultipartFile file);

    @PostMapping
    ResponseEntity<SplitBookResponse> splitBook(@RequestParam String name);
}
