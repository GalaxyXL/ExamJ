package com.galaxyxl.exam.controller;

import com.galaxyxl.exam.dao.PaperDao;
import com.galaxyxl.exam.dao.QuestionDao;
import com.galaxyxl.exam.model.*;
import com.galaxyxl.exam.util.EJBUtil;
import com.galaxyxl.exam.util.HTTPUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ExamBean implements Serializable {

    private QuestionDao questionDao = EJBUtil.getBean(QuestionDao.class);

    private PaperDao paperDao = EJBUtil.getBean(PaperDao.class);

    private Paper paper;

    private User user;

    private List<Question> questions;

    private List<AnswerUserPaperQuestion> answerUserPaperQuestions;


    private void init(){
        HttpServletRequest request = HTTPUtil.getRequest();
        String s_pid = request.getParameter("pid");
        Integer pid = null;
        try {
            pid = Integer.parseInt(s_pid);
        } catch (Exception e) {

        }
        user =(User) HTTPUtil.getSession().getAttribute("CUR_USER");
        paper = paperDao.find(Paper.class, pid);
        questions = questionDao.findQuestionsByPaperId(paper.getId());
        answerUserPaperQuestions = new ArrayList<>();
        questions.forEach(question -> {
            AnswerUserPaperQuestion answerUserPaperQuestion = new AnswerUserPaperQuestion();
            answerUserPaperQuestion.setUser(user);
            PaperQuestion paperQuestion = questionDao.findPQIdByPaperAndQuestion(paper.getId(), question.getId());
            answerUserPaperQuestion.setPaperQuestion(paperQuestion);
            answerUserPaperQuestion.setStatus(Byte.parseByte("0"));
            answerUserPaperQuestions.add(answerUserPaperQuestion);
        });
    }

    public ExamBean() {
        init();
    }

    public List<AnswerUserPaperQuestion> getAnswerUserPaperQuestions() {
        return answerUserPaperQuestions;
    }

    public void setAnswerUserPaperQuestions(List<AnswerUserPaperQuestion> answerUserPaperQuestions) {
        this.answerUserPaperQuestions = answerUserPaperQuestions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
