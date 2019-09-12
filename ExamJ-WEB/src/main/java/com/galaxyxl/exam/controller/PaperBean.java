package com.galaxyxl.exam.controller;

import com.galaxyxl.exam.dao.GradesDao;
import com.galaxyxl.exam.dao.PaperDao;
import com.galaxyxl.exam.model.AnswerUserPaperQuestion;
import com.galaxyxl.exam.model.Paper;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.model.User;
import com.galaxyxl.exam.util.EJBUtil;
import com.galaxyxl.exam.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PaperBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(PaperBean.class);

    private GradesDao gradesDao = EJBUtil.getBean(GradesDao.class);

    private PaperDao paperDao = EJBUtil.getBean(PaperDao.class);

    private User user;

    private List<Paper> paperList;

    private List<Paper> examList;

    public void submitPaper(List<AnswerUserPaperQuestion> answerUserPaperQuestions) {
        HttpServletRequest request = HTTPUtil.getRequest();
        answerUserPaperQuestions.forEach(answerUserPaperQuestion -> {
            if (answerUserPaperQuestion.getPaperQuestion().getQuestion().getType() == 1) {
                String value = request.getParameter("inlineRadioOptions" + String.valueOf(answerUserPaperQuestions.indexOf(answerUserPaperQuestion)));
                answerUserPaperQuestion.setAnswer(value);
            }
            answerUserPaperQuestion.setStatus(Byte.parseByte("1"));
        });
        paperDao.saveAnswerPaperQuestions(answerUserPaperQuestions);
        examList.remove(answerUserPaperQuestions.get(0).getPaperQuestion().getPaper());
        gradesDao.setPaperStatus(answerUserPaperQuestions.get(0).getPaperQuestion().getPaper(), user, 2);
        try {
            HTTPUtil.getResponse().sendRedirect(request.getServletContext().getContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUpPaper(Paper paper) {
        PaperUser paperUser = gradesDao.getPaperUserByUidAndPid(user.getId(), paper.getId());
        if (paperUser != null && paperUser.getStatus() ==0) {
            paperUser.setStatus(1);
            gradesDao.update(paperUser);
        } else {
            gradesDao.setPaperStatus(paper, user, 1);
        }
        examList.add(paper);
    }

    public void filterPaper() {
        List<Paper> removeItems = new ArrayList<>();

        paperList.forEach((paper) -> {
            PaperUser paperUser;
            paperUser = gradesDao.getPaperUserByUidAndPid(user.getId(), paper.getId());
            if (paperUser != null){
                if (paperUser.getStatus() == 2 || paperUser.getStatus() == 3)
                    removeItems.add(paper);
                if (paperUser.getStatus() == 1) {
                    examList.add(paper);
                }
            }

        });
        paperList.removeAll(removeItems);
    }

    public boolean isSignedUp(Paper paper) {
        PaperUser paperUser = gradesDao.getPaperUserByUidAndPid(user.getId(), paper.getId());
        if (paperUser == null)
            return true;
        else if (paperUser != null && paperUser.getStatus() == 0)
            return true;
        else
            return false;
    }

    public PaperBean() {
        init();
    }

    private void init() {
        user =(User) HTTPUtil.getSession().getAttribute("CUR_USER");
        paperList = paperDao.findAll(Paper.class);
        examList = new ArrayList<Paper>();
        filterPaper();
    }

    public List<Paper> getExamList() {
        return examList;
    }

    public void setExamList(List<Paper> examList) {
        this.examList = examList;
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }
}
