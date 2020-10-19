package com.cassio.example.bookstore.di.glide;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.cassio.example.bookstore.ui.util.GlideImageUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class GlideModule {



    @Singleton
    @Provides
    public RequestOptions provideRequestOptions(){
        return new RequestOptions()
                .priority(ImageConstants.DEFAULT_PRIORITY)
                .diskCacheStrategy(ImageConstants.DEFAULT_DISK_CACHE_STRATEGY);
    }

    @Singleton
    @Provides
    public RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    public GlideImageUtils providesImageUtil(RequestManager requestManager) {
        return new GlideImageUtils(requestManager);
    }

}
