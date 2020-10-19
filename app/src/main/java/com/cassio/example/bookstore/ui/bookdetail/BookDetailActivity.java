package com.cassio.example.bookstore.ui.bookdetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.validator.BookValidator;
import com.cassio.example.bookstore.ui.base.BaseActivity;
import com.cassio.example.bookstore.ui.util.GlideImageUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookDetailActivity extends BaseActivity {

    public static final int REQUEST_CODE_VIEW = 1;

    private BookDetail bookDetail;

    @Inject
    GlideImageUtils glideImageUtils;

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        bookDetail = recoverFromIntentOrSavedInstance(savedInstanceState);

        if (!new BookValidator(bookDetail).validate()) {
            onBackPressed();
        } else {
            replaceFragment();
        }
    }

    private BookDetail recoverFromIntentOrSavedInstance(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            if (getIntent().hasExtra(BookDetailFragment.ARG_BOOK_DETAIL)) {

                return new Gson().fromJson(
                        getIntent().getStringExtra(
                                BookDetailFragment.ARG_BOOK_DETAIL), BookDetail.class);
            }
        } else {

            return new Gson().fromJson(
                    savedInstanceState.getString(
                            BookDetailFragment.ARG_BOOK_DETAIL), BookDetail.class);
        }

        return null;
    }

    private void replaceFragment() {

        Fragment fragment = new BookDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BookDetailFragment.ARG_BOOK_DETAIL, getIntent().getStringExtra(BookDetailFragment.ARG_BOOK_DETAIL));
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container_handphone, fragment)
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void loadToolbarBg(String validatedUrl) {
        ImageView toolbarBg = findViewById(R.id.img_toolbar_bg);
        if (toolbarBg != null) {
            glideImageUtils.loadImg(validatedUrl, toolbarBg, null, null, false, true);
        }
    }

}