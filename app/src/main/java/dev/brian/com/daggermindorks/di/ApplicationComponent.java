package dev.brian.com.daggermindorks.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dev.brian.com.daggermindorks.Mindorks;
import dev.brian.com.daggermindorks.utils.DataManager;
import dev.brian.com.daggermindorks.utils.DbHelper;
import dev.brian.com.daggermindorks.utils.SharedPrefHelper;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Mindorks mindorks);

    @ApplicationContext
    Context getContext();

    Application getApplication();
    DataManager getDataManager();

    SharedPrefHelper getPreferenceHelper();

    DbHelper getDbHelper();
}
