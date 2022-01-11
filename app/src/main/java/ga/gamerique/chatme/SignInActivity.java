package ga.gamerique.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ga.gamerique.chatme.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    Boolean validFields = true;
    private FirebaseAuth fAuth;
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyFields(binding.etEmailLogin);
                emptyFields(binding.etPasswordLogin);

                if (validFields){

                    fAuth.signInWithEmailAndPassword(binding.etEmailLogin.getText().toString(), binding.etPasswordLogin.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignInActivity.this, "Welcome Sir!!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }


    public void emptyFields(EditText field){
        if (field.getText().toString().isEmpty()){
            field.setError("Required");
            validFields = false;
        }
        else {
            validFields = true;
        }
    }

}