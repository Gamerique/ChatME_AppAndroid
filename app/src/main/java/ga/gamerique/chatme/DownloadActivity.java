package ga.gamerique.chatme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DownloadActivity extends AppCompatActivity {

    Button btnSignInPage, btnSignUpPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btnSignInPage= findViewById(R.id.btnSignInPage);
        btnSignUpPage= findViewById(R.id.btnSignUpPage);


        btnSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInPage = new Intent(DownloadActivity.this, SignInActivity.class);
                startActivity(signInPage);
                finish();
            }
        });

        btnSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sugnUpPage = new Intent(DownloadActivity.this, SignUpActivity.class);
                startActivity(sugnUpPage);
                finish();
            }
        });
    }
}