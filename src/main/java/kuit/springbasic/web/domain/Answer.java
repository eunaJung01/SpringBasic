package kuit.springbasic.web.domain;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Answer {

    private int answerId;

    private int questionId;

    private String writer;

    private String contents;

    private Date createdDate = Date.valueOf(LocalDate.now());

    public Answer(int answerId, int questionId, String writer, String contents, Date createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public Answer(int questionId, String writer, String contents) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

}
