package com.example.android3lesson1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson1.OnItemClick;
import com.example.android3lesson1.R;
import com.example.android3lesson1.models.Model;
import com.example.android3lesson1.ui.home.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<Model> list = new ArrayList<>();
     OnItemClick click;



    public Model getModelPosition(int position) {
        return list.get(position);
    }


    public void addList(ArrayList<Model> models) {
        list = models;
        notifyDataSetChanged();
    }

    public void setClick(OnItemClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtText;
        ImageView imageView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.myText1);
            imageView = itemView.findViewById(R.id.myImageView);

        }

        public void bind(Model model) {
            txtText.setText(model.getTitle());
            imageView.setImageResource(model.getImage());
            itemView.setOnClickListener(v -> {
                click.clickListener(model);
            });
        }
    }
}

