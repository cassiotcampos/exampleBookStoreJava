package com.cassio.example.bookstore.ui.bookmaster.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.ui.base.BaseActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailFragment;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowAdapterContract;
import com.cassio.example.bookstore.ui.util.GlideImageUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseBookMasterActivity extends BaseActivity implements BaseContract.View {

    private static final String ARG_BOOK_ADAPTER_DATA = "ARG_BOOK_ADAPTER_DATA";

    @Inject
    protected GlideImageUtils glideImageUtils;

    @Nullable
    @BindView(R.id.item_detail_container_two_panel)
    android.view.View containerTwoPanel;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.recycler_view_books)
    protected RecyclerView rvBooks;

    protected BookRowAdapterContract adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean isTwoPanel() {
        return containerTwoPanel != null;
    }

    @Override
    public void onBookClicked(BookDetail bookDetail) {
        if (isTwoPanel()) {

            // start fragment
            Fragment fragment = new BookDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BookDetailFragment.ARG_BOOK_DETAIL, new Gson().toJson(bookDetail));
            fragment.setArguments(bundle);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.item_detail_container_two_panel, fragment)
                    .commit();

        } else {

            // start activity
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(BookDetailFragment.ARG_BOOK_DETAIL, new Gson().toJson(bookDetail));
            startActivityForResult(intent, BookDetailActivity.REQUEST_CODE_VIEW);
        }
    }
}