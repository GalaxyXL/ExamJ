package com.galaxyxl.exam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private byte role;

    private List<PaperUser> paperUserList = new ArrayList<>();

    private List<AnswerUserPaperQuestion> answerUserPaperQuestions = new ArrayList<>();

    @OneToMany(targetEntity = AnswerUserPaperQuestion.class, mappedBy = "user")
    public List<AnswerUserPaperQuestion> getAnswerUserPaperQuestions() {
        return answerUserPaperQuestions;
    }

    public void setAnswerUserPaperQuestions(List<AnswerUserPaperQuestion> answerUserPaperQuestions) {
        this.answerUserPaperQuestions = answerUserPaperQuestions;
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

    @OneToMany(targetEntity = PaperUser.class ,mappedBy = "user")
    public List<PaperUser> getPaperUserList() {
        return paperUserList;
    }

    public void setPaperUserList(List<PaperUser> paperUserList) {
        this.paperUserList = paperUserList;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, phone, role);
    }
}
