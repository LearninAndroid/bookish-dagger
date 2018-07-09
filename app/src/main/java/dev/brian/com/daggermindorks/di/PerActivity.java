package dev.brian.com.daggermindorks.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

/*The qualifier annotation is provides by the javax inject package and is
* used to qualify the dependency. For instance, a class can ask both, an
* application context and an activity context. but both objects will be
* of type Context, so for dagger2 to figure out which variable is to be
* provided with what, we have to explicitly specify the idenfier for it.*/

/*The @Scope annotation is used to specify the scope in which a dependency
* object persists. If a class getting dependencies, has members injected with
* classes annotated with a scope, then each instance of that class asking for
* dependencies will get its own set of member variables.*/

