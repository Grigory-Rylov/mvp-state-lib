package com.github.mvpstatelibexample.mvp.models.beans.fourth;

/**
 * Created by grishberg on 22.04.17.
 */

public class PersistentModel {
    private final String name;

    public PersistentModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersistentModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
