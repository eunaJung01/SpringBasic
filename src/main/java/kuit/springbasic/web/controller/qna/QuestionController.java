package kuit.springbasic.web.controller.qna;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.util.UserSessionUtils;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QuestionController {

    private final QuestionDao questionDao;

    @RequestMapping("/form")
    public String showQuestionForm(HttpServletRequest request) {
        log.info("QuestionController.showQuestionForm");

        HttpSession session = request.getSession();
        if (UserSessionUtils.isLoggedIn(session)) {
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }

    //    @RequestMapping("/create")
    public String createQuestionV1(@RequestParam String writer, @RequestParam String title, @RequestParam String contents) throws SQLException {
        log.info("QuestionController.createQuestionV1");

        Question question = new Question(writer, title, contents, 0);
        questionDao.insert(question);

        return "redirect:/";
    }

    @RequestMapping("/create")
    public String createQuestionV2(@ModelAttribute Question question) throws SQLException {
        log.info("QuestionController.createQuestionV2");
        questionDao.insert(question);
        return "redirect:/";
    }

    //    @RequestMapping("/updateForm")
    public String showUpdateQuestionFormV1(@RequestParam int questionId,
                                           HttpServletRequest request, Model model) throws SQLException {
        log.info("QuestionController.showUpdateQuestionFormV1");

        HttpSession session = request.getSession();
        if (!UserSessionUtils.isLoggedIn(session)) {
            return "redirect:/user/loginForm";
        }

        Question question = questionDao.findByQuestionId(questionId);
        User userFromSession = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(Objects.requireNonNull(userFromSession))) {
            throw new IllegalArgumentException();
        }

        model.addAttribute("question", question);
        return "/qna/updateForm";
    }

    @RequestMapping("/updateForm")
    public String showUpdateQuestionFormV2(@RequestParam int questionId,
                                           @SessionAttribute(name = UserSessionUtils.USER_SESSION_KEY, required = false) User userFromSession,
                                           Model model) throws SQLException {
        log.info("QuestionController.showUpdateQuestionFormV2");

        if (userFromSession == null) {
            return "redirect:/user/loginForm";
        }

        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(Objects.requireNonNull(userFromSession))) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("question", question);

        return "/qna/updateForm";
    }

    @RequestMapping("/update")
    public String updateQuestion(@RequestParam int questionId, @RequestParam String title, @RequestParam String contents,
                                 @SessionAttribute(name = UserSessionUtils.USER_SESSION_KEY, required = false) User userFromSession) throws SQLException {
        log.info("QuestionController.updateQuestion");

        if (userFromSession == null) {
            return "redirect:/user/loginForm";
        }

        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(userFromSession)) {
            throw new IllegalArgumentException();
        }
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);

        return "redirect:/";
    }

}
