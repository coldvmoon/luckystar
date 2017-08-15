package com.luckystar.web.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by coldvmoon on 13/08/2017.
 */
@Entity
public class UserInfoBoard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 真名
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "真名", required = true)
    @Column(name = "user_name", length = 10)
    private String userName;

    /**
     * 艺名
     */
    @Size(max = 50)
    @ApiModelProperty(value = "艺名")
    @Column(name = "nick_name", length = 50)
    private String nickName;

    /**
     * 繁星id
     */
    @NotNull
    @ApiModelProperty(value = "繁星id", required = true)
    @Column(name = "star_id")
    private Long starId;

    /**
     * 繁星等级
     */
    @ApiModelProperty(value = "繁星等级")
    @Column(name = "star_level")
    private Integer starLevel;

    /**
     * 繁星等级名称
     */
    @Size(max = 20)
    @ApiModelProperty(value = "繁星等级名称")
    @Column(name = "star_name", length = 20)
    private String starName;

    /**
     * 财富等级
     */
    @ApiModelProperty(value = "财富等级")
    @Column(name = "rich_level")
    private Integer richLevel;

    /**
     * 财富等级名称
     */
    @Size(max = 20)
    @ApiModelProperty(value = "财富等级名称")
    @Column(name = "rich_name", length = 20)
    private String richName;


    /**
     * 被关注数
     */
    @ApiModelProperty(value = "被关注数")
    @Column(name = "fans_count")
    private Integer fansCount;

    @Column(name = "bean_by_month")
    private Long beanByMonth;

    @Column(name = "worktime_by_month")
    private Long workTimeByMonth;


    @Column(name = "work_time")
    private Long workTime;



    /**
     * 任务数
     */
    @NotNull
    @ApiModelProperty(value = "任务数", required = true)
    @Column(name = "min_task")
    private Integer minTask;



    /**
     * 目标数
     */
    @NotNull

    @ApiModelProperty(value = "目标数", required = true)
    @Column(name = "max_task")
    private Integer maxTask;

    /**
     * 注册时间
     */
    @NotNull
    @ApiModelProperty(value = "注册时间", required = true)
    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "bean_by_day")
    private Long beanByDay;


    /**
     * 手机号
     */
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    /**
     * 手机号
     */

    @Column(name = "qq", length = 50)
    private String qq;

    /**
     * 微信号
     */
    @Column(name = "wei_chat", length = 50)
    private String weiChat;


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

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public Integer getRichLevel() {
        return richLevel;
    }

    public void setRichLevel(Integer richLevel) {
        this.richLevel = richLevel;
    }

    public String getRichName() {
        return richName;
    }

    public void setRichName(String richName) {
        this.richName = richName;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Long getBeanByMonth() {
        return beanByMonth;
    }

    public void setBeanByMonth(Long beanByMonth) {
        this.beanByMonth = beanByMonth;
    }

    public Long getWorkTimeByMonth() {
        return workTimeByMonth;
    }

    public void setWorkTimeByMonth(Long workTimeByMonth) {
        this.workTimeByMonth = workTimeByMonth;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public Integer getMinTask() {
        return minTask;
    }

    public void setMinTask(Integer minTask) {
        this.minTask = minTask;
    }

    public Integer getMaxTask() {
        return maxTask;
    }

    public void setMaxTask(Integer maxTask) {
        this.maxTask = maxTask;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Long getBeanByDay() {
        return beanByDay;
    }

    public void setBeanByDay(Long beanByDay) {
        this.beanByDay = beanByDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeiChat() {
        return weiChat;
    }

    public void setWeiChat(String weiChat) {
        this.weiChat = weiChat;
    }
}
