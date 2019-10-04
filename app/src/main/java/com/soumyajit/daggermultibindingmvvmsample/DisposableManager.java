package com.soumyajit.daggermultibindingmvvmsample;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableManager {

    private static final String TAG = DisposableManager.class.getSimpleName();
    private static CompositeDisposable compositeDisposable;

    public static void add(Disposable disposable) {
        Log.e(TAG,"add");
        getCompositeDisposable().add(disposable);
    }

    public static void dispose() {
        Log.e(TAG,"dispose");
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    private DisposableManager() {}

}
