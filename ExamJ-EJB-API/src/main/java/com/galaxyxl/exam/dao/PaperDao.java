package com.galaxyxl.exam.dao;

import com.galaxyxl.exam.model.AnswerUserPaperQuestion;
import com.galaxyxl.exam.model.Paper;
import com.galaxyxl.exam.model.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PaperDao extends BaseDao<Paper, Integer> {

    List<Paper> getPapersByStatus(Integer status);

    void saveAnswerPaperQuestions(List<AnswerUserPaperQuestion> answerUserPaperQuestions);


}
