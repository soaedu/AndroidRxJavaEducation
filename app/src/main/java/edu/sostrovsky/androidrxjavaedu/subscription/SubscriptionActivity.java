package edu.sostrovsky.androidrxjavaedu.subscription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

import edu.sostrovsky.androidrxjavaedu.R;

public class SubscriptionActivity extends AppCompatActivity implements MainInterface.View,
        SubscriptionInterface.View {

    private final String TAG = "MyObservable";
    private SubscriptionInterface.Presenter subscriptionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        subscriptionPresenter = new SubscriptionPresenter(this);
        subscriptionPresenter.init();
    }

    @Override
    public void log(String msg) {
        Log.e(TAG, msg);
    }


    @Override
    public void subscribe(final Observable observable, final Observer observer,
                          final int observerId, long delayMillis) {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer" +observerId+ " subscribe");
                Subscription subscription = observable.subscribe(observer);
                subscriptionPresenter.saveSubscription(subscription, observerId);
            }
        }, delayMillis);
    }

    @Override
    public void unsubscribe(final Subscription subscription, long delayMillis) {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("unsubscribe");
                subscription.unsubscribe();
            }
        }, delayMillis);
    }

    @Override
    public void unsubscribe(final Subscription subscription, final int observerId,
                            long delayMillis) {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer" +observerId+ " unsubscribe");
                subscription.unsubscribe();
                subscriptionPresenter.onUnsubscribed(observerId);
            }
        }, delayMillis);
    }
}