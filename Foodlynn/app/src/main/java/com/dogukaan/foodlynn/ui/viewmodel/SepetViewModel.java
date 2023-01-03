package com.dogukaan.foodlynn.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dogukaan.foodlynn.data.entity.SepetYemekler;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;
    String kullanici_adi = "DogukaanATLAR";
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi = new MutableLiveData<>();

    @Inject
    public SepetViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        sepetYemekGetir(kullanici_adi);
        sepetYemeklerListesi = yrepo.getSepetYemekListesi();
    }

    public void sepetYemekGetir(String kullanici_adi){
        //String kullanici_adi = "DogukaanATLAR";
        //yrepo.sepetYemekGetir(kullanici_adi);
        yrepo.sepetYemekGetir(kullanici_adi);
    }

    public void sepetYemekSil(int sepet_yemek_id,String kullanici_adi){
        yrepo.sepetYemekSil(sepet_yemek_id,kullanici_adi);
    }
}
