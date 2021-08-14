package com.example.android3lesson1.ui.framents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson1.R;
import com.example.android3lesson1.databinding.FragmentBlankBinding;
import com.example.android3lesson1.models.BooksViewModel;

public class DetailFragment extends Fragment {

    FragmentBlankBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        getData();
        return binding.getRoot();

    }

    private void getData() {
        BooksViewModel viewModel = new ViewModelProvider(requireActivity()).
                get(BooksViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), books -> {
            binding.title.setText(books.getTitle());
            binding.image.setImageResource(books.getImage());
            binding.description.setText(books.getDescription());
        });
    }
}