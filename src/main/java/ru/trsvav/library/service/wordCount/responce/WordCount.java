package ru.trsvav.library.service.wordCount.responce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordCount {
    private Long chapter;
    private String word;
    private Long count;
}
