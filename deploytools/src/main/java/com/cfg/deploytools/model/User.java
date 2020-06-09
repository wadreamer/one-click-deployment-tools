package com.cfg.deploytools.model;

/**
 * ClassName: User
 * Description:
 * date: 2020/6/8 8:45
 *
 * @author CFG
 * @since JDK 1.8
 */
public class User {

    private Integer id; // 用户主键
    private String account; // 账号
    private String password; // MD5 加密

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
