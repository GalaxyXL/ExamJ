package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Question implements Serializable {
    private int id;
    private int sid;
    private byte type;
    private String title;
    private String answer;

    private List<PaperQuestion> papers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    public List<PaperQuestion> getPapers() {
        return papers;
    }

    public void setPapers(List<PaperQuestion> papers) {
        this.papers = papers;
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
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 300)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "answer", nullable = false, length = 500)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                sid == question.sid &&
                type == question.type &&
                Objects.equals(title, question.title) &&
                Objects.equals(answer, question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sid, type, title, answer);
    }
}
