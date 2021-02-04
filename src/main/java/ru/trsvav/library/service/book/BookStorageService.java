package ru.trsvav.library.service.book;

import java.io.InputStream;
import java.util.Set;

public interface BookStorageService {

    void appendBook(String name, InputStream data);

    InputStream findBook(String name);

    Set<String> getAllBooks();
}
