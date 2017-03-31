package com.fuxia.w.view9;

import rx.Subscriber;

/**
 * Created by Bob on 2017/1/3.
 */

public class MySubscriber extends Subscriber {

    private SubscriberOnNextListener mSubscriberOnNextListener;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Object o) {
    }

    public interface SubscriberOnNextListener<T> {
        void onNext(T t);
    }
}
