package kuit.springbasic.web.controller.qna;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.util.UserSessionUtils;
import kuit.springbasic.web.dao.AnswerDao;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Answer;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnAController {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    @RequestMapping("/show")
    public String showQnA(@RequestParam int questionId, Model model) throws SQLException {
        log.info("QuestionController.showQnA");

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        return "/qna/show";
    }

}
