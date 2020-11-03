package com.example.exampleproject.CaptureImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampleproject.R;

import java.util.List;

public class ImageCaptureAdapter  extends RecyclerView.Adapter<ImageCaptureAdapter.ViewHolder> {
    Context context;
    List<ImageCaptureClass>list;
    public ImageCaptureAdapter(CapturingImageActivity context, List<ImageCaptureClass> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ImageCaptureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_capture_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCaptureAdapter.ViewHolder holder, int position) {
        ImageCaptureClass captureClass=list.get(position);
        holder.imageRV.setImageBitmap(captureClass.bitmap.bitmap);
        holder.imageRV.setRotation(-captureClass.bitmap.rotationDegrees);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageRV,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageRV=itemView.findViewById(R.id.image_RV);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
