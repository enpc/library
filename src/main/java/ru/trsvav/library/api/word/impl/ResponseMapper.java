package ru.trsvav.library.api.word.impl;

import org.mapstruct.Mapper;
import ru.trsvav.library.api.word.response.WordCountResponse;
import ru.trsvav.library.service.wordCount.responce.WordCount;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    WordCountResponse wordCountToResponse(WordCount wordCount);

}
