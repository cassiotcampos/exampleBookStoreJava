package com.cassio.example.bookstore.ui.util;

import android.text.Spanned;

import androidx.core.text.HtmlCompat;

/**
 * Created by Cassio Ribeiro on 10/19/2020
 */
public abstract class TextUtils {

    public static Spanned getSpannedHtmlFromStr(String str){
        return HtmlCompat.fromHtml(str, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }
    public static Spanned getSpannedHtmlFromStrUnderline(String str){
        return HtmlCompat.fromHtml("<u>".concat(str).concat("</u>"), HtmlCompat.FROM_HTML_MODE_LEGACY);
    }
}
