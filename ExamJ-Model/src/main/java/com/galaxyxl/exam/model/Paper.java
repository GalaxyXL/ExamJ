package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Paper implements Serializable {
    private int id;
    private String title;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<PaperUser> paperUserList = new ArrayList<>();

    private List<PaperQuestion> questions = new ArrayList<>();

    private Subject subject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper")
    public List<PaperQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PaperQuestion> questions) {
        this.questions = questions;
    }

    @ManyToOne
    @JoinColumn(name = "sid")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToMany(targetEntity = PaperUser.class, mappedBy = "paper")
    public List<PaperUser> getPaperUserList() {
        return paperUserList;
    }

    public void setPaperUserList(List<PaperUser> paperUserList) {
        this.paperUserList = paperUserList;
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
    @Column(name = "title", nullable = false, length = 10)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = false)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return id == paper.id &&
                Objects.equals(subject, paper.subject) &&
                Objects.equals(title, paper.title) &&
                Objects.equals(startTime, paper.startTime) &&
                Objects.equals(endTime, paper.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, title, startTime, endTime);
    }
}
