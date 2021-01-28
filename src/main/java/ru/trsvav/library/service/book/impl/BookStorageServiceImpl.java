package ru.trsvav.library.service.book.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import ru.trsvav.library.service.book.BookStorageService;
import ru.trsvav.library.service.book.exceptions.FileNotFoundException;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class BookStorageServiceImpl implements BookStorageService {

    private final GridFsTemplate gridFs;

    @Override
    public void appendBook(String name, InputStream data) {
        gridFs.store(data, name);
    }

    @Override
    public InputStream findBook(String name) {
        var file = gridFs.findOne(new Query(Criteria.where("filename").is(name)));
        if (file == null) {
            throw new FileNotFoundException("file " + name + " not found");
        }
        var resource = gridFs.getResource(file);
        return resource.getContent();
    }
}
