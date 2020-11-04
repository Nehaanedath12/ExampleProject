package com.example.exampleproject.CaptureImage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.exampleproject.R;

import io.fotoapparat.result.BitmapPhoto;

public class ImageFragment extends Fragment {

    BitmapPhoto bitmap;
    public ImageFragment(BitmapPhoto bitmap) {
       this.bitmap=bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=LayoutInflater.from(getContext()).inflate(R.layout.image_touch,container,false);
        ImageView imageView=view.findViewById(R.id.image);
        imageView.setImageBitmap(bitmap.bitmap);
        imageView.setRotation(-bitmap.rotationDegrees);
        return view;

    }
}
