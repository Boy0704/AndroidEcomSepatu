package com.jualkoding.ecomsepatu.ui.detail;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.model.ColorsModel;
import com.jualkoding.ecomsepatu.model.SizeModel;
import com.jualkoding.ecomsepatu.model.home.Casual;
import com.jualkoding.ecomsepatu.model.home.Sport;
import com.jualkoding.ecomsepatu.ui.cart.CartActivity;
import com.jualkoding.ecomsepatu.utils.Const;
import com.jualkoding.ecomsepatu.utils.Ekstention;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment implements ColorsAdapter.ItemAdapterCallback, SizeAdapter.ItemAdapterCallback {

    private RecyclerView rvSetColors;
    private List<ColorsModel> colorsModelList;

    private RecyclerView rvSetSize;
    private List<SizeModel> sizeModelList;
    private Button btnAddCart;
    private String dataStatus;
    private Sport dataSport;
    private Casual dataCasual;
    private ImageView ivPoster;
    private TextView tvJudul,tvDisc,tvHarga,tvHargaDisc;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivPoster = view.findViewById(R.id.imageView3);
        tvJudul = view.findViewById(R.id.textView11);
        tvDisc = view.findViewById(R.id.textView12);
        tvHarga = view.findViewById(R.id.textView13);
        tvHargaDisc = view.findViewById(R.id.textView14);


        rvSetColors = view.findViewById(R.id.rv_setcolor);
        rvSetSize = view.findViewById(R.id.rv_setsize);
        btnAddCart = view.findViewById(R.id.btn_add_cart);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dataStatus = getArguments().getString(new Const().INTENT_DETAIL_STATUS);
        if (dataStatus.equalsIgnoreCase(new Const().SPORT)) {
            dataSport = getArguments().getParcelable(new Const().INTENT_DETAIL);
            if (dataSport != null){
                initView(dataSport);
            }
        } else {
            dataCasual = getArguments().getParcelable(new Const().INTENT_DETAIL);
            if (dataCasual != null) {
                initView(dataCasual);
            }
        }

        colorsModelList = new ArrayList<>();

        colorsModelList.add(new ColorsModel(1, R.color.colorAccent));
        colorsModelList.add(new ColorsModel(2, R.color.colorGrey));
        colorsModelList.add(new ColorsModel(3, R.color.colorPrimary));

        ColorsAdapter colorsAdapter  = new ColorsAdapter(colorsModelList, this);
        rvSetColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSetColors.setAdapter(colorsAdapter);

        sizeModelList = new ArrayList<>();

        sizeModelList.add(new SizeModel(1,"36\nEU"));
        sizeModelList.add(new SizeModel(2,"37\nEU"));
        sizeModelList.add(new SizeModel(3,"38\nEU"));
        sizeModelList.add(new SizeModel(4,"39\nEU"));
        sizeModelList.add(new SizeModel(5,"40\nEU"));

        SizeAdapter sizeAdapter = new SizeAdapter(sizeModelList,this);
        rvSetSize.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvSetSize.setAdapter(sizeAdapter);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCart = new Intent(getActivity(), CartActivity.class);
                startActivity(goCart);
            }
        });
    }

    private void initView(Sport dataSport) {
        Glide.with(getContext()).load(dataSport.getPoster()).into(ivPoster);

        tvJudul.setText(dataSport.getTitle());
        tvDisc.setText(dataSport.getDisc());
        tvHarga.setText(new Ekstention().convertPrice(dataSport.getPrice()));
        tvHargaDisc.setText(new Ekstention().convertPrice(dataSport.getPriceReal()));
    }

    private void initView(Casual dataCasual){
        Glide.with(getContext()).load(dataCasual.getPoster()).into(ivPoster);

        tvJudul.setText(dataCasual.getTitle());
        tvDisc.setText(dataCasual.getDisc());
        tvHarga.setText(new Ekstention().convertPrice(dataCasual.getPrice()));
        tvHargaDisc.setText(new Ekstention().convertPrice(dataCasual.getPriceReal()));
    }

    @Override
    public void onClick(View view) {

    }
}