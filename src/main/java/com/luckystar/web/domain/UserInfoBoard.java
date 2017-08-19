package com.luckystar.web.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

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
	 * 繁星等级+名称
	 */
	@ApiModelProperty(value = "繁星等级")
	@Column(name = "star_level")
	private String starLevel;

	/**
	 * 财富等级+名称
	 */
	@ApiModelProperty(value = "财富等级")
	@Column(name = "rich_level")
	private String richLevel;

	/**
	 * 被关注数
	 */
	@ApiModelProperty(value = "被关注数")
	@Column(name = "fans_count")
	private Integer fansCount;

	/**
	 * 按天统计的星豆数
	 */
	@Column(name = "bean_by_day")
	private Long beanByDay;

	/**
	 * 按月统计的星豆数
	 */
	@Column(name = "bean_by_month")
	private Long beanByMonth;

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
	 * 按月统计的工作时长
	 */
	@Column(name = "worktime_by_month")
	private Long workTimeByMonth;

	/**
	 * 按界定标准划分后的合格天数
	 */
	@Column(name = "judge_by_month")
	private String judgeTimeByMonth;

	/**
	 * 注册时间
	 */
	@NotNull
	@ApiModelProperty(value = "注册时间", required = true)
	@Column(name = "reg_date")
	private LocalDate regDate;

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

	public String getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}

	public String getRichLevel() {
		return richLevel;
	}

	public void setRichLevel(String richLevel) {
		this.richLevel = richLevel;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Long getBeanByDay() {
		return beanByDay;
	}

	public void setBeanByDay(Long beanByDay) {
		this.beanByDay = beanByDay;
	}

	public Long getBeanByMonth() {
		return beanByMonth;
	}

	public void setBeanByMonth(Long beanByMonth) {
		this.beanByMonth = beanByMonth;
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

	public Long getWorkTimeByMonth() {
		return workTimeByMonth;
	}

	public void setWorkTimeByMonth(Long workTimeByMonth) {
		this.workTimeByMonth = workTimeByMonth;
	}

	public String getJudgeTimeByMonth() {
		return judgeTimeByMonth;
	}

	public void setJudgeTimeByMonth(String judgeTimeByMonth) {
		this.judgeTimeByMonth = judgeTimeByMonth;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

}
