package com.luckystar.web.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by coldvmoon on 13/08/2017.
 */
@Entity
public class UserInfoBoard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", length = 10, nullable = false)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
