package com.gknsvs.javamaptravelbook.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gknsvs.javamaptravelbook.databinding.RecyclerRowBinding;
import com.gknsvs.javamaptravelbook.model.Place;
import com.gknsvs.javamaptravelbook.view.MainActivity;
import com.gknsvs.javamaptravelbook.view.MapsActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceView> {

    List<Place> placeList;

    public PlaceAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PlaceView(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceView holder, int position) {
        holder.binding.recyclerViewTextView.setText(placeList.get(position).name);
        int myPosition=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent.putExtra("info","selected");
                intent.putExtra("place",placeList.get(myPosition));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class PlaceView extends RecyclerView.ViewHolder {
        RecyclerRowBinding binding;
        public PlaceView(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
