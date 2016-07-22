package github.changweitu.com.an.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

import github.changweitu.com.an.model.NetworkEvent;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by vale on 7/18/16.
 */

public final class NetworkStatusUtil {
    private BroadcastReceiver netBroadcaseReceiver;
    private Context context;
    private static NetworkStatusUtil instance = null;

    public static NetworkStatusUtil getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkStatusUtil(context);
        }
        return instance;
    }
    public NetworkStatusUtil(Context context) {
        this.context = context;
    }
    public void startDetectNetworkStatus() {
        netBroadcaseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        EventBus.getDefault().post(new NetworkEvent(NetworkEvent.AVALIABLE));
                    } else {
                        EventBus.getDefault().post(new NetworkEvent(NetworkEvent.UNAVALIABLE));
                    }
                }
            }
        };
        context.registerReceiver(netBroadcaseReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void stopDetectNetworkStatus() {
        context.unregisterReceiver(netBroadcaseReceiver);
    }
}
