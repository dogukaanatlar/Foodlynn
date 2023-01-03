package com.dogukaan.foodlynn.retrofit;

import com.dogukaan.foodlynn.data.entity.CRUDCevap;
import com.dogukaan.foodlynn.data.entity.SepetYemeklerCevap;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemeklerDao {
    //http://kasimadalan.pe.hu/ -> Base url

    //GET  : Webservise veri gönderilmiyorsa.
    //POST : Eğer webservise veri gönderiliyorsa.
    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekleriYukle();

    @POST("yemekler/tumYemekleriGetir.php")
    @FormUrlEncoded
    Call<YemeklerCevap> getir(@Field("yemek_id") int yemek_id,
                              @Field("yemek_adi") String yemek_adi,
                              @Field("yemek_resim_adi") String yemek_resim_adi,
                              @Field("yemek_fiyat") int yemek_fiyat);

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetYemekEkle(@Field("yemek_adi") String yemek_adi,
                                   @Field("yemek_resim_adi") String yemek_resim_adi,
                                   @Field("yemek_fiyat") int yemek_fiyat,
                                   @Field("yemek_siparis_adet") int yemek_siparis_adet,
                                   @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemeklerCevap> sepetYemekGetir(@Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetYemekSil(@Field("sepet_yemek_id") int sepet_yemek_id,
                                  @Field("kullanici_adi") String kullanici_adi);
}
