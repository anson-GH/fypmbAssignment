package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {
    TextView textView;
    static Image image;
    Bitmap bitmap;static byte[] bArray;String mPath;
    private Button btnTakeScreenshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        btnTakeScreenshot= (Button) findViewById(R.id.btnTakeScreenshot);
        btnTakeScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });

    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
             mPath = Environment.getExternalStorageDirectory().toString() + File.separator + now + ".PNG";
            final RelativeLayout v1 =(RelativeLayout)findViewById(R.id.reportrelative);
            // create bitmap screen capture
          //  View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
             bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] byteArray=stream.toByteArray();
            convertPDF(byteArray);

//            Document document=new Document();
//            PdfWriter.getInstance(document,new FileOutputStream("YourPDFHere.pdf"));
//            document.open();
//            Image image = Image.getInstance (mPath);
//            document.add(new Paragraph("Your Heading for the Image Goes Here"));
//            document.add(image);
//            document.close();


            MediaScannerConnection.scanFile(this,
                    new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

       //   openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
    public void convertPDF(byte[] path)
    {
        Document document=new Document();
        try {

            try {
                document = new Document();
                String dirpath= Environment.getExternalStorageDirectory().toString();
                PdfWriter.getInstance(document,new FileOutputStream(dirpath+"/example.pdf")); //  Change pdf's name.
                document.open();
                Image img =Image.getInstance(mPath);  // Change image's name and extension.

                float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                        - document.rightMargin() - 0) / img.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                img.scalePercent(scaler);
                img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
                //img.setAlignment(Image.LEFT| Image.TEXTWRAP);

 /* float width = document.getPageSize().width() - document.leftMargin() - document.rightMargin();
 float height = document.getPageSize().height() - document.topMargin() - document.bottomMargin();
 img.scaleToFit(width, height)*/

                        document.add(img);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}