package com.luckystar.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.luckystar.web.domain.enumeration.State;

/**
 * A ChickenInfo.
 */
@Entity
@Table(name = "chicken_info")
public class ChickenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @NotNull
    @Size(max = 100)
    @Column(name = "nick_name", length = 100, nullable = false)
    private String nickName;

    @NotNull
    @Column(name = "star_id", nullable = false)
    private Integer starId;

    @NotNull
    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @NotNull
    @Size(max = 10480)
    @Column(name = "cookie", length = 10480, nullable = false)
    private String cookie;

    @NotNull
    @Column(name = "time_rate", nullable = false)
    private Double timeRate;

    @NotNull
    @Column(name = "bean_rate", nullable = false)
    private Double beanRate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @OneToMany(mappedBy = "laborUnion")
    @JsonIgnore
    private Set<TaskInfo> taskInfos = new HashSet<>();

    @ManyToOne
    private LaborUnion laborUnion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public ChickenInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public ChickenInfo nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStarId() {
        return starId;
    }

    public ChickenInfo starId(Integer starId) {
        this.starId = starId;
        return this;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public ChickenInfo regDate(LocalDate regDate) {
        this.regDate = regDate;
        return this;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getCookie() {
        return cookie;
    }

    public ChickenInfo cookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Double getTimeRate() {
        return timeRate;
    }

    public ChickenInfo timeRate(Double timeRate) {
        this.timeRate = timeRate;
        return this;
    }

    public void setTimeRate(Double timeRate) {
        this.timeRate = timeRate;
    }

    public Double getBeanRate() {
        return beanRate;
    }

    public ChickenInfo beanRate(Double beanRate) {
        this.beanRate = beanRate;
        return this;
    }

    public void setBeanRate(Double beanRate) {
        this.beanRate = beanRate;
    }

    public State getState() {
        return state;
    }

    public ChickenInfo state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<TaskInfo> getTaskInfos() {
        return taskInfos;
    }

    public ChickenInfo taskInfos(Set<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
        return this;
    }

    public ChickenInfo addTaskInfo(TaskInfo taskInfo) {
        this.taskInfos.add(taskInfo);
        taskInfo.setLaborUnion(this);
        return this;
    }

    public ChickenInfo removeTaskInfo(TaskInfo taskInfo) {
        this.taskInfos.remove(taskInfo);
        taskInfo.setLaborUnion(null);
        return this;
    }

    public void setTaskInfos(Set<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
    }

    public LaborUnion getLaborUnion() {
        return laborUnion;
    }

    public ChickenInfo laborUnion(LaborUnion laborUnion) {
        this.laborUnion = laborUnion;
        return this;
    }

    public void setLaborUnion(LaborUnion laborUnion) {
        this.laborUnion = laborUnion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChickenInfo chickenInfo = (ChickenInfo) o;
        if (chickenInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chickenInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChickenInfo{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", starId='" + getStarId() + "'" +
            ", regDate='" + getRegDate() + "'" +
            ", cookie='" + getCookie() + "'" +
            ", timeRate='" + getTimeRate() + "'" +
            ", beanRate='" + getBeanRate() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
