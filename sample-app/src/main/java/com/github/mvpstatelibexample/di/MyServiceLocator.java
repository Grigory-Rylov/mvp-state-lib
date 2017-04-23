package com.github.mvpstatelibexample.di;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.mvpstatelibexample.mvp.models.fourth.ComplexScreenInteractor;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexScreenPersistentRepository;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexScreenPersistentRepositoryStub;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexTaskApiService;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexTaskApiServiceStub;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexTaskConvertedRepository;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexTaskConvertedRepositoryStub;
import com.github.mvpstatelibexample.utils.LoggerCat;

import java.lang.ref.WeakReference;

/**
 * Created by grishberg on 22.04.17.
 * Мой велосипедный даггер
 */

public class MyServiceLocator {
    @Nullable
    private static WeakReference<ComplexScreenInteractor> complexScreenInteractorRef;

    @Nullable
    private static WeakReference<ComplexScreenPersistentRepository> complexScreenPersistentRepositoryRef;

    private MyServiceLocator() {
    }

    @MainThread
    public static ComplexScreenInteractor provideComplexScreenInteractor() {
        if (complexScreenInteractorRef == null || complexScreenInteractorRef.get() == null) {
            ComplexScreenInteractor interactor = getComplexScreenInteractor();
            complexScreenInteractorRef = new WeakReference<>(interactor);
            return interactor;
        }
        return complexScreenInteractorRef.get();
    }

    @NonNull
    private static ComplexScreenInteractor getComplexScreenInteractor() {
        return new ComplexScreenInteractor(provideComplexScreenPersistentRepository(),
                provideApiService(),
                getConvertedRepository(),
                new LoggerCat());
    }

    @NonNull
    private static ComplexTaskApiService provideApiService() {
        return new ComplexTaskApiServiceStub();
    }

    @NonNull
    public static ComplexScreenPersistentRepository provideComplexScreenPersistentRepository() {
        if (complexScreenPersistentRepositoryRef == null || complexScreenPersistentRepositoryRef.get() == null) {
            ComplexScreenPersistentRepository repository = getPersistentRepository();
            complexScreenPersistentRepositoryRef = new WeakReference<>(repository);
            return repository;
        }
        return complexScreenPersistentRepositoryRef.get();
    }

    @NonNull
    private static ComplexScreenPersistentRepository getPersistentRepository() {
        return new ComplexScreenPersistentRepositoryStub();
    }

    private static ComplexTaskConvertedRepository getConvertedRepository() {
        return new ComplexTaskConvertedRepositoryStub();
    }
}
