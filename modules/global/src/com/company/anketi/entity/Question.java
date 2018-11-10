package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|questionnaire,number")
@Table(name = "ANKETI_QUESTION")
@Entity(name = "anketi$Question")
public class Question extends StandardEntity {
    private static final long serialVersionUID = 2787395314952411753L;

    @Column(name = "NUMBER_")
    protected Integer number;

    @Column(name = "TYPE_")
    protected String type;

    @Lob
    @Column(name = "CONTENT")
    protected String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRE_ID")
    protected Questionnaire questionnaire;

    @OrderBy("number")
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "question")
    protected List<AnswerVarian> answerVariants;

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setType(QuestionTypeEnum type) {
        this.type = type == null ? null : type.getId();
    }

    public QuestionTypeEnum getType() {
        return type == null ? null : QuestionTypeEnum.fromId(type);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAnswerVariants(List<AnswerVarian> answerVariants) {
        this.answerVariants = answerVariants;
    }

    public List<AnswerVarian> getAnswerVariants() {
        return answerVariants;
    }


}