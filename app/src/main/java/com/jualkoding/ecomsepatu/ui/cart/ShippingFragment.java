package com.jualkoding.ecomsepatu.ui.cart;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.jualkoding.ecomsepatu.BuildConfig;
import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.api.ApiService;
import com.jualkoding.ecomsepatu.model.cost.ItemCost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.Navigation.findNavController;

public class ShippingFragment extends Fragment {

    private Button btnPayment;
    private ProgressDialog progressDialog;

    public ShippingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shipping, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPayment = view.findViewById(R.id.btnPayment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Silahkan tunggu..");
                progressDialog.show();

                getCost("62","63","1700","jne", v);
            }
        });
    }

    private void getCost(String origin, String dest, String weight, String courier,final View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.RAJA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService =  retrofit.create(ApiService.class);
        Call<ItemCost> call = apiService.getCost(
                "8daca5cc68fa60eb24427504a17a49d0",
                "Android",
                origin,
                dest,
                weight,
                courier
        );

        call.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    int statusCode = response.body().getRajaongkir().getStatus().getCode();
                    if (statusCode == 200) {

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("rajaongkir", response.body().getRajaongkir());
                        Navigation
                                .findNavController(view)
                                .navigate(R.id.action_shippingFragment_to_paymentFragment, bundle);

                    } else {
                        String statusMessage = response.body().getRajaongkir().getStatus().getDescription();
                        Toast.makeText(getActivity(),statusMessage, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Error mendapatkan data dari server "+ response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}