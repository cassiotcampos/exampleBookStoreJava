package com.cassio.example.bookstore.ui.bookmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cassio.example.bookstore.R;
import com.cassio.example.bookstore.model.BookValidator;
import com.cassio.example.bookstore.model.BooksMasterValidator;
import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookRowAdapter
        extends RecyclerView.Adapter<BookRowAdapter.BookViewHolder>
        implements IBookRowAdapter {

    private BooksMaster booksMaster;
    private boolean isTwoPanel;
    private BookRowCallback bookRowCallback;
    private ImageUtil imageUtil;


    public BookRowAdapter(BooksMaster booksMaster, boolean isTwoPanel, ImageUtil imageUtil) {
        BooksMasterValidator.initBooksWithEmptyList(booksMaster);
        this.booksMaster = booksMaster;
        this.isTwoPanel = isTwoPanel;
        this.imageUtil = imageUtil;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        if(position == this.booksMaster.getBooks().size() - 1){
            bookRowCallback.lastBookBinded();
        }

        BookDetail mBookDetail = booksMaster.getBooks().get(position);

        holder.bookTitle.setText(BookValidator.getTitle(mBookDetail));
        imageUtil.loadThumAndBg(BookValidator.getImgUrl(mBookDetail), holder.imgThumb, holder.imgBg);

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

        if(!BooksMasterValidator.isBookListAvailable(booksMaster)){
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
    public void setCallback(BookRowCallback bookRowCallback) {
        this.bookRowCallback = bookRowCallback;
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
