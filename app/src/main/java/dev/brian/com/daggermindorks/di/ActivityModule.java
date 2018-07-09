package dev.brian.com.daggermindorks.di;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }
    @Provides
    @ActivityContext
    Context provideContext(){
        return mActivity;
    }
    @Provides
    Activity provideActivity(){
        return mActivity;
    }
}
