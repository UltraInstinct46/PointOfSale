package com.example.pointofsale.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsale.Activity.TransactionViewActivity;
import com.example.pointofsale.Activity.TransactionViewActivity;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.CardViewViewHolder> {
    private ArrayList<Transaction> listTransaction;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public TransactionAdapter(ArrayList<Transaction> list){
        this.listTransaction = list;
    }
    @NonNull
    @Override
    public TransactionAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_transaction_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Transaction transaction = listTransaction.get(position);
        holder.idTransaction = transaction.getId();
        holder.tvIdTransaction.setText(String.valueOf(transaction.getId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), TransactionViewActivity.class);
                intent.putExtra(TransactionViewActivity.EXTRA_ID_TRANSACTION_VIEW, holder.idTransaction);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listTransaction.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdTransaction;
        int idTransaction;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdTransaction = itemView.findViewById(R.id.txt_id_transaction);
            idTransaction = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Transaction data);
    }
}
