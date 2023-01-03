package com.dogukaan.foodlynn.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogukaan.foodlynn.R;
import com.dogukaan.foodlynn.data.entity.SepetYemekler;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.databinding.FragmentSepetBinding;
import com.dogukaan.foodlynn.ui.adapter.SepetYemeklerAdapter;
import com.dogukaan.foodlynn.ui.viewmodel.SepetViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false);
        binding.setSepetToolbarBaslik("Sepet");


        binding.sepetRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        //ArrayList<SepetYemekler> sepetYemeklerListesi = new ArrayList<>();

        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);

        //binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.sepetYemeklerListesi.observe(getViewLifecycleOwner(),sepetYemeklerListesi->{
            SepetYemeklerAdapter adapter =  new SepetYemeklerAdapter(requireContext(),sepetYemeklerListesi,viewModel);
            //binding.sepetRv.setAdapter(adapter);
            binding.setSepetYemekAdapter(adapter);
        });

        
        //SepetYemeklerAdapter adapter =  new SepetYemeklerAdapter(requireContext(),sepetYemeklerListesi);
        //binding.sepetRv.setAdapter(adapter);


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
    }
}