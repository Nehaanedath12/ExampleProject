package com.example.exampleproject;

import android.net.Uri;

public class ImageClass {
    Uri uri;

    public ImageClass(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
