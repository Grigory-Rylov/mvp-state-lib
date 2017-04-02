package com.grishberg.mvpstatelibrary.framework;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grishberg on 22.01.17.
 * Presenter storage
 */
public class MvpDelegate {
    private static Map<String, BaseMvpPresenter> presenterMap = new ConcurrentHashMap<>();

    private MvpDelegate() {
    }

    public static BaseMvpPresenter getPresenter(final Class clazz) {
        return getPresenter(clazz.getName());
    }

    public static BaseMvpPresenter getPresenter(final String tag) {
        return presenterMap.get(tag);
    }

    public static void putPresenter(final Class clazz, BaseMvpPresenter presenter) {
        putPresenter(clazz.getName(), presenter);
    }

    public static void putPresenter(final String tag, BaseMvpPresenter presenter) {
        presenterMap.put(tag, presenter);
    }

    public static void removePresenter(final Class clazz) {
        removePresenter(clazz.getName());
    }

    public static void removePresenter(final String tag) {
        BaseMvpPresenter presenter = presenterMap.get(tag);
        if (presenter != null) {
            presenter.onDestroy();
        }
        presenterMap.remove(tag);
    }
}
