package com.example.pokercardcal;

import android.os.Bundle;

import com.example.pokercardcal.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Mylog";
//    private long mExitTime;       //实现“再按一次退出”的记录时间变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        final HomeFragment home = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_home);
//        final Fragment notifications = (Fragment) getSupportFragmentManager().findFragmentById(R.id.navigation_notifications);
//        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        KeepStateNavigator navigator = new KeepStateNavigator(this, navHostFragment.getChildFragmentManager(), R.id.nav_host_fragment);
        navController.getNavigatorProvider().addNavigator(navigator);
        navController.setGraph(R.navigation.mobile_navigation);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // 恢复各种View的状态
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override //再按一次退出程序
    public void onBackPressed() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener(){
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
//            {
//                if(destination.getId() == R.id.navigation_home){
//
//                }else {
//                    MainActivity.super.onBackPressed();
//                }
//            }
//        });
        moveTaskToBack(false);

//        if (System.currentTimeMillis() - mExitTime < 2000) {
//            super.onBackPressed();
//        } else {
//            mExitTime = System.currentTimeMillis();
//            Toast.makeText(getBaseContext(),"再按一次返回键退出应用",Toast.LENGTH_LONG);
//        }
    }

}