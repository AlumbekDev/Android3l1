package com.example.android3lesson1.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson1.OnItemClick;
import com.example.android3lesson1.R;
import com.example.android3lesson1.databinding.FragmentHomeBinding;
import com.example.android3lesson1.models.BooksViewModel;
import com.example.android3lesson1.models.Model;
import com.example.android3lesson1.ui.adapters.BookAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    BookAdapter adapter = new BookAdapter();
    BooksViewModel viewModel;
    ArrayList<Model> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupViews();
        setupListener();
    }


    private void setupListener() {
        setupClick();
        adapter.setClick(new OnItemClick() {
            @Override
            public void clickListener(Model model) {
                BooksViewModel viewModel = new ViewModelProvider(requireActivity()).get(BooksViewModel.class);
                viewModel.setSelected(model);
                NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.detailFragment);
            }
        });
    }

    private void setupClick() {
        binding.btnAdd.setOnClickListener(v -> {
            viewModel.fetchData();
        });
          viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<Model>>() {
              @Override
              public void onChanged(ArrayList<Model> models) {
                  adapter.addList(models);

              }
          });
    }

    private void setupViews() {
        binding.rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecycler.setAdapter(adapter);
    }

    private void initialize() {
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        viewModel.mutableLiveData.observe(requireActivity(), list -> {
            adapter.addList(list);
            binding.btnAdd.setVisibility(View.GONE);
        });

    }
}