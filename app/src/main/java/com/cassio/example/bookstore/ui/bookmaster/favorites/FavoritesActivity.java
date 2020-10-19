package com.cassio.example.bookstore.ui.bookmaster.favorites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.bookmaster.base.BaseBookMasterActivity;

import javax.inject.Inject;

/**
 * Created by Cassio Ribeiro on 10/19/2020
 */
public class FavoritesActivity extends BaseBookMasterActivity  implements FavoritesContract.View{

    @Inject
    protected FavoritesContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null)
            presenter.loadFavoritesFromSharedP();

        toolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.my_favorites));
        getSupportActionBar().setIcon(
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.ic_action_fav,
                        getTheme()
                )
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        menu.findItem(R.id.action_favorite).setVisible(false);
        return true;
    }

    @Override
    public void showBookList(BooksMaster booksMaster) {
        adapter.addMoreBooks(booksMaster);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void lastBookBinded() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    public void reloadBooks() {
        setupRecyclerView();
        presenter.loadFavoritesFromSharedP();
    }
}
