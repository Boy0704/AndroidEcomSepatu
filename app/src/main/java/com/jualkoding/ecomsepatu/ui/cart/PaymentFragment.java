package com.jualkoding.ecomsepatu.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.BuildConfig;
import com.jualkoding.ecomsepatu.model.CustomerModel;
import com.jualkoding.ecomsepatu.model.cost.Rajaongkir;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;


public class PaymentFragment extends Fragment implements TransactionFinishedCallback {

    private Button btnPayment;
    private TextView tvAlamat;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPayment = view.findViewById(R.id.btn_payment);
        View paymentDetail = view.findViewById(R.id.includePaymentDetail);

        tvAlamat = paymentDetail.findViewById(R.id.tv_alamat);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Rajaongkir rajaongkir = getArguments().getParcelable("rajaongkir");
        if (rajaongkir != null) {
            tvAlamat.setText(rajaongkir.getDestinationDetails().getCityName());
        }

        initMidtransSDK();
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                        "BCOO1",
                        500000,
                        1,
                        "Adidas Falcon"
                ));
                MidtransSDK.getInstance().startPaymentUiFlow(getActivity(), PaymentMethod.BANK_TRANSFER_MANDIRI);

            }
        });

    }

    private void initMidtransSDK() {
        SdkUIFlowBuilder.init()
                .setClientKey(BuildConfig.CLIENT_KEY) // client_key is mandatory
                .setContext(getActivity()) // context is mandatory
                .setTransactionFinishedCallback(this) // set transaction finish callback (sdk callback)
                .setMerchantBaseUrl(BuildConfig.BASE_URL) //set merchant url (required)
                .enableLog(true) // enable sdk log (optional)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
//                .setLanguage("id") //`en` for English and `id` for Bahasa
                .buildSDK();
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getStatus()) {
                case TransactionResult.STATUS_SUCCESS :
                    Toast.makeText(getActivity(), "Transaksi sukses dengan id "+result.getResponse().getTransactionId(),
                            Toast.LENGTH_SHORT).show();
                    break;

                case TransactionResult.STATUS_PENDING :
                    Toast.makeText(getActivity(), "Transaksi tertunda dengan id "+result.getResponse().getTransactionId(),
                            Toast.LENGTH_SHORT).show();
                    break;

                case TransactionResult.STATUS_FAILED :
                    Toast.makeText(getActivity(), "Transaksi gagal dengan id "+result.getResponse().getTransactionId(),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            result.getResponse().getValidationMessages();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(getActivity(), "Transaksi di cancel dengan id "+result.getResponse().getTransactionId(),
                    Toast.LENGTH_SHORT).show();
        } else {
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)){
                Toast.makeText(getActivity(), "Transaksi di invalid dengan id ",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Transaksi di finish with fail ",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}