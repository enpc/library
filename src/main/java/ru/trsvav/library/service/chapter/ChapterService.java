package ru.trsvav.library.service.chapter;

import ru.trsvav.library.entity.Chapter;

import java.util.stream.Stream;

//TODO:rename
public interface ChapterService {

    Long split(String name);

    Chapter getChapter(String book, Long chapterNumber);

    Stream<Chapter> getAllChapters(String book, int page, int size);

    Long chaptersCount(String book);
}
