package com.example.exampleproject;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.exampleproject.BluetoothPrinter.PrintBluetoothActivity;
import com.example.exampleproject.CaptureImage.CapturingImageActivity;
import com.example.exampleproject.GraphicalRep.BubbleChartActivity;
import com.example.exampleproject.GraphicalRep.CandleStickChartActivity;
import com.example.exampleproject.GraphicalRep.GraphicalActivity;
import com.example.exampleproject.GraphicalRep.LineChartActivity;
import com.example.exampleproject.GraphicalRep.PieChartActivity;
import com.example.exampleproject.GraphicalRep.RadarChartActivity;
import com.example.exampleproject.GraphicalRep.ScatteredChartActivity;
import com.example.exampleproject.ImageOneByOne.PickImageActivity;
import com.example.exampleproject.LoadallImage.MainActivity;
import com.example.exampleproject.Map.MapActivity;
import com.example.exampleproject.Payment.PaymentActivity;
import com.example.exampleproject.Sign.SignActivity;
import com.example.exampleproject.Sign.SignMainActivity;
import com.example.exampleproject.databinding.ActivityHomeBinding;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.snackbar.Snackbar;
import com.shasin.notificationbanner.Banner;

import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import es.dmoral.toasty.Toasty;

public class Home extends AppCompatActivity {
    Button all, one, mapText, captureImage, network,graph,pieGraph,
            radar_graph_Home,line_graph_Home,bubbleChart,candle_graph_Home,scattered_graph_Home,pdfCreate;
    LinearLayout linearLayout;
    String dirpath;
    File file;
    WebView  webview;
    PDFView pdfView;
    Button payment,sign;

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        all = findViewById(R.id.all_image);
        one = findViewById(R.id.one_by_one);
        mapText = findViewById(R.id.mapText);
        captureImage = findViewById(R.id.capture_image);
        network = findViewById(R.id.network);
        graph=findViewById(R.id.graph_Home);
        pieGraph=findViewById(R.id.pie_graph_Home);
        radar_graph_Home=findViewById(R.id.radar_graph_Home);
        line_graph_Home=findViewById(R.id.line_graph_Home);
        bubbleChart=findViewById(R.id.bubble_graph_Home);
        candle_graph_Home=findViewById(R.id.candle_graph_Home);
        scattered_graph_Home=findViewById(R.id.scattered_graph_Home);
        pdfCreate=findViewById(R.id.pdf);
//        webview=findViewById(R.id.webview);
        pdfView=findViewById(R.id.pdfView);
        payment=findViewById(R.id.payment);
        sign=findViewById(R.id.sign);

        binding.printBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PrintBluetoothActivity.class));
            }
        });


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PaymentActivity.class));

            }
        });

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        pdfCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPDF();
            }
        });





        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tools.isConnected(getApplicationContext())) {
                    Toasty.success(Home.this, "You have internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toasty.error(Home.this,"No Internet!!",Toast.LENGTH_LONG).show();
                    Banner.make(v,Home.this,Banner.INFO,"not success",Banner.TOP).show();
                    Snackbar snackbar = Snackbar.make(v, "Please connect to network!! ", Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.show();
                }
            }
        });

        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CapturingImageActivity.class));
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PickImageActivity.class));
            }
        });

        mapText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        });
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GraphicalActivity.class));
            }
        });
        pieGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });
        radar_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RadarChartActivity.class));
            }
        });
        line_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LineChartActivity.class));
            }
        });
        bubbleChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BubbleChartActivity.class));
            }
        });
        candle_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CandleStickChartActivity.class));
            }
        });
        scattered_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScatteredChartActivity.class));
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                String smsNumber = "+919605286342"; // E164 format without '+' sign
//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                sendIntent.setType("text/plain");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
//                sendIntent.setPackage("com.whatsapp");
//                if(getPackageManager() == null) {
//                    Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
////                    Toast.makeText(this, "Error/n" , Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                startActivity(sendIntent);

////////////////////////////////////////////////////////////////////////////////////////////////
                PackageManager packageManager = getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone="+ "+919605286342" +"&text=" + URLEncoder.encode("Hai", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }


//                startActivity(new Intent(getApplicationContext(), SignMainActivity.class));
            }
        });

    }


    private void createPDF() {
        PdfDocument pdfDocument=new PdfDocument();
        Paint paint=new Paint();
        PdfDocument.PageInfo myPageinfo=new PdfDocument.PageInfo.Builder(250,400,1).create();
        PdfDocument.Page myPage=pdfDocument.startPage(myPageinfo);
        Canvas canvas=myPage.getCanvas();
        canvas.drawText("welcome Neha",40,50,paint);
        pdfDocument.finishPage(myPage);
//         file=new File(this.getExternalFilesDir("/"),"FirstPDF.pdf");
        file=new File(Environment.getExternalStorageDirectory()+"/FirstPDF.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "PDF Not Created", Toast.LENGTH_SHORT).show();

        }
        pdfDocument.close();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkURI = FileProvider.getUriForFile(this,getPackageName() + ".provider", file);
            intent.setDataAndType(apkURI, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        }
        startActivity(intent);

//        openPDF();

//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setPluginsEnabled(true);
//        webview.getSettings().setAllowFileAccess(true);
//        File file = new File(Environment.getExternalStorageDirectory() + "/test.pdf");

//        final Uri uri = Uri.fromFile(file);

//        webview.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + file.getAbsolutePath() + "#zoom=page-width");


    }

    private void openPDF() {



//        File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/FirstPDF.pdf");
//        Uri path = Uri.fromFile(file);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(path);
//        intent.setType("application/pdf");
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(this, "No application found",
//                    Toast.LENGTH_SHORT).show();
//        }



//        File pdfFile = new File(Environment.getExternalStorageDirectory(),"FirstPDF.pdf");//File path
//        if (pdfFile.exists()) //Checking if the file exists or not
//        {
//            Uri path = Uri.fromFile(pdfFile);
//            Intent objIntent = new Intent(Intent.ACTION_VIEW);
//            objIntent.setDataAndType(path, "application/pdf");
//            objIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(objIntent);//Starting the pdf viewer
//        } else {
//
//            Toast.makeText(this, "The file not exists! ", Toast.LENGTH_SHORT).show();
//
//        }
    }



}