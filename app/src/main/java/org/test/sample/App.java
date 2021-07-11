package org.test.sample;

import android.app.Application;

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        //getHashKey();

        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }
}
