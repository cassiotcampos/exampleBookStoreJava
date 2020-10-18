package com.cassio.example.bookstore.ui.bookdetail;

import com.cassio.example.bookstore.ui.base.IBasePresenter;
import com.cassio.example.bookstore.ui.base.IBaseView;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface IBookDetail {

    interface IView extends IBaseView{

    }

    interface Presenter extends IBasePresenter<IView> {

    }
}
