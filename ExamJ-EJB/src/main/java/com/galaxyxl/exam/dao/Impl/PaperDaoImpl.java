package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.PaperDao;
import com.galaxyxl.exam.model.AnswerUserPaperQuestion;
import com.galaxyxl.exam.model.Paper;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.model.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PaperDaoImpl extends BaseDaoImpl<Paper, Integer> implements PaperDao {

    @Override
    public List<Paper> getPapersByStatus(Integer status) {
        return null;
    }

    @Override
    public void saveAnswerPaperQuestions(List<AnswerUserPaperQuestion> answerUserPaperQuestions) {
        answerUserPaperQuestions.forEach(answerUserPaperQuestion -> {
            em.persist(answerUserPaperQuestion);
        });
    }
}
