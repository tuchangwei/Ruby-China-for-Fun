package github.changweitu.com.an;

import android.app.Application;

import github.changweitu.com.an.model.User;

/**
 * Created by vale on 7/16/16.
 */

public class AnApplication extends Application {
    public User user;
    public static AnApplication shareApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        shareApplication = this;
    }

}
