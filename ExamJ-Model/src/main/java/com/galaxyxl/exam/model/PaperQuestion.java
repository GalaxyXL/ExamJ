package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paper_question", schema = "examination")
public class PaperQuestion implements Serializable {
    private int id;

    private Question question;

    private Paper paper;

    private int score;

    private List<AnswerUserPaperQuestion> answerUserPaperQuestions = new ArrayList<>();

    @OneToMany(targetEntity = AnswerUserPaperQuestion.class, mappedBy = "paperQuestion")
    public List<AnswerUserPaperQuestion> getAnswerUserPaperQuestions() {
        return answerUserPaperQuestions;
    }

    public void setAnswerUserPaperQuestions(List<AnswerUserPaperQuestion> answerUserPaperQuestions) {
        this.answerUserPaperQuestions = answerUserPaperQuestions;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "qid", referencedColumnName = "id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "pid", referencedColumnName = "id")
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperQuestion that = (PaperQuestion) o;
        return id == that.id &&
                paper == that.paper &&
                question == that.question &&
                score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paper, question, score);
    }
}
