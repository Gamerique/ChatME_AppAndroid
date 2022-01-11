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
import com.google.firebase.database.FirebaseDatabase;

import ga.gamerique.chatme.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    Boolean validFields = true;
    ActivitySignUpBinding binding;
    private FirebaseAuth fAuth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emptyFields(binding.etNameSignup);
                emptyFields(binding.etEmailSignup);
                emptyFields(binding.etPhoneSignup);
                emptyFields(binding.etPasswordSignup);

                if (validFields && binding.etPhoneSignup.length() ==10) {


                    fAuth.createUserWithEmailAndPassword
                            (binding.etEmailSignup.getText().toString(), binding.etPasswordSignup.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        String phoneNum = "+91"+binding.etPhoneSignup.getText().toString();
                                        Users user = new Users(binding.etNameSignup.getText().toString(), binding.etEmailSignup.getText().toString(), phoneNum, binding.etPasswordSignup.getText().toString());

                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(user);

                                        Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        Toast.makeText(SignUpActivity.this, (CharSequence) task.getException(), Toast.LENGTH_SHORT).show();
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