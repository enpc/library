package ru.trsvav.library.service.book;

import java.io.InputStream;

public interface BookStorageService {

    void appendBook(String name, InputStream data);

    InputStream findBook(String name);
}
