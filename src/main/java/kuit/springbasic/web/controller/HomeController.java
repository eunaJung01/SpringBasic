package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import kuit.springbasic.web.dao.QuestionDao;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

    /**
     * TODO: showHome
     * showHomeV0 : parameter - HttpServletRequest, HttpServletResponse / return - ModelAndView
     * showHomeV1 : parameter - none / return - ModelAndView
     * showHomeV2 : parameter - Model / return - String
     */
    @RequestMapping("/homeV0")
    public ModelAndView showHomeV0(HttpServletRequest request, HttpServletResponse response) {
        log.info("HomeController.homeV0");

        ModelAndView modelAndView = new ModelAndView("home");

        List<Question> questions = questionDao.findAll();
//        modelAndView.getModel().put("questions", questions);
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }

    @RequestMapping("/homeV1")
    public ModelAndView showHomeV1() {
        log.info("HomeController.homeV1");

        ModelAndView modelAndView = new ModelAndView("home");

        List<Question> questions = questionDao.findAll();
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }

    @RequestMapping("/")
    public String showHomeV2(Model model) {
        log.info("HomeController.homeV2");

        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }

}
