package dev.brian.com.daggermindorks.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {
}
/*@DatabaseInfo is to provide tha database name into the class dependency,
* Since a String class is already provided as a dependency, it's always good
* to qualify it so that Dagger can explicitly resolve it. */
