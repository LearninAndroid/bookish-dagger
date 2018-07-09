package dev.brian.com.daggermindorks;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import dev.brian.com.daggermindorks.Model.User;
import dev.brian.com.daggermindorks.di.ActivityComponent;
import dev.brian.com.daggermindorks.di.ActivityModule;
import dev.brian.com.daggermindorks.di.ApplicationModule;
import dev.brian.com.daggermindorks.di.DaggerActivityComponent;
import dev.brian.com.daggermindorks.di.DaggerApplicationComponent;
import dev.brian.com.daggermindorks.utils.DataManager;

public class MainActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;
    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    @Inject
    DataManager mDataManager;
    public ActivityComponent getActivityComponent(){
        if(activityComponent == null){
           activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).applicationComponent(Mindorks.get(this).getApplicationComponent()).build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        mTvUserInfo = (TextView) findViewById(R.id.tv_user_info);
        mTvAccessToken = (TextView) findViewById(R.id.tv_access_token);

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createUser();
        getUser();
        mDataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543");

        String token = mDataManager.getAccessToken();
        if(token != null){
            mTvAccessToken.setText(token);
        }
    }

    private void createUser(){
        try {
            mDataManager.createUser(new User("Ali", "1367, Gurgaon, Haryana, India"));
        }catch (Exception e){e.printStackTrace();}
    }

    private void getUser(){
        try {
            User user = mDataManager.getUser(1L);
            mTvUserInfo.setText(user.toString());
        }catch (Exception e){e.printStackTrace();}
    }
}
