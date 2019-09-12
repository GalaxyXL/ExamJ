package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "answer_user_paper_question", schema = "examination")
public class AnswerUserPaperQuestion implements Serializable {
    private int id;

    private User user;

    private String answer;
    private byte status;
    private Short score;

    private PaperQuestion paperQuestion;

    @ManyToOne
    @JoinColumn(name = "pqid")
    public PaperQuestion getPaperQuestion() {
        return paperQuestion;
    }

    public void setPaperQuestion(PaperQuestion paperQuestion) {
        this.paperQuestion = paperQuestion;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    @Column(name = "answer", nullable = true, length = 300)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerUserPaperQuestion that = (AnswerUserPaperQuestion) o;
        return id == that.id &&
                paperQuestion == that.paperQuestion &&
                user == that.user &&
                status == that.status &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paperQuestion, user, answer, status, score);
    }
}
