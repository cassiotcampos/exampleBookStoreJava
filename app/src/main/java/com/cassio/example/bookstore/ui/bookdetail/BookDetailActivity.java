package com.cassio.example.bookstore.ui.bookdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.BookValidator;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterActivity;
import com.cassio.example.bookstore.ui.util.ImageUtil;
import com.google.gson.Gson;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookDetailActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_VIEW = 1;

    private BookDetail bookDetail;

    @Inject
    ImageUtil imageUtil;

    @BindView(R.id.detail_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        bookDetail = recoverFromIntentOrSavedInstance(savedInstanceState);

        // if not valid
        if (!BookValidator.validate(bookDetail)) {
            onBackPressed();
            return;
        }

        // valid
        loadContent();
        replaceFragment();
    }

    private BookDetail recoverFromIntentOrSavedInstance(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            if (getIntent().hasExtra(BookDetailFragment.ARG_BOOK_DETAIL)) {

                return new Gson().fromJson(
                        getIntent().getStringExtra(
                                BookDetailFragment.ARG_BOOK_DETAIL), BookDetail.class);
            }

        }else{

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


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container_handphone, fragment)
                .commit();

    }

    private void loadContent() {

        // load title on actiobar
        Objects.requireNonNull(getSupportActionBar()).setTitle(BookValidator.getTitle(bookDetail));




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}