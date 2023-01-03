package com.dogukaan.foodlynn.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dogukaan.foodlynn.data.entity.CRUDCevap;
import com.dogukaan.foodlynn.data.entity.SepetYemekler;
import com.dogukaan.foodlynn.data.entity.SepetYemeklerCevap;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.data.entity.YemeklerCevap;
import com.dogukaan.foodlynn.retrofit.YemeklerDao;
import com.dogukaan.foodlynn.ui.adapter.YemeklerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    private MutableLiveData<List<Yemekler>> yemekListesi;
    private YemeklerDao ydao;
    private MutableLiveData<List<SepetYemekler>> sepetYemekListesi;

    public YemeklerDaoRepository(YemeklerDao ydao) {
        this.ydao = ydao;
        yemekListesi = new MutableLiveData();
        sepetYemekListesi = new MutableLiveData<>();
    }

    public MutableLiveData<List<Yemekler>> getYemekListesi() {
        return yemekListesi;
    }

    public MutableLiveData<List<SepetYemekler>> getSepetYemekListesi() {
        return sepetYemekListesi;
    }

    public void yemekGetir() {
        ydao.yemekleriYukle().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> liste = response.body().getYemekler();
                yemekListesi.setValue(liste);
                Log.e("yemek", String.valueOf(getYemekListesi()));
                //adapter = new YemeklerAdapter(,liste);
            }
            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {}
        });
    }

    public void sepetYemekGetir(String kullanici_adi) {
        ydao.sepetYemekGetir(kullanici_adi).enqueue(new Callback<SepetYemeklerCevap>() {
            @Override
            public void onResponse(Call<SepetYemeklerCevap> call, Response<SepetYemeklerCevap> response) {
                List<SepetYemekler> liste = response.body().getSepetYemekler();
                sepetYemekListesi.setValue(liste);
                Log.e("yemek ",kullanici_adi +" - sepet ");
            }
            @Override
            public void onFailure(Call<SepetYemeklerCevap> call, Throwable t) {}
        });
    }

    public void sepetYemekEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        ydao.sepetYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }

    public void sepetYemekSil(int sepet_yemek_id, String kullanici_adi){
        ydao.sepetYemekSil(sepet_yemek_id,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepetYemekGetir(kullanici_adi);
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }

}
