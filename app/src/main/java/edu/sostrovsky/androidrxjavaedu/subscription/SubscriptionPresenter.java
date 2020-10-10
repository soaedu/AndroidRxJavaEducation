package edu.sostrovsky.androidrxjavaedu.subscription;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SubscriptionPresenter extends BasePresenter implements
        SubscriptionInterface.Presenter {
    private Subscription subscription;
    private HashMap<Integer, Subscription> subscriptionMap = new HashMap<>();

    public SubscriptionPresenter(SubscriptionInterface.View view) {
        super(view);
    }

    @Override
    public void init() {
        // unsubscribeAfterDelay();
        // setCompositeSubscription();
        // setOwnObservable();
        // setColdObservable();
        // setHotObservable();
        // setRefCountHotObservable();
        // setCacheHotObservable();
    }

    // ----------------------------------------------------------- //

    private void unsubscribeAfterDelay() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Subscription subscription = observable.subscribe(getAction());
        unsubscribe(subscription,4500);
    }

    // ----------------------------------------------------------- //

    private void setCompositeSubscription() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        Subscription subscription1 = observable.subscribe(getAction());
        Subscription subscription2 = observable.subscribe(getAction());

        CompositeSubscription compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription1);
        compositeSubscription.add(subscription2);

        showMsg("subscription1 is unsubscribed " + subscription1.isUnsubscribed());
        showMsg("subscription2 is unsubscribed " + subscription2.isUnsubscribed());

        showMsg("unsubscribe CompositeSubscription");
        compositeSubscription.unsubscribe();

        showMsg("subscription1 is unsubscribed " + subscription1.isUnsubscribed());
        showMsg("subscription2 is unsubscribed " + subscription2.isUnsubscribed());
    }

    // ----------------------------------------------------------- //

    private void setOwnObservable() {
        Observable.OnSubscribe<Integer> onSubscribe = subscriber -> {
            if (canSendData()) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (canSendData()) {
                        subscriber.onNext(i);
                    } else {
                        break;
                    }
                }

                if (canSendData()) {
                    subscriber.onCompleted();
                }
            }
        };

        Observable<Integer> observable = Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io());

        Subscription subscription = observable.subscribe(getObserver());
        unsubscribe(subscription, 4500);
    }

    private boolean canSendData() {
        return !subscription.isUnsubscribed();
    }

    // ----------------------------------------------------------- //

    private void setColdObservable() {
        showMsg("observable create");
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(5);

        int observerId = (subscriptionMap.size() + 1);
        subscribe(observable, getObserver(observerId), observerId, 3000);

        observerId++;
        subscribe(observable, getObserver(observerId), observerId, 5500);
    }

    // ----------------------------------------------------------- //

    private void setHotObservable() {
        ConnectableObservable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
                // .publish();
                .replay();

//        int observerId = 1;
//        subscribe(observable, getObserver(observerId), observerId, 0);

        showMsg("observable connect");
        observable.connect();

        int observerId = 1;
        subscribe(observable, getObserver(observerId), observerId, 2500);

        observerId++;
        subscribe(observable, getObserver(observerId), observerId, 4500);
    }

    // ----------------------------------------------------------- //

    private void setRefCountHotObservable() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
                .publish()
                .refCount();

        int observerId = 1;
        subscribe(observable, getObserver(observerId), observerId, 1500);

        observerId++;
        subscribe(observable, getObserver(observerId), observerId, 3000);
    }

    // ----------------------------------------------------------- //

    private void setCacheHotObservable() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .cache();

        int observerId = 1;
        subscribe(observable, getObserver(observerId), observerId, 1500);

        observerId++;
        subscribe(observable, getObserver(observerId), observerId, 4000);
    }

    // ----------------------------------------------------------- //

    private void subscribe(Observable observable, Observer observer, int observerId,
                                   long delay) {
        if (view != null) {
            ((SubscriptionInterface.View) view).subscribe(observable, observer, observerId, delay);
        }
    }

    private void unsubscribe(Subscription subscription, long delay) {
        if (view != null && subscription != null) {
            ((SubscriptionInterface.View) view).unsubscribe(subscription, delay);
        }
    }

    private void unsubscribe(Subscription subscription, int observerId, long delay) {
        if (view != null && subscription != null) {
            ((SubscriptionInterface.View) view).unsubscribe(subscription, observerId, delay);
        }
    }

    // ----------------------------------------------------------- //

    @Override
    public void saveSubscription(Subscription subscription, int observerId) {
        if (subscription != null) {
            subscriptionMap.put(observerId, subscription);
        }

        switch(observerId) {
            case 1:
                // unsubscribe(subscriptionMap.get(1), observerId,5000);
                unsubscribe(subscriptionMap.get(1), observerId,5500);
                break;
            case 2:
                // unsubscribe(subscriptionMap.get(2), observerId, 6000);
                unsubscribe(subscriptionMap.get(2), observerId, 6500);
                break;
        }
    }

    @Override
    public void onUnsubscribed(int observerId) {
        switch(observerId) {
            case 1:
                // setRefCountHotObservable();
                setCacheHotObservable();
                break;
        }
    }

}
