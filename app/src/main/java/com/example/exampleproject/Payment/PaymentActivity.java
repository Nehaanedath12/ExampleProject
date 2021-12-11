package com.example.exampleproject.Payment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.exampleproject.R;
import com.example.exampleproject.databinding.ActivityPaymentBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    final int UPI_PAYMENT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPaymentBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("upi:pay").buildUpon()
                        .appendQueryParameter("pa",binding.upiId.getText().toString())
                        .appendQueryParameter("pn",binding.name.getText().toString())
                        .appendQueryParameter("tn",binding.note.getText().toString())
                        .appendQueryParameter("am",binding.amount.getText().toString())
                        .appendQueryParameter("cu","INR")
                        .build();
                Intent upiPaymentIntent=new Intent(Intent.ACTION_VIEW);
                upiPaymentIntent.setData(uri);

                Intent chooser=Intent.createChooser(upiPaymentIntent,"Pay with");

                if(null!=chooser.resolveActivity(getPackageManager())){
                    Log.d("responseUPI","nullll");
                    startActivityForResult(chooser,UPI_PAYMENT);
                }else {
                    Toast.makeText(PaymentActivity.this, "No upi app found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("responseUPI","nullAl");
        switch (requestCode){
            case UPI_PAYMENT:
                if((RESULT_OK==resultCode) || (resultCode==11)){
                    if(data!=null){
                        String txt=data.getStringExtra("response");
                        Log.d("responseUPI",txt);
                        ArrayList<String>dataList=new ArrayList<>();
                        dataList.add(txt);
                        upiPaymentOperation(dataList);
                    }else {
                        Log.d("responseUPI","null");
                        ArrayList<String>dataList=new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentOperation(dataList);
                    }
                }
                break;
        }
    }

    private void upiPaymentOperation(ArrayList<String> data) {
        if (isConnectionAvailable(PaymentActivity.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(PaymentActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(PaymentActivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(PaymentActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(PaymentActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}