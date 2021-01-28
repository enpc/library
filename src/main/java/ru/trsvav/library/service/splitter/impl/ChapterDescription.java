package ru.trsvav.library.service.splitter.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChapterDescription {
    private Long number;
    private String text;
}
