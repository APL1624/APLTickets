package com.apl.ticket.been.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MineInfoBean {
    private List<String> name;

    private String LogInImag;
    private List<Integer> imageUrl;
    private String logInName;
    private String password;
    private int type;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }



    public String getLogInName() {
        return logInName;
    }

    public void setLogInName(String logInName) {
        this.logInName = logInName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public List<Integer> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<Integer> imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getLogInImag() {
        return LogInImag;
    }

    public void setLogInImag(String logInImag) {
        LogInImag = logInImag;
    }
}
