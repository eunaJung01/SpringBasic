package kuit.springbasic.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"questionId"})
public class Question {

    private int questionId;

    private String writer;

    private String title;

    private String contents;

    private Date createdDate = Date.valueOf(LocalDate.now());

    private int countOfAnswer = 0;

    public Question(int questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.countOfAnswer = countOfAnswer;
    }

    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void increaseCountOfAnswer() {
        countOfAnswer += 1;
    }

    public boolean isSameUser(User user) {
        return writer.equals(user.getUserId());
    }

}
