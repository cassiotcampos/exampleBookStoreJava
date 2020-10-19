package com.cassio.example.bookstore.model.validator;

import com.cassio.example.bookstore.model.api.BookDetail;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookValidator extends BaseValidator<BookDetail> {


    public BookValidator(BookDetail modelData) {
        super(modelData);
    }

    @Override
    public boolean validate() {
        return modelData != null && modelData.getVolumeInfo() != null;
    }

    public boolean isTitleAvailable() {
        if (
                modelData != null
                        && modelData.getVolumeInfo() != null
                        && modelData.getVolumeInfo().getTitle() != null
                        && !modelData.getVolumeInfo().getTitle().isEmpty()) {

            return true;

        } else {
            return false;
        }
    }

    public String getTitle() {
        if (isTitleAvailable()) return modelData.getVolumeInfo().getTitle();

        return "";
    }

    public boolean hasValidImg() {
        return isValidSmallThumb() || isValidThumb();
    }


    public String getValidImgUrl() {
        if (isValidSmallThumb()) {
            return modelData.getVolumeInfo().getImageLinks().getSmallThumbnail().replace("http://", "https://");
        }

        if (isValidSmallThumb()) {
            return modelData.getVolumeInfo().getImageLinks().getThumbnail().replace("http://", "https://");
        }
        return null;
    }

    private boolean isValidSmallThumb() {
        if (modelData != null
                && modelData.getVolumeInfo() != null
                && modelData.getVolumeInfo().getImageLinks() != null
                && modelData.getVolumeInfo().getImageLinks().getSmallThumbnail() != null
                && !modelData.getVolumeInfo().getImageLinks().getSmallThumbnail().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isValidThumb() {
        if (modelData != null
                && modelData.getVolumeInfo() != null
                && modelData.getVolumeInfo().getImageLinks() != null
                && modelData.getVolumeInfo().getImageLinks().getThumbnail() != null
                && !modelData.getVolumeInfo().getImageLinks().getThumbnail().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isAuthorsAvailable() {
        return modelData.getVolumeInfo().getAuthors() != null
                && !modelData.getVolumeInfo().getAuthors().isEmpty();
    }

    public String getAuthorsFormated() {
        String authorsDescription = "";

        for (String authorStr : modelData.getVolumeInfo().getAuthors()) {
            authorsDescription = authorsDescription.concat(authorStr).concat(", ");
        }

        authorsDescription = authorsDescription.substring(0, authorsDescription.length() - 3);
        authorsDescription = authorsDescription.concat(".");

        return authorsDescription;
    }

    public String getValidBuyLink() {
        return modelData.getSaleInfo().getBuyLink().replace("http://", "https://");
    }

    public boolean isBuyLinkAvailable() {
        return modelData.getSaleInfo() != null
                && modelData.getSaleInfo().getBuyLink() != null
                && !modelData.getSaleInfo().getBuyLink().isEmpty();
    }

    public boolean hasDescription() {
        return modelData.getVolumeInfo() != null
                && modelData.getVolumeInfo().getDescription() != null
                && !modelData.getVolumeInfo().getDescription().isEmpty();
    }

    public boolean hasSnippet() {
        return modelData.getVolumeInfo() != null
                && modelData.getSearchInfo() != null
                && modelData.getSearchInfo().getTextSnippet() != null
                && !modelData.getSearchInfo().getTextSnippet().isEmpty();
    }

    public boolean hasDescriptionOrSnippets() {
        return hasDescription() || hasSnippet();
    }

    public String getDescriptionOrSnippets() {
        if(hasDescription()) return modelData.getVolumeInfo().getDescription();
        if(hasSnippet()) return modelData.getSearchInfo().getTextSnippet();

        return "";
    }
}
