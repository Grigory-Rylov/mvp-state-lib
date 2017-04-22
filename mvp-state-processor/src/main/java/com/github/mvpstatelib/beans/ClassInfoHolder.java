package com.github.mvpstatelib.beans;

import java.util.ArrayList;

/**
 * Created by grishberg on 22.04.17.
 */

public class ClassInfoHolder {
    private final String packageName;
    private final String parentClassName;
    private final ArrayList<ArgumentHolder> argumentsList = new ArrayList<>();

    public ClassInfoHolder(String packageName, String parentClassName) {
        this.packageName = packageName;
        this.parentClassName = parentClassName;
    }

    public void addArgumentHolder(ArgumentHolder argHolderForMethod) {
        argumentsList.add(argHolderForMethod);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public ArrayList<ArgumentHolder> getArgumentsList() {
        return argumentsList;
    }
}
