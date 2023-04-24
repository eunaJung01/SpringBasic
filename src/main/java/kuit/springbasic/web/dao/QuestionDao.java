package kuit.springbasic.web.dao;

import kuit.springbasic.jdbc.JdbcTemplate;
import kuit.springbasic.jdbc.KeyHolder;
import kuit.springbasic.web.domain.Question;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "insert into questions (writer, title, contents, createdDate) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4, question.getCreatedDate());
        }, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public void update(Question question) {
        String sql = "update questions set title = ?, contents = ? where questionId = ?";
        jdbcTemplate.update(sql, question.getTitle(), question.getContents(), question.getQuestionId());
    }

    public void delete(int id) {
        String sql = "delete from questions where questionId=?";
        jdbcTemplate.update(sql, pstmt -> pstmt.setObject(1, id));
    }

    public List<Question> findAll() {
        String sql = "select * from questions order by questionId";
        return jdbcTemplate.query(sql, rs ->
                new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "select questionId, writer, title, contents, createdDate, countOfAnswer from questions where questionId=?";
        return jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setObject(1, questionId),
                rs -> new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

    public void updateCountOfAnswer(Question question) {
        String sql = "update questions set countOfAnswer=? where questionId=?";
        jdbcTemplate.update(sql,
                pstmt -> {
                    pstmt.setObject(1, question.getCountOfAnswer());
                    pstmt.setObject(2, question.getQuestionId());
                });
    }

}
