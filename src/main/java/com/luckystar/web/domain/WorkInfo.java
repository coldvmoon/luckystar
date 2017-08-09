package com.luckystar.web.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A WorkInfo.
 */
@Entity
@Table(name = "work_info")
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "star_level", nullable = false)
    private Integer starLevel;

    @NotNull
    @Column(name = "rich_level", nullable = false)
    private Integer richLevel;

    @NotNull
    @Column(name = "fisrt_bean", nullable = false)
    private Double fisrtBean;

    @NotNull
    @Column(name = "bean_total", nullable = false)
    private Double beanTotal;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Double coin;

    @NotNull
    @Column(name = "coin_total", nullable = false)
    private Double coinTotal;

    @NotNull
    @Column(name = "fans_count", nullable = false)
    private Integer fansCount;

    @NotNull
    @Column(name = "follow_count", nullable = false)
    private Integer followCount;

    @NotNull
    @Column(name = "experience", nullable = false)
    private Double experience;

    @NotNull
    @Column(name = "work_time", nullable = false)
    private Integer workTime;

    @NotNull
    @Column(name = "cur_month", nullable = false)
    private Integer curMonth;

    @NotNull
    @Column(name = "cur_day", nullable = false)
    private LocalDate curDay;

    @NotNull
    @Column(name = "last_time", nullable = false)
    private LocalDate lastTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public WorkInfo starLevel(Integer starLevel) {
        this.starLevel = starLevel;
        return this;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public Integer getRichLevel() {
        return richLevel;
    }

    public WorkInfo richLevel(Integer richLevel) {
        this.richLevel = richLevel;
        return this;
    }

    public void setRichLevel(Integer richLevel) {
        this.richLevel = richLevel;
    }

    public Double getFisrtBean() {
        return fisrtBean;
    }

    public WorkInfo fisrtBean(Double fisrtBean) {
        this.fisrtBean = fisrtBean;
        return this;
    }

    public void setFisrtBean(Double fisrtBean) {
        this.fisrtBean = fisrtBean;
    }

    public Double getBeanTotal() {
        return beanTotal;
    }

    public WorkInfo beanTotal(Double beanTotal) {
        this.beanTotal = beanTotal;
        return this;
    }

    public void setBeanTotal(Double beanTotal) {
        this.beanTotal = beanTotal;
    }

    public Double getCoin() {
        return coin;
    }

    public WorkInfo coin(Double coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public Double getCoinTotal() {
        return coinTotal;
    }

    public WorkInfo coinTotal(Double coinTotal) {
        this.coinTotal = coinTotal;
        return this;
    }

    public void setCoinTotal(Double coinTotal) {
        this.coinTotal = coinTotal;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public WorkInfo fansCount(Integer fansCount) {
        this.fansCount = fansCount;
        return this;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public WorkInfo followCount(Integer followCount) {
        this.followCount = followCount;
        return this;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Double getExperience() {
        return experience;
    }

    public WorkInfo experience(Double experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public WorkInfo workTime(Integer workTime) {
        this.workTime = workTime;
        return this;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public Integer getCurMonth() {
        return curMonth;
    }

    public WorkInfo curMonth(Integer curMonth) {
        this.curMonth = curMonth;
        return this;
    }

    public void setCurMonth(Integer curMonth) {
        this.curMonth = curMonth;
    }

    public LocalDate getCurDay() {
        return curDay;
    }

    public WorkInfo curDay(LocalDate curDay) {
        this.curDay = curDay;
        return this;
    }

    public void setCurDay(LocalDate curDay) {
        this.curDay = curDay;
    }

    public LocalDate getLastTime() {
        return lastTime;
    }

    public WorkInfo lastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) o;
        if (workInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkInfo{" +
            "id=" + getId() +
            ", starLevel='" + getStarLevel() + "'" +
            ", richLevel='" + getRichLevel() + "'" +
            ", fisrtBean='" + getFisrtBean() + "'" +
            ", beanTotal='" + getBeanTotal() + "'" +
            ", coin='" + getCoin() + "'" +
            ", coinTotal='" + getCoinTotal() + "'" +
            ", fansCount='" + getFansCount() + "'" +
            ", followCount='" + getFollowCount() + "'" +
            ", experience='" + getExperience() + "'" +
            ", workTime='" + getWorkTime() + "'" +
            ", curMonth='" + getCurMonth() + "'" +
            ", curDay='" + getCurDay() + "'" +
            ", lastTime='" + getLastTime() + "'" +
            "}";
    }
}
