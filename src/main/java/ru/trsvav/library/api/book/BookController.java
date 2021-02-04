package ru.trsvav.library.api.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.trsvav.library.api.book.response.BookItemReponse;
import ru.trsvav.library.api.book.response.SplitBookResponse;

import java.util.Set;

@RequestMapping("/api/book")
public interface BookController {

    @PutMapping
    ResponseEntity<Void> uploadBook(@RequestParam String name, @RequestParam("file") MultipartFile file);

    @PostMapping("/{book}/split")
    ResponseEntity<SplitBookResponse> splitBook(@PathVariable("book") String name);

    @GetMapping
    ResponseEntity<Set<BookItemReponse>> getAllBooks();

    @GetMapping("/{book}/chapters")
    ResponseEntity<Long> getChaptersCount(@PathVariable("book") String book);
}
