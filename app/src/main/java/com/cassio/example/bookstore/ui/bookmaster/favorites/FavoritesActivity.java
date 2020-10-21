package com.cassio.example.bookstore.ui.bookmaster.favorites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowAdapter;
import com.cassio.example.bookstore.ui.bookmaster.base.BaseBookMasterActivity;

import javax.inject.Inject;

/**
 * Created by Cassio Ribeiro on 10/19/2020
 */
public class FavoritesActivity extends BaseBookMasterActivity implements FavoritesContract.View {

    @Inject
    protected FavoritesContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

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
        setupRecyclerView(booksMaster);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    public void reloadBooks() {
        presenter.loadFavoritesFromSharedP();
    }

    @Override
    public void lastBookBinded() {

    }
}
