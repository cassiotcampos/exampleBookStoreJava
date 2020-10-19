package com.cassio.example.bookstore.ui.bookmaster.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.bookmaster.base.BaseBookMasterActivity;
import com.cassio.example.bookstore.ui.bookmaster.favorites.BookFavoritesActivity;

import javax.inject.Inject;

/**
 * Created by Cassio Ribeiro on 10/19/2020
 */
public class BookMasterActivity extends BaseBookMasterActivity implements BookMasterContract.View {

    @Inject
    protected BookMasterContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (savedInstanceState == null)
            presenter.loadBooksFromApi();

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

    @Override
    public void lastBookBinded() {
        presenter.loadMoreBooks();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_favorite){
            Intent intent = new Intent(this, BookFavoritesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}