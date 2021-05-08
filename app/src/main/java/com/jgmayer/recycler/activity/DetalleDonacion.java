package com.jgmayer.recycler.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jgmayer.recycler.R;
import com.jgmayer.recycler.model.ItemList;

public class DetalleDonacion extends AppCompatActivity {
    private ImageView imgItemDetail;
    private TextView tvNombreDetail;
    private TextView tvDescripcionDetail;
    private TextView tvContactoDetail;
    private ItemList itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
    }

    private void initViews() {
        imgItemDetail = findViewById(R.id.imgItemDetail);
        tvNombreDetail = findViewById(R.id.tvNombreDetail);
        tvDescripcionDetail = findViewById(R.id.tvDescripcionDetail);
        tvContactoDetail = findViewById(R.id.tvContactoDetail);
    }

    private void initValues(){
        itemDetail = (ItemList) getIntent().getExtras().getSerializable("itemDetail");

        imgItemDetail.setImageResource(itemDetail.getImgResource());
        tvNombreDetail.setText(itemDetail.getNombre());
        tvDescripcionDetail.setText(itemDetail.getDescripcion());
        tvContactoDetail.setText(itemDetail.getContacto());
    }
}
