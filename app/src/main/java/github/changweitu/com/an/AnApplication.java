package github.changweitu.com.an;

import android.app.Application;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import github.changweitu.com.an.model.DaoMaster;
import github.changweitu.com.an.model.DaoSession;
import github.changweitu.com.an.model.User;

/**
 * Created by vale on 7/16/16.
 */

public class AnApplication extends Application {
    public User user;
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;
    public static AnApplication shareApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        shareApplication = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "ruby-china-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
