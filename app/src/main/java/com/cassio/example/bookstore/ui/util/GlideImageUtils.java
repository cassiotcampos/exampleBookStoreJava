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
import com.cassio.example.bookstore.di.glide.ImageConstants;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class GlideImageUtils {

    private final RequestManager defaultRequestManager;


    public GlideImageUtils(RequestManager defaultRequestManager) {
        this.defaultRequestManager = defaultRequestManager;
    }

    public void loadThumAndBgWithOneRequest(String url,
                                            ImageView ivThumb,
                                            ImageView ivBg) {

        loadThumAndBgWithOneRequest(
                url,
                ivThumb,
                ivBg,
                null,
                null,
                true);

    }

    public void loadThumAndBgWithOneRequest(String url,
                                            ImageView ivThumb,
                                            ImageView ivBg,
                                            Priority priority,
                                            DiskCacheStrategy diskCacheStrategy,
                                            boolean withTransition) {


        RequestBuilder<Bitmap> requestBuilder = this.defaultRequestManager
                .asBitmap()
                .load(url);

        RequestOptions requestOptions = new RequestOptions();
        if (priority != null) requestOptions.priority(priority);
        if (diskCacheStrategy != null) requestOptions.diskCacheStrategy(diskCacheStrategy);
        if (withTransition) requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade());


        requestBuilder.apply(requestOptions)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        tryTransitionOrLoadDefault(transition, resource, ivThumb);
                        tryTransitionOrLoadDefault(transition, scaleBitmap(resource), ivBg);

                        ivThumb.setMinimumHeight(0);
                        ivThumb.setMinimumWidth(0);
                    }
                });
    }


    public void loadImg(String url,
                        ImageView ivTarget) {

        loadImg(url,
                ivTarget,
                null,
                null,
                false,
                false);

    }


    public void loadImg(String url,
                        ImageView ivTarget,
                        Priority priority,
                        DiskCacheStrategy diskCacheStrategy,
                        boolean withTransition,
                        boolean withBlur) {

        RequestBuilder<Bitmap> requestBuilder = this.defaultRequestManager
                .asBitmap()
                .load(url);

        RequestOptions requestOptions = new RequestOptions();
        if (priority != null) requestOptions.priority(priority);
        if (diskCacheStrategy != null) requestOptions.diskCacheStrategy(diskCacheStrategy);
        if (withTransition) requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade());


        requestBuilder.apply(requestOptions)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap res = withBlur ? scaleBitmap(resource) : resource;
                        tryTransitionOrLoadDefault(transition, res, ivTarget);
                    }
                });
    }


    private Bitmap scaleBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(
                bitmap,
                ImageConstants.BLUR_SCALE_WIDTH,
                ImageConstants.BLUR_SCALE_HEIGHT,
                true);
    }

    private void tryTransitionOrLoadDefault(Transition<? super Bitmap> transition, Bitmap resource, ImageView target) {

        if (transition != null
                && !transition.transition(resource, new BitmapImageViewTarget(target))) {
            target.setImageBitmap(resource);
        }
    }
}
