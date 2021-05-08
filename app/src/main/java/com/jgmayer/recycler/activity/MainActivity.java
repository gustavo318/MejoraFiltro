package com.jgmayer.recycler.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jgmayer.recycler.R;
import com.jgmayer.recycler.adapter.RecyclerAdapter;
import com.jgmayer.recycler.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Licuadora en buen estado y funcionando", "ROSANA AGRIELA","091478963", R.drawable.licuadora));
        itemLists.add(new ItemList("Lavarropas, con problemas en centrifugado", "SONIA OLIVERA","091123123",R.drawable.lavarropas));
        itemLists.add(new ItemList("Ropero, de 1,20 x 1,10 x 0,50", "GUSTAVO MAYER","091456789", R.drawable.ropero));
        itemLists.add(new ItemList("Celular en buen estado ", "MARCELO ALVAREZ", "094456321",R.drawable.celular));
        itemLists.add(new ItemList("Plancha fucionando", "SARA PEREZ", "099987963",R.drawable.plancha));


        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetalleDonacion.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}