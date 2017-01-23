package com.grishberg.mviexample.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grishberg on 22.01.17.
 * Presenter storage
 */
public class MvpDelegate {
    private static Map<String, BaseMviPresenter> presenterMap = new ConcurrentHashMap<>();

    private MvpDelegate() {
    }

    public static BaseMviPresenter getPresenter(final Class clazz) {
        return presenterMap.get(clazz.getName());
    }

    public static void putPresenter(final Class clazz, BaseMviPresenter presenter) {
        presenterMap.put(clazz.getName(), presenter);
    }

    public static void removePresenter(final Class clazz) {
        presenterMap.remove(clazz.getName());
    }
}
