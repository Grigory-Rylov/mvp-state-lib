package com.github.mvpstatelibexample.mvp.models.beans.fourth;

import java.io.Serializable;

/**
 * Created by grishberg on 22.04.17.
 */

public class ApiConvertedModel implements Serializable {
    private final String name;

    public ApiConvertedModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ApiConvertedModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
