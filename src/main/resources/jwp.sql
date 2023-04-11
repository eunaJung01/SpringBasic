DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
    userId   varchar(12) NOT NULL,
    password varchar(12) NOT NULL,
    name     varchar(20) NOT NULL,
    email    varchar(50),

    PRIMARY KEY (userId)
);

INSERT INTO USERS
VALUES ('admin', 'password', '정우', 'admin@naver.com');

INSERT INTO USERS
VALUES ('euna', 'password', '아랄라', 'aralla@daum.net');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS
(
    questionId    bigint auto_increment,
    writer        varchar(30)   NOT NULL,
    title         varchar(50)   NOT NULL,
    contents      varchar(5000) NOT NULL,
    createdDate   timestamp     NOT NULL,
    countOfAnswer int,
    PRIMARY KEY (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS
(
    answerId    bigint auto_increment,
    writer      varchar(30)   NOT NULL,
    contents    varchar(5000) NOT NULL,
    createdDate timestamp     NOT NULL,
    questionId  bigint        NOT NULL,
    PRIMARY KEY (answerId)
);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('정우',
        '스프링이 갑자기 대세가 된 이유는 뭘까?',
        '어느 순간부터 백엔드하면 스프링이란 말이 돌고 있고, 실제로 대부분의 백엔드 채용이 스프링으로 이루어 진다. 이유가 뭘끼?',
        CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('민병욱',
        'Tomcat 설정이 잘안되는데 이유가 뭐죠??',
        '이거,, webapp 파일을 인식하질 못하는 것 같습니다. 저랑 같은 오류 겪으시는 분?',
        CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('정은아',
        '여러분들은 KUIT에서 무엇을 하고 싶으세요?',
        '저는 쿠잇에 처음 들어와서 지금껏 많은 가치를 얻었고, 또 그 가치를 공유하고 있어요! 여러분들은 어떤가요?',
        CURRENT_TIMESTAMP(), 0);


INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('정경은',
        '같이 술 마실 분?',
        '제가 술을 참 좋아해요! 낯을 안가려서 어느 자리를 가도 mc 하는 편입니다 ^^ 저랑 술드실분?',
        CURRENT_TIMESTAMP(), 3);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('김한주',
        '스프링 질문 받아요',
        '모르셨죠? 사실 제가 스프링 왕입니다^^ 언제든 질문주세요.',
        CURRENT_TIMESTAMP(), 2);


INSERT INTO ANSWERS (writer, contents, createdDate, questionId)
VALUES ('강연주',
        '저는 술 한번 시작하면 안멈추는 불도저에요^^',
        CURRENT_TIMESTAMP(), 4);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId)
VALUES ('문현우',
        '저 불렀나요?',
        CURRENT_TIMESTAMP(), 4);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)
VALUES ('황재상',
        '오늘 그홉 ㄱㄱ?',
        '컴공 낭만 그홉 하실 분 구함 ' ||
        '맥주 먹으면서 백준 푸실 분 구함',
        CURRENT_TIMESTAMP(), 0);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId)
VALUES ('박장우',
        '이것이 회장인가...',
        CURRENT_TIMESTAMP(), 5);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId)
VALUES ('정현석',
        '저는 안드 질문 받아요 24시 open',
        CURRENT_TIMESTAMP(), 5);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId)
VALUES ('송채영',
        '사자 머리 보여드림',
        CURRENT_TIMESTAMP(), 4);