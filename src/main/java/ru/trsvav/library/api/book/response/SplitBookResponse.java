package ru.trsvav.library.api.book.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SplitBookResponse {
    private Long chaptersCount;
}
