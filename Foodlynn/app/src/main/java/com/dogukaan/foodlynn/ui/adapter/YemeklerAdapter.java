package com.dogukaan.foodlynn.ui.adapter;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.databinding.YemekCardTasarimBinding;
import com.dogukaan.foodlynn.ui.view.YemekDetayActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.YemekCardTasarimTutucu>{
    private Context mContext;
    private List<Yemekler> yemeklerListesi;

    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerListesi) {
        this.mContext = mContext;
        this.yemeklerListesi = yemeklerListesi;
    }

    public class YemekCardTasarimTutucu extends RecyclerView.ViewHolder{
        private YemekCardTasarimBinding binding;

        public YemekCardTasarimTutucu(YemekCardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public YemekCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YemekCardTasarimBinding binding = YemekCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new YemekCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull YemekCardTasarimTutucu holder, int position) {
        Yemekler yemekler = yemeklerListesi.get(position);
        YemekCardTasarimBinding t = holder.binding;



        Log.e("yemekler", yemekler.getYemek_id()+" - "+ yemekler.getYemek_adi() + yemekler.getYemek_resim_adi()+ yemekler.getYemek_fiyat());
        t.textViewFiyat.setText(yemekler.getYemek_fiyat()+"₺");
        t.textViewYemek.setText(yemekler.getYemek_adi());

        //t.imageViewYemek.setImageResource(mContext.getResources().getIdentifier(yemekler.getYemek_resim_adi(),"drawable",mContext.getPackageName()));

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+yemekler.getYemek_resim_adi();

        Picasso.get().load(url).into(t.imageViewYemek);

        Log.e("yemek", yemekler.getYemek_resim_adi());
        t.cardViewYemek.setOnClickListener(view -> {
            //AnasayfaFragmentDirections.YemekDetayGecis gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek);
            //Navigation.findNavController(view).navigate(gecis);
            Intent intent = new Intent(mContext, YemekDetayActivity.class);
            intent.putExtra("nesne", yemekler);
            mContext.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }
}
