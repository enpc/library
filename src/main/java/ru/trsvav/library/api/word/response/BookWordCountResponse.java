package ru.trsvav.library.api.word.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookWordCountResponse {
    private Long chapter;
    private String word;
    private Long count;
}
