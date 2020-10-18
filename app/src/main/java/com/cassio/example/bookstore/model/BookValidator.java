package com.cassio.example.bookstore.model;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BookValidator {

    static boolean isTitleAvailable(BookDetail bookDetail) {
        if (
                bookDetail != null
                        && bookDetail.getVolumeInfo() != null
                        && bookDetail.getVolumeInfo().getTitle() != null
                        && !bookDetail.getVolumeInfo().getTitle().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }

    static boolean getDescription(BooksMaster booksMaster) {
        if (
                booksMaster != null
                        && booksMaster.getBooks() != null
                        && !booksMaster.getBooks().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }

    static boolean validate(BookDetail bookDetail) {
        return isTitleAvailable(bookDetail);
    }

    static String getImgUrl(BookDetail bookDetail) {
        if(bookDetail != null
                && bookDetail.getVolumeInfo() != null
        && bookDetail.getVolumeInfo().getImageLinks() != null
        && bookDetail.getVolumeInfo().getImageLinks().getSmallThumbnail() != null
        && !bookDetail.getVolumeInfo().getImageLinks().getSmallThumbnail().isEmpty()){
            return bookDetail.getVolumeInfo().getImageLinks().getSmallThumbnail().replace("http://", "https://");
        }

        if(bookDetail != null
                && bookDetail.getVolumeInfo() != null
                && bookDetail.getVolumeInfo().getImageLinks() != null
                && bookDetail.getVolumeInfo().getImageLinks().getThumbnail() != null
                && !bookDetail.getVolumeInfo().getImageLinks().getThumbnail().isEmpty()){
            return bookDetail.getVolumeInfo().getImageLinks().getThumbnail().replace("http://", "https://");
        }

        return null;
    }

    static String getTitle(BookDetail mBookDetail) {
        if(isTitleAvailable(mBookDetail)) return mBookDetail.getVolumeInfo().getTitle();

        return "";
    }
}
