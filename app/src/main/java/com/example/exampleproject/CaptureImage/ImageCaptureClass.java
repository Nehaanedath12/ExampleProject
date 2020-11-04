package com.example.exampleproject.CaptureImage;


import android.graphics.Bitmap;

import io.fotoapparat.result.BitmapPhoto;

public class ImageCaptureClass {
    BitmapPhoto bitmap;
    public ImageCaptureClass(BitmapPhoto bitmap) {
        this.bitmap=bitmap;
    }

    public BitmapPhoto getBitmap() {
        return bitmap;
    }
}
