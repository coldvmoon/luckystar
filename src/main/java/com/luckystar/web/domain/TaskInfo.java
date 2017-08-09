package com.luckystar.web.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
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

    @NotNull
    @Column(name = "min_task", nullable = false)
    private Integer minTask;

    @NotNull
    @Column(name = "max_task", nullable = false)
    private Integer maxTask;

    @NotNull
    @Column(name = "cur_month", nullable = false)
    private Integer curMonth;

    @ManyToOne
    private ChickenInfo laborUnion;

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

    public ChickenInfo getLaborUnion() {
        return laborUnion;
    }

    public TaskInfo laborUnion(ChickenInfo chickenInfo) {
        this.laborUnion = chickenInfo;
        return this;
    }

    public void setLaborUnion(ChickenInfo chickenInfo) {
        this.laborUnion = chickenInfo;
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
