package com.example.pointofsale.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsale.Activity.BarangViewActivity;
import com.example.pointofsale.Activity.MerekViewActivity;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class MerekAdapter extends RecyclerView.Adapter<MerekAdapter.CardViewViewHolder> {
    private ArrayList<Merek> listMerek;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public MerekAdapter(ArrayList<Merek> list){
        this.listMerek = list;
    }
    @NonNull
    @Override
    public MerekAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_merek_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Merek merek = listMerek.get(position);
        holder.idMerek = merek.getId();
        holder.tvNamaMerek.setText(merek.getMerek());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), MerekViewActivity.class);
                intent.putExtra(MerekViewActivity.EXTRA_ID_MEREK_VIEW, holder.idMerek);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMerek.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaMerek;
        int idMerek;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaMerek = itemView.findViewById(R.id.txt_nama_merek);
            idMerek = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Merek data);
    }
}
