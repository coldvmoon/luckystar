package com.luckystar.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TaskInfo.
 */
@Entity
@Table(name = "task_info")
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务数
     */
    @NotNull
    @ApiModelProperty(value = "任务数", required = true)
    @Column(name = "min_task", nullable = false)
    private Integer minTask;

    /**
     * 目标数
     */
    @NotNull
    @ApiModelProperty(value = "目标数", required = true)
    @Column(name = "max_task", nullable = false)
    private Integer maxTask;

    /**
     * 月份
     */
    @NotNull
    @ApiModelProperty(value = "月份", required = true)
    @Column(name = "cur_month", nullable = false)
    private Integer curMonth;

    @OneToMany(mappedBy = "taskInfo")
    @JsonIgnore
    private Set<WorkInfo> workInfos = new HashSet<>();

    @ManyToOne
    private ChickenInfo chickenInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinTask() {
        return minTask;
    }

    public TaskInfo minTask(Integer minTask) {
        this.minTask = minTask;
        return this;
    }

    public void setMinTask(Integer minTask) {
        this.minTask = minTask;
    }

    public Integer getMaxTask() {
        return maxTask;
    }

    public TaskInfo maxTask(Integer maxTask) {
        this.maxTask = maxTask;
        return this;
    }

    public void setMaxTask(Integer maxTask) {
        this.maxTask = maxTask;
    }

    public Integer getCurMonth() {
        return curMonth;
    }

    public TaskInfo curMonth(Integer curMonth) {
        this.curMonth = curMonth;
        return this;
    }

    public void setCurMonth(Integer curMonth) {
        this.curMonth = curMonth;
    }

    public Set<WorkInfo> getWorkInfos() {
        return workInfos;
    }

    public TaskInfo workInfos(Set<WorkInfo> workInfos) {
        this.workInfos = workInfos;
        return this;
    }

    public TaskInfo addWorkInfo(WorkInfo workInfo) {
        this.workInfos.add(workInfo);
        workInfo.setTaskInfo(this);
        return this;
    }

    public TaskInfo removeWorkInfo(WorkInfo workInfo) {
        this.workInfos.remove(workInfo);
        workInfo.setTaskInfo(null);
        return this;
    }

    public void setWorkInfos(Set<WorkInfo> workInfos) {
        this.workInfos = workInfos;
    }

    public ChickenInfo getChickenInfo() {
        return chickenInfo;
    }

    public TaskInfo chickenInfo(ChickenInfo chickenInfo) {
        this.chickenInfo = chickenInfo;
        return this;
    }

    public void setChickenInfo(ChickenInfo chickenInfo) {
        this.chickenInfo = chickenInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskInfo taskInfo = (TaskInfo) o;
        if (taskInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
            "id=" + getId() +
            ", minTask='" + getMinTask() + "'" +
            ", maxTask='" + getMaxTask() + "'" +
            ", curMonth='" + getCurMonth() + "'" +
            "}";
    }
}
