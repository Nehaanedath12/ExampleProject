package com.example.exampleproject.CaptureImage;

import io.fotoapparat.result.BitmapPhoto;

public class ImageCaptureClass {
    BitmapPhoto bitmap;
    public ImageCaptureClass(BitmapPhoto bitmap) {

        this.bitmap=bitmap;
    }

    public BitmapPhoto getBitmap() {
        return bitmap;
    }

    public void setBitmap(BitmapPhoto bitmap) {
        this.bitmap = bitmap;
    }
}
