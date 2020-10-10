package edu.sostrovsky.androidrxjavaedu.subscription;

public interface MainInterface {
    interface Presenter {
        void init();
    }

    interface View {
        void log(String msg);
    }
}
