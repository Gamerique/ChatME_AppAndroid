package ga.gamerique.chatme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText sName, sEmail, sPhone, sOTP, sPassword;
    Button btnGetOTP, btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sName = findViewById(R.id.etNameSignup);
        sEmail = findViewById(R.id.etEmailSignup);
        sPhone = findViewById(R.id.etPhoneSignup);
        sOTP = findViewById(R.id.etOTP);
        sPassword = findViewById(R.id.etPasswordSignup);
        btnGetOTP = findViewById(R.id.btnOTP);
        btnSignUp = findViewById(R.id.btnSignup);

    }
}