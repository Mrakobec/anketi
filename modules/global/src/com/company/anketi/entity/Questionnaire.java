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
import ru.rsmu.statusmanagement.entity.Status;

@NamePattern("%s|name")
@Table(name = "ANKETI_QUESTIONNAIRE")
@Entity(name = "anketi$Questionnaire")
public class Questionnaire extends StandardEntity {
    private static final long serialVersionUID = 7994703319474898671L;

    @Column(name = "NUMBER_")
    protected Integer number;

    @Lob
    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID")
    protected Status status;

    @OrderBy("number")
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "questionnaire")
    protected List<Question> questions;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }


}