package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|question,number")
@Table(name = "ANKETI_ANSWER_VARIAN")
@Entity(name = "anketi$AnswerVarian")
public class AnswerVarian extends StandardEntity {
    private static final long serialVersionUID = -148403569591104085L;

    @Column(name = "NUMBER_")
    protected Integer number;

    @Lob
    @Column(name = "CONTENT")
    protected String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    protected Question question;

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


}