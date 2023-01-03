package com.dogukaan.foodlynn.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.databinding.ActivityYemekDetayBinding;

import com.dogukaan.foodlynn.ui.viewmodel.YemekDetayViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class YemekDetayActivity extends AppCompatActivity {
    private ActivityYemekDetayBinding binding;
    private YemekDetayViewModel viewModel;
    private int yemekAdet = 1;
    //private int say = (int) Double.parseDouble((String) binding.textViewAdet.getText());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYemekDetayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Yemekler y = (Yemekler) getIntent().getSerializableExtra("nesne"); //getIntent için Activity gerekli
        //Yemek y = (Yemek) getIntent().getSerializableExtra("nesne");
        binding.toolbarYemekDetay.setTitle(y.getYemek_adi());
        //binding.ivYemekDetay.setImageResource(getResources().getIdentifier(y.getYemek_resim_adi(), "drawable", getPackageName()));
        //binding.tvFiyat4.setText(y.getYemek_fiyat()+"₺");

        viewModel = new ViewModelProvider(this).get(YemekDetayViewModel.class);


        binding.textViewAdet.setText(String.valueOf(yemekAdet));
        binding.buttonPlus.setOnClickListener(view -> {
            increase();
        });
        binding.buttonMinus.setOnClickListener(view -> {
            if (yemekAdet > 0) {
                reduce();
            } else {
                Snackbar.make(view, "Adet 0 dan az olamaz", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.buttonEkle.setOnClickListener(view -> {
            Snackbar.make(view, y.getYemek_adi() + " sipariş verildi. Fiyat: " + y.getYemek_fiyat() + "₺", Snackbar.LENGTH_SHORT).show();
            ekle(y.getYemek_adi(),y.getYemek_resim_adi(),y.getYemek_fiyat(),yemekAdet,"DogukaanATLAR");
        });

        binding.tvFiyat.setText("Adet Fiyatı : "+String.valueOf(y.getYemek_fiyat())+"₺");
        binding.tvYemekAdi.setText(y.getYemek_adi());

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+y.getYemek_resim_adi();

        Picasso.get().load(url).into(binding.ivYemekDetay);

    }

    public void ekle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        Log.e("yemek ekle ", kullanici_adi + " - " + yemek_siparis_adet + "adet " + yemek_adi + " eklendi Fiyat: " + yemek_fiyat+ "₺");
        viewModel.sepetYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
    }


    public void increase(){
        yemekAdet++;
        binding.textViewAdet.setText(String.valueOf(yemekAdet));
    }
    public void reduce(){
        yemekAdet--;
        binding.textViewAdet.setText(String.valueOf(yemekAdet));
    }


}