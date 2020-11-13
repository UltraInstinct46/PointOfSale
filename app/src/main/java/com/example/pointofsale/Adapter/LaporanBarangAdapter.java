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

public class LaporanBarangAdapter extends RecyclerView.Adapter<LaporanBarangAdapter.CardViewViewHolder> {
    private ArrayList<Barang> listLaporanBarang;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public LaporanBarangAdapter(ArrayList<Barang> list){
        this.listLaporanBarang = list;
    }
    @NonNull
    @Override
    public LaporanBarangAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_laporan_barang_item,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Barang laporanBarang = listLaporanBarang.get(position);
        holder.idBarang = laporanBarang.getId();
        holder.tvNamaBarang.setText(laporanBarang.getNama_barang());
        holder.tvKdBarang.setText(String.valueOf(laporanBarang.getId()));
        holder.tvKdMerek.setText(String.valueOf(laporanBarang.getKd_merek()));
        holder.tvKdDistributor.setText(String.valueOf(laporanBarang.getKd_distributor()));
        holder.tvTanggalMasuk.setText(laporanBarang.getTanggal_masuk());
        holder.tvHargaBarang.setText(String.valueOf(laporanBarang.getHarga_barang()));
        holder.tvStockBarang.setText(String.valueOf(laporanBarang.getStok_barang()));
        holder.tvKeterangan.setText(laporanBarang.getKeterangan());

        holder.tvNamaBarangLabel.setText("Nama Barang : ");
        holder.tvKdBarangLabel.setText("Kode Barang : ");
        holder.tvKdMerekLabel.setText("Kode Merek : ");
        holder.tvKdDistributorLabel.setText("Kode Distributor : ");
        holder.tvTanggalMasukLabel.setText("Tanggal Masuk : ");
        holder.tvHargaBarangLabel.setText("Harga Barang : ");
        holder.tvStockBarangLabel.setText("Stock Barang : ");
        holder.tvKeteranganLabel.setText("Keterangan : ");
    }

    @Override
    public int getItemCount() {
        return listLaporanBarang.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvKdBarang,tvNamaBarang,tvKdMerek,tvKdDistributor,tvTanggalMasuk,tvHargaBarang,tvStockBarang,tvKeterangan;
        TextView tvKdBarangLabel,tvNamaBarangLabel,tvKdMerekLabel,tvKdDistributorLabel,tvTanggalMasukLabel,tvHargaBarangLabel,tvStockBarangLabel,tvKeteranganLabel;

        int idBarang;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.txt_nama_barang_laporan);
            tvKdBarang = itemView.findViewById(R.id.txt_kode_barang_laporan);
            tvKdMerek = itemView.findViewById(R.id.txt_kode_merek_laporan);
            tvKdDistributor = itemView.findViewById(R.id.txt_kode_distributor_laporan);
            tvTanggalMasuk = itemView.findViewById(R.id.txt_tanggal_masuk_laporan);
            tvHargaBarang = itemView.findViewById(R.id.txt_harga_barang_laporan);
            tvKeterangan = itemView.findViewById(R.id.txt_keterangan_laporan);
            tvStockBarang = itemView.findViewById(R.id.txt_stock_barang_laporan);

            tvNamaBarangLabel = itemView.findViewById(R.id.txt_nama_barang_laporan_label);
            tvKdBarangLabel = itemView.findViewById(R.id.txt_kode_barang_laporan_label);
            tvKdMerekLabel = itemView.findViewById(R.id.txt_kode_merek_laporan_label);
            tvKdDistributorLabel = itemView.findViewById(R.id.txt_kode_distributor_laporan_label);
            tvTanggalMasukLabel = itemView.findViewById(R.id.txt_tanggal_masuk_laporan_label);
            tvHargaBarangLabel = itemView.findViewById(R.id.txt_harga_barang_laporan_label);
            tvKeteranganLabel = itemView.findViewById(R.id.txt_keterangan_laporan_label);
            tvStockBarangLabel = itemView.findViewById(R.id.txt_stock_barang_laporan_label);
            idBarang = 0;
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Barang data);
    }
}
