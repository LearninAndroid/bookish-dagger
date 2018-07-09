package dev.brian.com.daggermindorks;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dev.brian.com.daggermindorks.di.ApplicationComponent;
import dev.brian.com.daggermindorks.di.ApplicationModule;
import dev.brian.com.daggermindorks.di.DaggerApplicationComponent;
import dev.brian.com.daggermindorks.utils.DataManager;

public class Mindorks extends Application {
    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static Mindorks get(Context context){
        return (Mindorks) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
                new ApplicationModule(this)
        ).build();
        applicationComponent.inject(this);


    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
