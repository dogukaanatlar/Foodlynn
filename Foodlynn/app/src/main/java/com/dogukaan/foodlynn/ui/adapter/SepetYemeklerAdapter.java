package com.dogukaan.foodlynn.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dogukaan.foodlynn.data.entity.SepetYemekler;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.databinding.SepetCardTasarimBinding;
import com.dogukaan.foodlynn.ui.viewmodel.SepetViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SepetYemeklerAdapter extends RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu>{
    private Context mContext;
    private List<SepetYemekler> sepetYemeklerListesi;
    private SepetViewModel viewModel;

    public SepetYemeklerAdapter(Context mContext, List<SepetYemekler> sepetYemeklerListesi,SepetViewModel viewModel) {
        this.mContext = mContext;
        this.sepetYemeklerListesi = sepetYemeklerListesi;
        this.viewModel = viewModel;
    }

    public class SepetCardTasarimTutucu extends RecyclerView.ViewHolder{
        private SepetCardTasarimBinding binding;

        public SepetCardTasarimTutucu(SepetCardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SepetCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetCardTasarimBinding binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new SepetCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetCardTasarimTutucu holder, int position) {
        SepetYemekler sepetYemek = sepetYemeklerListesi.get(position);
        SepetCardTasarimBinding t = holder.binding;
        t.setSepetYemekNesnesi(sepetYemek);


        t.tvSepetBilgi.setText(sepetYemek.getYemek_adi()+" - "+sepetYemek.getYemek_siparis_adet()+" - "+sepetYemek.getYemek_fiyat());

        t.imageViewSil.setOnClickListener(view -> {
            Snackbar.make(view,sepetYemek.getYemek_adi()+" silinsin mi?",Snackbar.LENGTH_LONG)
                    .setAction("EVET",view1 -> {
                        Log.e("Yemek sil ", String.valueOf(sepetYemek.getSepet_yemek_id()));
                        viewModel.sepetYemekSil(sepetYemek.getSepet_yemek_id(), sepetYemek.getKullanici_adi());
                    }).show();
        });

    }

    @Override
    public int getItemCount() {
        return sepetYemeklerListesi.size();
    }
}
