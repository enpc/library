package ru.trsvav.library.service.splitter;

import ru.trsvav.library.service.splitter.impl.ChapterDescription;

import java.io.InputStream;
import java.util.stream.Stream;

public interface SplitterService {

    Stream<ChapterDescription> splitBook(InputStream input);

}
