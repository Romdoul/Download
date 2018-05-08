package romdoul.kit.download;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends AppCompatActivity {
    private Button mDownload;
    ProgressDialog dialog = null;
    String imageDownloadUrl = "http://androhub.com/demo/demo.pdf";
    String imageDownloadFile = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mDownload = findViewById(R.id.Button01);
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog = new ProgressDialog.show(this,"", "Downloading...", true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloadFile(imageDownloadUrl);
                    }
                }).start();
            }
        });

    }

    private void downloadFile(String imageUrl) {

        try {
//            File myImageFile = new File(imageFile);
            URL myImageUrl = new URL(imageUrl);
            URLConnection connection = myImageUrl.openConnection();
            int contentLength = connection.getContentLength();
            DataInputStream dataInputStream  = new DataInputStream(myImageUrl.openStream());
            byte[] buffer = new byte[contentLength];
            dataInputStream.readFully(buffer);
            dataInputStream.close();

//            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(myImageFile));
//            dataOutputStream.write(buffer);
//            dataOutputStream.flush();
            dataInputStream.close();
            hideProgressIndicator();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hideProgressIndicator() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }

}
