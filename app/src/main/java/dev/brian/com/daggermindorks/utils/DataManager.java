package dev.brian.com.daggermindorks.utils;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;

import dev.brian.com.daggermindorks.Model.User;
import dev.brian.com.daggermindorks.di.ApplicationContext;

public class DataManager {
    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefHelper mSharedPrefHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefHelper sharedPrefHelper){
        this.mContext = context;
        this.mDbHelper=dbHelper;
        this.mSharedPrefHelper = sharedPrefHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefHelper.put(SharedPrefHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken(){
        return mSharedPrefHelper.get(SharedPrefHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
