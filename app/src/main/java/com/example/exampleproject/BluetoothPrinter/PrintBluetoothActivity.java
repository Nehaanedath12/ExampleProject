package com.example.exampleproject.BluetoothPrinter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.exampleproject.databinding.ActivityPrintBluetoothBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class PrintBluetoothActivity extends AppCompatActivity {

    ActivityPrintBluetoothBinding binding;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    boolean stopWorker=false;
    int readBufferPosition=0;
    byte[] readBuffer;
    Thread workerThread;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrintBluetoothBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        createPDF();

        binding.connection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    findBT();
                    openBT();
                } catch (Exception ex) {
                }
            }
        });
        // send data typed by the user to be printed
        binding.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    sendData();
                } catch (IOException ex) {
                }
            }
        });

        // close bluetooth connection
        binding.close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    closeBT();
                } catch (IOException ex) {
                }
            }
        });



    }

    private void createPDF() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo myPageinfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(myPageinfo);
        Canvas canvas = myPage.getCanvas();
        canvas.drawText("pdf testing ", 40, 50, paint);
        pdfDocument.finishPage(myPage);
//         file=new File(this.getExternalFilesDir("/"),"FirstPDF.pdf");
        file = new File(Environment.getExternalStorageDirectory() + "/FirstPDF.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "PDF Not Created", Toast.LENGTH_SHORT).show();

        }
        pdfDocument.close();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Uri apkURI = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
//            intent.setDataAndType(apkURI, "application/pdf");
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//        }
//        startActivity(intent);
    }

    private void sendData() throws IOException {
        try {

            // the text typed by the user
            //String msg = myTextbox.getText().toString();
            //msg += "\n";

            InputStream is = this.openFileInput("FirstPDF.pdf"); // Where this is Activity
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int bytesRead;
            while (( bytesRead = is.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byte[] bytes = bos.toByteArray();

            byte[] printformat = { 27, 33, 0 }; //try adding this print format

            mmOutputStream.write(printformat);
            mmOutputStream.write(bytes);

            // tell the user data were sent
            binding.myLabel.setText("Data Sent");

            closeBT();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            binding.close.setText("Bluetooth Closed");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openBT()throws IOException {
        try {
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();

            binding.myLabel.setText("Bluetooth Opened");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void findBT() {

        try {

            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


            if (mBluetoothAdapter == null) {
//                myLabel.setText("No bluetooth adapter available");
                Toast.makeText(PrintBluetoothActivity.this, "No bluetooth  available", Toast.LENGTH_LONG).show();
                // startActivity(new Intent(MainActivity.this,NewAct.class));
            }

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
                Toast.makeText(this, "OPen", Toast.LENGTH_LONG).show();

                //startActivity(new Intent(MainActivity.this,NewAct.class));
            }


            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
                    .getBondedDevices();

            if (pairedDevices.size() > 0) {


                for (BluetoothDevice device : pairedDevices) {

                    // MP300 is the name of the bluetooth printer device

                    Log.d("BluetoothDevice",device.getName()+"");
//                    if (device.getName().equals("vivo 1929")) {
                        //openBT();
                        mmDevice = device;
                        break;
//                    }
//                    else {
//
//                    }
                }
            }
            {
                binding.myLabel.setText("Bluetooth Device Found");
                try {
                    // Standard SerialPortService ID
                    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
                    mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
                    mmSocket.connect();
                    mmOutputStream = mmSocket.getOutputStream();
                    mmInputStream = mmSocket.getInputStream();

                    beginListenForData();

                    binding.myLabel.setText("Bluetooth Opened");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // This is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted()
                            && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length);
                                        final String data = new String(
                                                encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        handler.post(new Runnable() {
                                            public void run() {
                                                binding.myLabel.setText(data);
                                            }
                                        });
                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}