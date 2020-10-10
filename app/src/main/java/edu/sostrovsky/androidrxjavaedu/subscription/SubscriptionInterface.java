package edu.sostrovsky.androidrxjavaedu.subscription;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public interface SubscriptionInterface {
    interface Presenter extends MainInterface.Presenter {
        void saveSubscription(Subscription subscription, int observerId);
        void onUnsubscribed(int observerId);
    }

    interface View extends MainInterface.View {
        void subscribe(Observable observable, Observer observer, int observerId, long delayMillis);
        void unsubscribe(final Subscription subscription, long delayMillis);
        void unsubscribe(final Subscription subscription, int observerId, long delayMillis);
    }
}
