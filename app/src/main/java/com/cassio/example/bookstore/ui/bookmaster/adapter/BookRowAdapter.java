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
        extends RecyclerView.Adapter<BookRowAdapter.BookViewHolder>
        implements BookRowAdapterContract {

    private BooksMaster booksMaster;
    private boolean isTwoPanel;
    private BookRowCallback bookRowCallback;
    private GlideImageUtils glideImageUtils;


    public BookRowAdapter(BooksMaster booksMaster,
                          boolean isTwoPanel,
                          GlideImageUtils glideImageUtils,
                          BookRowCallback bookRowCallback) {

        new BooksMasterValidator(booksMaster).initBooksWithEmptyList();
        this.booksMaster = booksMaster;
        this.isTwoPanel = isTwoPanel;
        this.glideImageUtils = glideImageUtils;
        this.bookRowCallback = bookRowCallback;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {


        if (position == this.booksMaster.getBooks().size() - 1) {
            bookRowCallback.lastBookBinded();
        }

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
        return booksMaster.getBooks() == null ? 0 : booksMaster.getBooks().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void addMoreBooks(BooksMaster booksMaster) {

        if (!new BooksMasterValidator(booksMaster).isBookListAvailable()) {
            bookRowCallback.onLoadMoreBooksUnavailable("Can't load more books");
            return;
        }

        int positionStart = booksMaster.getBooks().size() + this.booksMaster.getBooks().size();
        int itensAddedCount = booksMaster.getBooks().size();

        this.booksMaster.getBooks().addAll(booksMaster.getBooks());

        notifyItemRangeInserted(positionStart, itensAddedCount);
    }

    @Override
    public void removeBook(BookDetail bookToBeRemoved) {

    }

    @Override
    public BooksMaster getBookMaster() {
        return booksMaster;
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
}
