package com.example.pointofsale.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsale.Activity.BarangViewActivity;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.CardViewViewHolder> {
    private ArrayList<Barang> listBarang;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public BarangAdapter(ArrayList<Barang> list){
        this.listBarang = list;
    }
    @NonNull
    @Override
    public BarangAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_barang_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Barang barang = listBarang.get(position);
        holder.idBarang = barang.getId();
        holder.tvNamaBarang.setText(barang.getNama_barang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), BarangViewActivity.class);
                intent.putExtra(BarangViewActivity.EXTRA_ID_BARANG_VIEW, holder.idBarang);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang;
        int idBarang;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.txt_nama_barang);
            idBarang = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Barang data);
    }
}
