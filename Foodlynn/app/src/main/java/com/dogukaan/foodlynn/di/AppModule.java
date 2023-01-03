package com.dogukaan.foodlynn.di;


import com.dogukaan.foodlynn.data.repo.YemeklerDaoRepository;
import com.dogukaan.foodlynn.retrofit.ApiUtils;
import com.dogukaan.foodlynn.retrofit.YemeklerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository provideYemeklerDaoRepository(YemeklerDao ydao){
        return new YemeklerDaoRepository(ydao);
    }

    @Provides
    @Singleton
    public YemeklerDao provideYemeklerDao(){
        return ApiUtils.getYemeklerDao();
    }
}
