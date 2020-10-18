package com.cassio.example.bookstore.ui.bookmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.di.application.IApplicationComponent;
import com.cassio.example.bookstore.di.bookmaster.BookMasterModule;
import com.cassio.example.bookstore.di.bookmaster.DaggerIBookMasterComponent;
import com.cassio.example.bookstore.di.bookmaster.IBookMasterComponent;
import com.cassio.example.bookstore.model.BooksMasterValidator;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.base.BaseActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailFragment;
import com.cassio.example.bookstore.ui.util.ImageUtil;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookMasterActivity extends BaseActivity implements IBookMaster.IView {

    private static final String ARG_BOOK_ADAPTER_DATA = "ARG_BOOK_ADAPTER_DATA";

    @Inject
    ImageUtil imageUtil;

    @Inject
    IBookMaster.IPresenter presenter;

    @Inject
    IBookMaster.IMasterNavigation navigation;

    @Nullable
    @BindView(R.id.item_detail_container_two_panel)
    View containerTwoPanel;

    @BindView(R.id.recycler_view_books) RecyclerView recyclerViewBooks;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IBookMasterComponent bookMasterComponent;
    private IBookRowAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);

        if(savedInstanceState != null){
            getIntent().putExtras(savedInstanceState);
        }

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.recycler_view_books);
        setupRecyclerView((RecyclerView) recyclerView);

        presenter.attachView(this);

        if (savedInstanceState == null)
            presenter.loadBooks();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        BooksMaster booksMaster = new BooksMaster();

        // recover from savedInstanceState
        if(getIntent() != null && getIntent().getExtras() != null){
            String recoveredBookMasterStr = getIntent().getExtras()
                    .getString(ARG_BOOK_ADAPTER_DATA);

            BooksMaster recoveredBookMaster = new Gson().fromJson(recoveredBookMasterStr, BooksMaster.class);
            if(BooksMasterValidator.isBookListAvailable(recoveredBookMaster)){
                booksMaster = recoveredBookMaster;
            }
        }

        BookRowAdapter rowAdapter = new BookRowAdapter(booksMaster, isTwoPanel(), imageUtil);
        recyclerView.setAdapter(rowAdapter);
        adapter = rowAdapter;
        adapter.setCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void setupActivityComponent(IApplicationComponent applicationComponent) {

        bookMasterComponent = DaggerIBookMasterComponent.builder()
                .iApplicationComponent(applicationComponent)
                .bookMasterModule(new BookMasterModule(this))
                .build();

        bookMasterComponent.inject(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showBookList(BooksMaster booksMaster) {
        adapter.addMoreBooks(booksMaster);
    }

    @Override
    public void showApiErrorTryAgain() {

    }

    @Override
    public boolean isTwoPanel() {
        return containerTwoPanel != null;
    }


    @Override
    public void onLoadMoreBooksUnavailable(String msg) {

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

    @Override
    public void lastBookBinded() {
        presenter.loadMoreBooks();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_BOOK_ADAPTER_DATA, BooksMasterValidator.getAsJson(adapter.getBookMaster()));
    }
}