package com.cassio.example.bookstore.di.glide;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.cassio.example.bookstore.ui.util.ImageUtil;

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
        return RequestOptions
                .placeholderOf(GlideConstants.DEFAULT_PLACEHOLDER)
                .error(GlideConstants.DEFAULT_ERROR_PLACEHOLDER)
                .priority(GlideConstants.DEFAULT_PRIORITY)
                .diskCacheStrategy(GlideConstants.DEFAULT_DISK_CACHE_STRATEGY);
    }

    @Singleton
    @Provides
    public RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    public ImageUtil providesImageUtil(RequestManager requestManager) {
        return new ImageUtil(requestManager);
    }

}
