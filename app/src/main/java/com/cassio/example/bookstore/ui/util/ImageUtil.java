package com.cassio.example.bookstore.ui.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cassio.example.bookstore.di.glide.GlideConstants;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class ImageUtil {

    private final RequestManager defaultRequestManager;


    public ImageUtil(RequestManager defaultRequestManager) {
        this.defaultRequestManager = defaultRequestManager;
    }

    public void loadThumAndBg(String url,
                                     ImageView ivThumb,
                                     ImageView ivBg) {

        loadThumAndBg(
                url,
                ivThumb,
                ivBg,
                null,
                null,
                true);

    }


    public void loadImg(String url,
                               ImageView ivTarger) {


    }


    public void loadThumAndBg(String url,
                              ImageView ivThumb,
                              ImageView ivBg,
                              Priority priority,
                              DiskCacheStrategy diskCacheStrategy,
                              boolean withTransition) {

        RequestOptions requestOptions = new RequestOptions();

        RequestBuilder requestBuilder = this.defaultRequestManager
                .asBitmap()
                .load(url);

        if(priority != null) requestOptions.priority(priority);
        if(diskCacheStrategy != null)    requestOptions.diskCacheStrategy(diskCacheStrategy);
        if(withTransition) requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade());

        requestBuilder.apply(requestOptions);


        requestBuilder.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                tryTransitionOrLoadDefault(transition, resource, ivThumb);
                tryTransitionOrLoadDefault(transition, scaleBitmap(resource), ivBg);
            }
        });
    }

    private void tryTransitionOrLoadDefault(Transition<? super Bitmap> transition, Bitmap resource, ImageView target) {

        if (transition != null
                && !transition.transition(resource, new BitmapImageViewTarget(target))) {
            target.setImageBitmap(resource);
        }
    }


    public void loadImg(String url,
                               ImageView ivTarger,
                               Priority priority,
                               DiskCacheStrategy diskCacheStrategy,
                               boolean withTransition,
                               boolean withBlurr) {


    }


    private Bitmap scaleBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(
                bitmap,
                GlideConstants.BLUR_SCALE_WIDTH,
                GlideConstants.BLUR_SCALE_HEIGHT,
                true);
    }
}
