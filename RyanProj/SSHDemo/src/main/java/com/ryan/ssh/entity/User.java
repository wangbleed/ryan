package com.hyron.alarmcenter.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ryan on 15/1/5.
 */
@Entity(name="User")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public User(){
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="username",length=32)
    private String username;

    @Column(name="userpwd",length=16)
    private String userpwd;

    @Column(name="age",length = 2)
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
