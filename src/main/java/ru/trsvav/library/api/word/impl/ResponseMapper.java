package ru.trsvav.library.api.word.impl;

import org.mapstruct.Mapper;
import ru.trsvav.library.api.word.response.BookWordCountResponse;
import ru.trsvav.library.api.word.response.ChapterWordCountResponse;
import ru.trsvav.library.service.wordCount.responce.WordCount;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    BookWordCountResponse wordCountToBookResponse(WordCount wordCount);

    ChapterWordCountResponse wordCountToChapterResponse(WordCount wordCount);

}
