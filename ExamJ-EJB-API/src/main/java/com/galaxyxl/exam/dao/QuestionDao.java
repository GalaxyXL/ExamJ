package com.galaxyxl.exam.dao;

import com.galaxyxl.exam.model.PaperQuestion;
import com.galaxyxl.exam.model.Question;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuestionDao extends BaseDao<Question, Integer> {

    List<Question> findQuestionsByPaperId(Integer pid);

    PaperQuestion findPQIdByPaperAndQuestion(Integer pid, Integer qid);

}
