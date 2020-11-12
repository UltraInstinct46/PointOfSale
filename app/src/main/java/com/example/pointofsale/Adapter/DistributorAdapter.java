package com.example.pointofsale.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsale.Activity.BarangViewActivity;
import com.example.pointofsale.Activity.DistributorViewActivity;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class DistributorAdapter extends RecyclerView.Adapter<DistributorAdapter.CardViewViewHolder> {
    private ArrayList<Distributor> lisDistributor;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public DistributorAdapter(ArrayList<Distributor> list){
        this.lisDistributor = list;
    }
    @NonNull
    @Override
    public DistributorAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_distributor_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Distributor distributor = lisDistributor.get(position);
        holder.idDistributor = distributor.getId();
        holder.tvNamaDistributor.setText(distributor.getNama_distributor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), DistributorViewActivity.class);
                intent.putExtra(DistributorViewActivity.EXTRA_ID_DISTRIBUTOR_VIEW, holder.idDistributor);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lisDistributor.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaDistributor;
        int idDistributor;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaDistributor = itemView.findViewById(R.id.txt_nama_distributor);
            idDistributor = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Distributor data);
    }
}
