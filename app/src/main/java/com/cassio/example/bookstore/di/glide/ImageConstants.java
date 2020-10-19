package com.cassio.example.bookstore.di.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cassio.example.bookstore.R;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class ImageConstants {

    public static final Priority DEFAULT_PRIORITY = Priority.LOW;

    public static final DiskCacheStrategy DEFAULT_DISK_CACHE_STRATEGY = DiskCacheStrategy.ALL;

    public static final boolean DEFAULT_WITH_TRANSITION = false;

    public static final int DEFAULT_ERROR_PLACEHOLDER_BLACK = R.drawable.ic_no_image_black;

    public static final int DEFAULT_ERROR_PLACEHOLDER_WHITE = R.drawable.ic_no_image_white;

    public static final int BLUR_SCALE_WIDTH = 1;

    public static final int BLUR_SCALE_HEIGHT = 1;

}
