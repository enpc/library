package ru.trsvav.library.service.error;

import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.entity.Error;

import java.util.Set;

public interface ErrorService {

    Chapter scanErrors(Chapter chapter);

    Set<Error> getErrors(String book, Long chapter);


}
