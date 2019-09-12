package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.QuestionDao;
import com.galaxyxl.exam.model.PaperQuestion;
import com.galaxyxl.exam.model.Question;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class QuestionDaoImpl extends BaseDaoImpl<Question, Integer> implements QuestionDao {

    @Override
    public List<Question> findQuestionsByPaperId(Integer pid) {

        TypedQuery<PaperQuestion> pqquery = em.createQuery("select q from PaperQuestion as q where q.paper.id = :pid", PaperQuestion.class);
        pqquery.setParameter("pid", pid);

        List<Question> questions = new ArrayList<>();
        List<PaperQuestion> paperQuestions = pqquery.getResultList();
        paperQuestions.forEach(paperQuestion -> {
            Question question = find(Question.class, paperQuestion.getQuestion().getId());
            questions.add(question);
        });
        return questions;
    }

    @Override
    public PaperQuestion findPQIdByPaperAndQuestion(Integer pid, Integer qid) {
        TypedQuery<PaperQuestion> query = em.createQuery("select pq from PaperQuestion as pq where pq.paper.id = :pid and pq.question.id = :qid", PaperQuestion.class);
        query.setParameter("pid", pid);
        query.setParameter("qid", qid);
        return query.getSingleResult();
    }
}
