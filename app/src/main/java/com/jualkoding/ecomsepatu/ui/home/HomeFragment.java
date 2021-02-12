package com.jualkoding.ecomsepatu.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jualkoding.ecomsepatu.BuildConfig;
import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.api.ApiService;
import com.jualkoding.ecomsepatu.model.HomeModel;
import com.jualkoding.ecomsepatu.model.home.Casual;
import com.jualkoding.ecomsepatu.model.home.ItemProduk;
import com.jualkoding.ecomsepatu.model.home.Sport;
import com.jualkoding.ecomsepatu.ui.categories.CategoriesActivity;
import com.jualkoding.ecomsepatu.ui.home.adapter.CasualAdapter;
import com.jualkoding.ecomsepatu.ui.home.adapter.SportAdapter;
import com.jualkoding.ecomsepatu.utils.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements CasualAdapter.ItemAdapterCallback, SportAdapter.ItemAdapterCallback {

    private RecyclerView rvSport;
    private RecyclerView rvCasual;
    private List<HomeModel> listData;

    private TextView tvActionLayoutSport, tvActionLayoutCasual, tvHeaderLayoutCasual;
    private TextView tvShopNow;
    private ImageView ivShowNow;
    private ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSport = view.findViewById(R.id.rv_sport);
        rvCasual = view.findViewById(R.id.rv_casual);
        View headerLayoutSport = view.findViewById(R.id.layout_header_sport);
        View headerLayoutCasual = view.findViewById(R.id.layout_header_casual);
        View headerLayout = view.findViewById(R.id.layout_header);

        tvActionLayoutSport = headerLayoutSport.findViewById(R.id.tv_action);
        tvActionLayoutCasual = headerLayoutCasual.findViewById(R.id.tv_action);
        tvHeaderLayoutCasual = headerLayoutCasual.findViewById(R.id.tv_title);

        tvShopNow = headerLayout.findViewById(R.id.tv_shop_now);
        ivShowNow = headerLayout.findViewById(R.id.iv_show_now);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ivShowNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(getActivity(), CategoriesActivity.class);
                startActivity(goCategory);
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Silahkan tunggu..");
        progressDialog.show();

        getData();

    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment);
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SHOP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ItemProduk> call = apiService.getProduk(
                new Const().CODE_APPS
        );

        call.enqueue(new Callback<ItemProduk>() {
            @Override
            public void onResponse(Call<ItemProduk> call, Response<ItemProduk> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){

                    String statusCode = response.body().getCodeStatus();
                    if (statusCode.equalsIgnoreCase("200")) {
                        setDataSport(response.body().getData().getSport());
                        setDataCasual(response.body().getData().getCasual());
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ItemProduk> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Error "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataCasual(List<Casual> casual) {

        tvHeaderLayoutCasual.setText("Casual Shoes");
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        CasualAdapter casualAdapter = new CasualAdapter(casual,this);
        rvCasual.setLayoutManager(linearLayoutManager1);
        rvCasual.setAdapter(casualAdapter);

    }

    private void setDataSport(List<Sport> sport) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        SportAdapter sportAdapter = new SportAdapter(sport,this);
        rvSport.setLayoutManager(linearLayoutManager);
        rvSport.setAdapter(sportAdapter);

    }

}