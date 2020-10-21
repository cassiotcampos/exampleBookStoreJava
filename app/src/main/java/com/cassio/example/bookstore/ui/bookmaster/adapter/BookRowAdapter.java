package com.cassio.example.bookstore.ui.bookmaster.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.di.glide.ImageConstants;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.model.validator.BookValidator;
import com.cassio.example.bookstore.model.validator.BooksMasterValidator;
import com.cassio.example.bookstore.ui.util.GlideImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookRowAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements BookRowAdapterContract {

    private static final int TYPE_HEADER = -1;
    private static final int TYPE_FOOTER = -2;

    private static final int FOOTER_NONE = 0;
    private static final int FOOTER_LOADING = 1;
    private static final int FOOTER_TRY_AGAIN = 2;
    private static final int FOOTER_END = 3;

    private BooksMaster booksMaster;
    private boolean isTwoPanel;
    private BookRowCallback bookRowCallback;
    private GlideImageUtils glideImageUtils;

    private int showFooter = FOOTER_NONE;
    private boolean showHeader = false;


    public BookRowAdapter(BooksMaster booksMaster,
                          boolean isTwoPanel,
                          GlideImageUtils glideImageUtils,
                          BookRowCallback bookRowCallback) {

        if(booksMaster.getTotalItems() > 0) showHeader = true;
        this.booksMaster = booksMaster;
        this.isTwoPanel = isTwoPanel;
        this.glideImageUtils = glideImageUtils;
        this.bookRowCallback = bookRowCallback;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book_row_header, parent, false));
        } else if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book_row_footer, parent, false));
        } else {
            return new BookViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book_row, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewHolderType = getItemViewType(position);
        if (viewHolderType == TYPE_HEADER) {
            onBindViewHolderHeader((HeaderViewHolder) holder);
        } else if (viewHolderType == TYPE_FOOTER) {
            onBindViewHolderFooter((FooterViewHolder) holder);
        } else {
            onBindViewHolderBook((BookViewHolder) holder, position - 1);
        }

        if (position == getLastPosition() -1 && position > 1) {
            bookRowCallback.lastBookBinded();
        }
    }

    private void onBindViewHolderHeader(HeaderViewHolder holder) {
        if (showHeader && new BooksMasterValidator(booksMaster).isBookListAvailable()) {
            if (holder.itemView.getVisibility() != View.VISIBLE)
                holder.itemView.setVisibility(View.VISIBLE);

            holder.tvHeader.setText("Total de Livros: ".concat(String.valueOf(booksMaster.getTotalItems())));
        }else{
            if (holder.itemView.getVisibility() != View.GONE)
                holder.itemView.setVisibility(View.GONE);
        }
    }


    private void onBindViewHolderFooter(FooterViewHolder holder) {
        if(showFooter == FOOTER_NONE) {
            if (holder.itemView.getVisibility() != View.GONE)
                holder.itemView.setVisibility(View.GONE);
            return;
        }

        if (holder.itemView.getVisibility() != View.VISIBLE)
            holder.itemView.setVisibility(View.VISIBLE);

        if (showFooter == FOOTER_LOADING) {

            holder.vLoading.setVisibility(View.VISIBLE);
            holder.vNoMoreResults.setVisibility(View.GONE);
            holder.vTryAgain.setVisibility(View.GONE);

        }else if(showFooter == FOOTER_TRY_AGAIN){

            holder.vLoading.setVisibility(View.GONE);
            holder.vNoMoreResults.setVisibility(View.GONE);
            holder.vTryAgain.setVisibility(View.VISIBLE);
            holder.vTryAgain.setOnClickListener(view -> {
                bookRowCallback.lastBookBinded();
            });

        }else if(showFooter == FOOTER_END){

            holder.vLoading.setVisibility(View.GONE);
            holder.vNoMoreResults.setVisibility(View.VISIBLE);
            holder.vTryAgain.setVisibility(View.GONE);
        }
    }


    private void onBindViewHolderBook(BookViewHolder holder, int position) {
        BookDetail mBookDetail = booksMaster.getBooks().get(position);
        BookValidator validator = new BookValidator(mBookDetail);

        holder.bookTitle.setText(validator.getTitle());
        if (validator.hasValidImg()) {
            glideImageUtils.loadThumAndBgWithOneRequest(validator.getValidImgUrl(), holder.imgThumb, holder.imgBg);
        } else {
            holder.imgThumb.setImageResource(ImageConstants.DEFAULT_ERROR_PLACEHOLDER_WHITE);
            holder.imgThumb.setBackground(null);
        }

        holder.btnContainer.setOnClickListener(view -> {
            bookRowCallback.onBookClicked(mBookDetail);
        });
    }

    @Override
    public int getItemCount() {
        return booksMaster.getBooks() == null ? 2 : booksMaster.getBooks().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        if (position == getLastPosition()) return TYPE_FOOTER;
        return position;
    }

    @Override
    public void addMoreBooks(BooksMaster booksMaster) {

        int positionStart = booksMaster.getBooks().size() + this.booksMaster.getBooks().size();
        int itensAddedCount = booksMaster.getBooks().size();

        this.booksMaster.getBooks().addAll(booksMaster.getBooks());

        notifyItemRangeInserted(positionStart, itensAddedCount);

        if(this.booksMaster.getTotalItems() == 0){
            this.booksMaster.setTotalItems(booksMaster.getTotalItems());
            showHeader = true;
            notifyItemChanged(0);
        }
    }

    @Override
    public void removeBook(BookDetail bookToBeRemoved) {

    }

    @Override
    public BooksMaster getBookMaster() {
        return booksMaster;
    }

    @Override
    public int getLastPosition() {
        return booksMaster.getBooks().size() + 1;
    }

    @Override
    public void showProgress() {
        showFooter = FOOTER_LOADING;
        notifyItemChanged(getLastPosition());
    }

    @Override
    public void hideProgress() {
        showFooter = FOOTER_NONE;
        notifyItemChanged(getLastPosition());
    }

    @Override
    public void showErrorTryAgain() {
        showFooter = FOOTER_TRY_AGAIN;
        notifyItemChanged(getLastPosition());
    }

    @Override
    public void showNoMoreResults() {
        showFooter = FOOTER_END;
        notifyItemChanged(getLastPosition());
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgbg)
        ImageView imgBg;

        @BindView(R.id.imgmini)
        ImageView imgThumb;

        @BindView(R.id.book_title)
        TextView bookTitle;

        @BindView(R.id.container_book_button)
        View btnContainer;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_book_row_header_text)
        TextView tvHeader;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_book_row_footer_end_of_list)
        View vNoMoreResults;

        @BindView(R.id.item_book_row_footer_loading)
        View vLoading;

        @BindView(R.id.item_book_row_footer_try_again)
        View vTryAgain;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
