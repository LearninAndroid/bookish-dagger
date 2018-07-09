package dev.brian.com.daggermindorks.di;

import dagger.Component;
import dev.brian.com.daggermindorks.MainActivity;

@PerActivity
@Component(dependencies =  ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}

