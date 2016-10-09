package com.rock.advancedday01.model;

/**
 * Created by Rock on 2016/8/29.
 */
public class Model {

    private String name;
    // 在使用瀑布流的布局管理器的时候，加载不同高度
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
