package com.cassio.example.bookstore.ui.bookmaster;



import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.base.IBasePresenter;
import com.cassio.example.bookstore.ui.base.IBaseView;

public interface IBookMaster {

    interface IMasterNavigation {

        void goToBookDetail(BookDetail bookDetail);
    }

    interface IView extends IBaseView, BookRowCallback {

        void showProgress();

        void hideProgress();

        void showBookList(BooksMaster booksMaster);

        void showApiErrorTryAgain();

        boolean isTwoPanel();
    }

    interface IPresenter extends IBasePresenter<IView> {

        void loadBooks();

        void clickBook(BookDetail book);

        void loadMoreBooks();

        void saveInstanceStateForOrientationChanges(BooksMaster booksMaster);
        BooksMaster restoreInstanceStateForOrientationChanges();
    }

}
