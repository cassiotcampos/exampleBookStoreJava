package com.cassio.example.bookstore.ui.bookdetail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.di.glide.ImageConstants;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.validator.BookValidator;
import com.cassio.example.bookstore.ui.base.BaseFragment;
import com.cassio.example.bookstore.ui.bookmaster.favorites.FavoritesActivity;
import com.cassio.example.bookstore.ui.bookmaster.master.MasterActivity;
import com.cassio.example.bookstore.ui.util.GlideImageUtils;
import com.cassio.example.bookstore.ui.util.TextUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookDetailFragment extends BaseFragment implements BookDetailContract.View {

    public static final String ARG_BOOK_DETAIL = "ARG_BOOK_DETAIL";

    private BookDetail bookDetail;

    private Context context;

    @Inject
    BookDetailContract.Presenter bookDetailPresenter;

    @Inject
    GlideImageUtils glideImageUtils;

    @BindView(R.id.detail_title)
    TextView tvTitle;

    @BindView(R.id.container_details_authors)
    ViewGroup containderDetailsAuthors;

    @BindView(R.id.detail_authors)
    TextView tvAuthors;

    @BindView(R.id.tv_book_detail_description)
    TextView tvDesc;

    @BindView(R.id.container_book_buy_link)
    ViewGroup containerBookBuyLink;

    @BindView(R.id.detail_buylink)
    TextView tvBuyLink;

    @BindView(R.id.img_book_detail)
    ImageView ivThumb;

    @BindView(R.id.container_adicionar_favorito)
    ViewGroup containerAdicionarFavoritos;

    @BindView(R.id.tv_fav)
    TextView tvFav;

    @BindView(R.id.imb_add_fav)
    ImageView imbAddFav;

    private View.OnClickListener onClickFavorites = view -> {
        BookDetailFragment.this.onClickFavorites();
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_BOOK_DETAIL)) {

            String bookDetailStr = getArguments().getString(ARG_BOOK_DETAIL);
            bookDetail = new Gson().fromJson(bookDetailStr, BookDetail.class);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(new BookValidator(bookDetail).getTitle());
            }
        }
    }


    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View rootView = inflater.inflate(R.layout.item_fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookDetailPresenter.init(bookDetail);
        containerAdicionarFavoritos.setOnClickListener(onClickFavorites);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void onClickFavorites() {
        bookDetailPresenter.onClickFavorite();
        if(getActivity() instanceof MasterActivity){
            ((MasterActivity)getActivity()).invalidateOptionsMenu();
        }
    }


    @Override
    public void loadFavoriteButton(boolean isFavorite) {
        if (isFavorite) {
            containerAdicionarFavoritos.setBackground(ContextCompat.getDrawable(context,
                    R.drawable.my_ripple_fav_selected));

            tvFav.setText(getText(R.string.remove_favorite));
            tvFav.setTextColor(ContextCompat.getColor(context, R.color.white));
            imbAddFav.setColorFilter(
                    ContextCompat.getColor(context, R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN);

        } else {

            containerAdicionarFavoritos.setBackground(ContextCompat.getDrawable(context,
                    R.drawable.my_ripple_fav_normal));

            tvFav.setText(getText(R.string.add_favorite));
            tvFav.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            imbAddFav.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorRed),
                    android.graphics.PorterDuff.Mode.SRC_IN);

        }
    }

    @Override
    public void showTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void showTitleNotAvailable() {
        tvTitle.setText("");
    }

    @Override
    public void loadThumb(String validImgUrl) {
        glideImageUtils.loadImg(validImgUrl, ivThumb);
        if(getActivity() instanceof BookDetailActivity){
            ((BookDetailActivity)getActivity()).loadToolbarBg(validImgUrl);
        }
    }

    @Override
    public void loadThumDefault() {
        ivThumb.setImageDrawable(ContextCompat.getDrawable(context, ImageConstants.DEFAULT_ERROR_PLACEHOLDER_BLACK));
    }

    @Override
    public void showAuthors(String authorsFormated) {
        containderDetailsAuthors.setVisibility(View.VISIBLE);
        tvAuthors.setText(authorsFormated);
    }

    @Override
    public void hideAuthors() {
        containderDetailsAuthors.setVisibility(View.GONE);
    }

    @Override
    public void showBuyLink(String validBuyLink) {
        containderDetailsAuthors.setVisibility(View.VISIBLE);
        tvBuyLink.setText(TextUtils.getSpannedHtmlFromStrUnderline(validBuyLink));
    }

    @Override
    public void hideBuyLink() {
        containerBookBuyLink.setVisibility(View.GONE);
    }

    @Override
    public void showDescription(String descriptionOrSnippets) {
        tvDesc.setText(TextUtils.getSpannedHtmlFromStr(descriptionOrSnippets));
    }

    @Override
    public void refreshFavoritesActivity() {
        if(getActivity() instanceof FavoritesActivity){
            ((FavoritesActivity)getActivity()).reloadBooks();
        }
    }
}