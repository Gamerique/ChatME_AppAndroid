package ga.gamerique.chatme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();

        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(1500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    FirebaseUser currentUser = fAuth.getCurrentUser();
                    if(currentUser != null){
                        Intent dashboard = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(dashboard);
                        finish();
                    }
                    else {
                        Intent downloadPage = new Intent(SplashActivity.this, DownloadActivity.class);
                        startActivity(downloadPage);
                        finish();
                    }
                }

            }
        };thread.start();

    }
}