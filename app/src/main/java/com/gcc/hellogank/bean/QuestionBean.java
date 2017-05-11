package com.gcc.hellogank.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/4/14.
 */

public class QuestionBean implements Serializable {
    private String content;
    private boolean isSelect = false;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
