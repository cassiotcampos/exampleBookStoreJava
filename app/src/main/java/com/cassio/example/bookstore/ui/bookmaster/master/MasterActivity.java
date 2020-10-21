package com.cassio.example.bookstore.ui.bookmaster.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowAdapter;
import com.cassio.example.bookstore.ui.bookmaster.base.BaseBookMasterActivity;
import com.cassio.example.bookstore.ui.bookmaster.favorites.FavoritesActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Cassio Ribeiro on 10/19/2020
 */
public class MasterActivity extends BaseBookMasterActivity implements MasterContract.View {

    @Inject
    protected MasterContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if(savedInstanceState != null){
            getIntent().putExtras(savedInstanceState);
        }

        presenter.loadBooksOnCreate(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        menu.findItem(R.id.action_favorite).setVisible(presenter.hasFavorites());
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    protected void setupRecyclerView(BooksMaster booksMaster) {

        // header and footer items spans 1 column
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0) return 2;
                if(position == adapter.getLastPosition()) return 2;
                return 1;
            }
        });

        rvBooks.setLayoutManager(gridLayoutManager);

        BookRowAdapter rowAdapter = new BookRowAdapter(booksMaster, isTwoPanel(), glideImageUtils, this);
        rvBooks.setAdapter(rowAdapter);
        adapter = rowAdapter;
    }

    @Override
    public void lastBookBinded() {
        rvBooks.post(() -> adapter.showProgress());
        presenter.loadMoreBooks();
    }

    @Override
    public void showProgress() {
        rvBooks.post(() -> adapter.showProgress());
    }

    @Override
    public void hideProgress() {
        rvBooks.post(() -> adapter.hideProgress());
    }

    @Override
    public void showBookList(BooksMaster booksMaster) {
        if(adapter == null){
            setupRecyclerView(booksMaster);
        }else {
            rvBooks.post(() -> adapter.addMoreBooks(booksMaster));
        }
    }

    @Override
    public void showApiErrorTryAgain() {
        adapter.showErrorTryAgain();
    }

    @Override
    public void showNoMoreResults() {
        adapter.showNoMoreResults();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_favorite){
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        presenter.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
