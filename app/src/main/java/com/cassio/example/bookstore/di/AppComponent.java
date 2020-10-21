package com.cassio.example.bookstore.di;

import android.app.Application;

import com.cassio.example.bookstore.application.BookApplication;
import com.cassio.example.bookstore.di.glide.GlideModule;
import com.cassio.example.bookstore.di.retrofit.RetrofitModule;
import com.cassio.example.bookstore.di.retrofit.SharedPreferencesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuildersModule.class,
                GlideModule.class,
                RetrofitModule.class,
                SharedPreferencesModule.class
        }
)
public interface AppComponent extends AndroidInjector<BookApplication> {

    void inject(BookApplication app);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
        Builder setRetrofitModule(RetrofitModule retrofitModule); // used for tests
    }


}







