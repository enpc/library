package ru.trsvav.library.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String book;

    private Long chapterNumber;

    @Column(columnDefinition = "text")
    private String text;

    private boolean errorChecked;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Error> errors;
}
