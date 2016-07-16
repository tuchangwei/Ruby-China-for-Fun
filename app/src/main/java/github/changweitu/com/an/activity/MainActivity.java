package github.changweitu.com.an.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.changweitu.com.an.AnApplication;
import github.changweitu.com.an.R;
import github.changweitu.com.an.fragment.MainFragment;
import github.changweitu.com.an.model.NetworkEvent;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;
    private BroadcastReceiver netBroadcaseReceiver;
    private MenuItem selectedMenuItem;

    public static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.app_name,R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, new MainFragment());
        transaction.commit();

        netBroadcaseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
                   ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                   NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
                   if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                       EventBus.getDefault().post(new NetworkEvent(NetworkEvent.AVALIABLE));
                   } else {
                       EventBus.getDefault().post(new NetworkEvent(NetworkEvent.UNAVALIABLE));
                   }
               }
            }
        };
        registerReceiver(netBroadcaseReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        selectedMenuItem = navigationView.getMenu().findItem(R.id.theme);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (selectedMenuItem.getItemId() != item.getItemId()) {
                    item.setChecked(true);
                    selectedMenuItem.setChecked(false);
                    selectedMenuItem = item;

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetworkEvent event) {
        if (event.getType()==NetworkEvent.UNAVALIABLE) {
            MaterialDialog dialog = new MaterialDialog.Builder(this)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .title("没有连接网络")
                    .content("是否开启网络连接?")
                    .positiveText("好的")
                    .negativeText("不用")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .build();
            dialog.show();
        }
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netBroadcaseReceiver);
    }

    public void onHeaderViewPressed(View view) {
        if (AnApplication.shareApplication.user.isLoggedIn()) return;
        startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE);
    }
}
