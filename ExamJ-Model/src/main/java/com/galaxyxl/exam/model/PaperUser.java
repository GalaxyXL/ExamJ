package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "paper_user", schema = "examination")
public class PaperUser implements Serializable {
    private int id;

    private Paper paper;

    private User user;

    private Short score;

    private int status;

    private int rank;

    @ManyToOne
    @JoinColumn(name = "pid")
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
    @Column(name = "score", nullable = true)
    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperUser paperUser = (PaperUser) o;
        return id == paperUser.id &&
                paper == paperUser.paper &&
                user == paperUser.user &&
                status == paperUser.status &&
                Objects.equals(score, paperUser.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paper, user, score, status);
    }
}
