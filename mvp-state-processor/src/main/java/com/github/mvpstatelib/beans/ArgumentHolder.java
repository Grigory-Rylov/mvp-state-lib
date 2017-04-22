package com.github.mvpstatelib.beans;

/**
 * Created by grishberg on 22.04.17.
 */

public class ArgumentHolder {
    private final String methodName;
    private final String argType;

    public ArgumentHolder(String methodName, String argType) {
        this.methodName = methodName;
        this.argType = argType;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getArgType() {
        return argType;
    }
}
