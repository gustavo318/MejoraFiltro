package com.jgmayer.recycler.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jgmayer.recycler.R;
import com.jgmayer.recycler.activity.DetalleDonacion;
import com.jgmayer.recycler.activity.MainActivity;
import com.jgmayer.recycler.model.ItemList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<ItemList> items;
    private List<ItemList> originalItems;
    private RecyclerItemClick itemClick;


    public RecyclerAdapter(List<ItemList> items, MainActivity mainActivity) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList item = items.get(position);
        holder.imgItem.setImageResource(item.getImgResource());
        holder.tvNombre.setText(item.getNombre());
        holder.tvDescripcion.setText(item.getDescripcion());
        holder.tvContacto.setText(item.getContacto());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleDonacion.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<ItemList> collect = originalItems.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList i : originalItems) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private ImageView imgItem;
        private TextView tvNombre;
        private TextView tvDescripcion;
        private TextView tvContacto;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvContacto = itemView.findViewById(R.id.tvContacto);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(ItemList item);
    }
}
