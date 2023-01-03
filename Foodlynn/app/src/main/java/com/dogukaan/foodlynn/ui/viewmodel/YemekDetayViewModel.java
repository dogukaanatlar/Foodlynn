package com.dogukaan.foodlynn.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.dogukaan.foodlynn.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class YemekDetayViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;

    @Inject
    public YemekDetayViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
    }

    public void sepetYemekEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        yrepo.sepetYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
    }
}
