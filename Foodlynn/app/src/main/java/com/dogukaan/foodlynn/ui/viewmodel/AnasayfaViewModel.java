package com.dogukaan.foodlynn.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();

    @Inject
    public AnasayfaViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        yemekGetir();
        yemeklerListesi=yrepo.getYemekListesi();
    }

    public void yemekGetir(){
        yrepo.yemekGetir();
    }

}
