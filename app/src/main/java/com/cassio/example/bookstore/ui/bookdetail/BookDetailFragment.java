package com.cassio.example.bookstore.ui.bookdetail;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class BookDetailFragment extends Fragment {

    public static final String ARG_BOOK_DETAIL = "ARG_BOOK_DETAIL";

    private BookDetail bookDetail;

    public BookDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("teste");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_fragment_detail, container, false);

        return rootView;
    }
}