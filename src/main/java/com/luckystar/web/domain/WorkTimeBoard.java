package com.luckystar.web.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class WorkTimeBoard  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 真名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 艺名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 繁星id
     */
    @Column(name = "star_id")
    private Long starId;


    @Column(name = "total_time")
    private Double totalTime;

    @Column(name = "total_day")
    private Double totalDay;

    @Column(name = "cur_day")
    private LocalDate curDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getStarId() {
        return starId;
    }

    public void setStarId(Long starId) {
        this.starId = starId;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Double getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Double totalDay) {
        this.totalDay = totalDay;
    }

    public LocalDate getCurDay() {
        return curDay;
    }

    public void setCurDay(LocalDate curDay) {
        this.curDay = curDay;
    }
}
