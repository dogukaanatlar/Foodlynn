package com.dogukaan.foodlynn.ui.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogukaan.foodlynn.R;
import com.dogukaan.foodlynn.data.entity.Yemekler;
import com.dogukaan.foodlynn.databinding.FragmentAnasayfaBinding;
import com.dogukaan.foodlynn.ui.adapter.YemeklerAdapter;
import com.dogukaan.foodlynn.ui.viewmodel.AnasayfaViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);

        //((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbarAnasayfa);

        binding.yemeklerRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //ArrayList<Yemekler> YemeklerListesi = new ArrayList<>();

        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),yemeklerlistesi->{
            YemeklerAdapter adapter =  new YemeklerAdapter(requireContext(), yemeklerlistesi);
            binding.yemeklerRv.setAdapter(adapter);
        });


        binding.fab.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.sepetGecis);
        });

        return binding.getRoot();
    }
}