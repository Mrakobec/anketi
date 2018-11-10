package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s|name,endTime")
@Table(name = "ANKETI_PERSON_VARIANT")
@Entity(name = "anketi$PersonVariant")
public class PersonVariant extends StandardEntity {
    private static final long serialVersionUID = -1288829170651820575L;

    @Lob
    @Column(name = "NAME")
    protected String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    protected Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    protected Date endTime;

    @Column(name = "SOLVER_LOGIN")
    protected String solverLogin;

    @OrderBy("orderNumber")
    @OneToMany(mappedBy = "personVariant")
    protected List<PersonVarianQuestion> personVarianQuestions;

    @OneToMany(mappedBy = "personVariant")
    protected List<PersonVarianQuestionnaire> personVariantQuestionnaire;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setSolverLogin(String solverLogin) {
        this.solverLogin = solverLogin;
    }

    public String getSolverLogin() {
        return solverLogin;
    }

    public void setPersonVarianQuestions(List<PersonVarianQuestion> personVarianQuestions) {
        this.personVarianQuestions = personVarianQuestions;
    }

    public List<PersonVarianQuestion> getPersonVarianQuestions() {
        return personVarianQuestions;
    }

    public void setPersonVariantQuestionnaire(List<PersonVarianQuestionnaire> personVariantQuestionnaire) {
        this.personVariantQuestionnaire = personVariantQuestionnaire;
    }

    public List<PersonVarianQuestionnaire> getPersonVariantQuestionnaire() {
        return personVariantQuestionnaire;
    }


}