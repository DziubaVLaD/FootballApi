package com.test.footballapi.presentation.base;

import java.util.concurrent.LinkedBlockingQueue;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mvpView;
    private LinkedBlockingQueue<ViewAction<T>> postponedViewActions;

    private CompositeDisposable disposables;

    public BasePresenter() {
        disposables = new CompositeDisposable();
        postponedViewActions = new LinkedBlockingQueue<>();
    }

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
        sendPostponedActionsToView(this.mvpView);
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    @Override
    public void destroy() {
        if (disposables != null) {
            disposables.dispose();
        }
        postponedViewActions.clear();
    }

    public void sendToView(final ViewAction<T> action) {
        if (mvpView != null) {
            action.call(mvpView);
        } else {
            postponedViewActions.add(action);
        }
    }

    private void sendPostponedActionsToView(final T view) {
        while (!postponedViewActions.isEmpty()) {
            postponedViewActions.poll().call(view);
        }
    }

    public void removeDisposable(Disposable disposable) {
        disposables.remove(disposable);
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}