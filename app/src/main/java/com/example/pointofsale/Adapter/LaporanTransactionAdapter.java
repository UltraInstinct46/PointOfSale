package com.example.pointofsale.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class LaporanTransactionAdapter extends RecyclerView.Adapter<LaporanTransactionAdapter.CardViewViewHolder> {
    private ArrayList<Transaction> listLaporanTransaction;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public LaporanTransactionAdapter(ArrayList<Transaction> list){
        this.listLaporanTransaction = list;
    }
    @NonNull
    @Override
    public LaporanTransactionAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_laporan_transaction_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Transaction laporanTransaction = listLaporanTransaction.get(position);
        holder.idTransaction = laporanTransaction.getId();
        holder.tvKdTransaksi.setText(String.valueOf(laporanTransaction.getId()));
        holder.tvKdBarang.setText(String.valueOf(laporanTransaction.getKd_barang()));
        holder.tvKdUser.setText(String.valueOf(laporanTransaction.getKd_user()));
        holder.tvJumlahBeli.setText(String.valueOf(laporanTransaction.getJumlah_beli()));
        holder.tvTotalBeli.setText(String.valueOf(laporanTransaction.getTotal_harga()));
        holder.tvTanggalBeli.setText(String.valueOf(laporanTransaction.getDate()));

        holder.tvKdTransaksiLabel.setText("Kode Transaksi : ");
        holder.tvKdBarangLabel.setText("Kode Barang : ");
        holder.tvKdUserLabel.setText("Kode User : ");
        holder.tvJumlahBeliLabel.setText("Jumlah Beli : ");
        holder.tvTotalBeliLabel.setText("Total Beli : ");
        holder.tvTanggalBeliLabel.setText("Tanggal Beli : ");
    }

    @Override
    public int getItemCount() {
        return listLaporanTransaction.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvKdTransaksi,tvKdBarang,tvKdUser,tvJumlahBeli,tvTotalBeli,tvTanggalBeli;
        TextView tvKdTransaksiLabel,tvKdBarangLabel,tvKdUserLabel,tvJumlahBeliLabel,tvTotalBeliLabel,tvTanggalBeliLabel;
        int idTransaction;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKdTransaksi = itemView.findViewById(R.id.txt_kode_transaksi_laporan);
            tvKdBarang = itemView.findViewById(R.id.txt_kode_barang_transaction_laporan);
            tvKdUser = itemView.findViewById(R.id.txt_kode_user_laporan);
            tvJumlahBeli = itemView.findViewById(R.id.txt_jumlah_beli_laporan);
            tvTotalBeli = itemView.findViewById(R.id.txt_total_harga_laporan);
            tvTanggalBeli = itemView.findViewById(R.id.txt_tanggal_beli_laporan);


            tvKdTransaksiLabel = itemView.findViewById(R.id.txt_kode_transaksi_laporan_label);
            tvKdBarangLabel = itemView.findViewById(R.id.txt_kode_barang_transaction_laporan_label);
            tvKdUserLabel = itemView.findViewById(R.id.txt_kode_user_laporan_label);
            tvJumlahBeliLabel = itemView.findViewById(R.id.txt_jumlah_beli_laporan_label);
            tvTotalBeliLabel = itemView.findViewById(R.id.txt_total_harga_laporan_label);
            tvTanggalBeliLabel = itemView.findViewById(R.id.txt_tanggal_beli_laporan_label);
            idTransaction = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Transaction data);
    }
}
