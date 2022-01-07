package ga.gamerique.chatme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText sName, sEmail, sPhone, sOTP, sPassword;
    Button btnGetOTP, btnResendOtp, btnSignUp;
    Boolean detailsValid =true;
    String otpVerificationId;

    FirebaseAuth fAuth;
    // PhoneAuthCredential phnCredentials;
    PhoneAuthProvider.ForceResendingToken token;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks phnCallbacks;


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
        btnResendOtp = findViewById(R.id.btnResendOTP);

        fAuth = FirebaseAuth.getInstance();


        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP("+91"+sPhone.getText().toString());
            }
        });
        btnResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOTP("+91"+sPhone.getText().toString());
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validating Data(Checking that non of field is Empty
                checkingEmptyFields(sName);
                checkingEmptyFields(sEmail);
                checkingEmptyFields(sPhone);
                checkingEmptyFields(sOTP);
                checkingEmptyFields(sPassword);

                if (detailsValid){
                    // Send OTP to the user and after OTP Verification Register that user in Database.

                    String otp = sOTP.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpVerificationId, otp);

                    verifyAuthentication(credential);


                    fAuth.createUserWithEmailAndPassword(sEmail.getText().toString(), sPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            // Now send the user to MainActivity
                            phnCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);

                                    otpVerificationId = s;
                                    token = forceResendingToken;
                                    btnResendOtp.setVisibility(View.GONE);
                                }

                                @Override
                                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                                    super.onCodeAutoRetrievalTimeOut(s);

                                    btnResendOtp.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    // When Verification Compleated.
                                    verifyAuthentication(phoneAuthCredential);
                                    btnResendOtp.setVisibility(View.GONE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    // When Verification Failed.
                                    Toast.makeText(SignUpActivity.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();

                                }
                            };

                            sendOTP("+91"+sPhone.getText().toString());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }


    public void sendOTP(String PhoneNo){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(PhoneNo, 30, TimeUnit.SECONDS, this, phnCallbacks);
    }

    public void resendOTP(String PhoneNo){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(PhoneNo, 30, TimeUnit.SECONDS, this, phnCallbacks, token);
    }


    public void checkingEmptyFields(EditText field){
        if (field.getText().toString().isEmpty()){
            field.setError("Required");
            detailsValid = false;
        }
        else {
            detailsValid = true;
        }
    }

    public void verifyAuthentication(PhoneAuthCredential credential){
        FirebaseAuth.getInstance().getCurrentUser().linkWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignUpActivity.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                //Sending user to Dashboard (MainActivity)
                Intent dashboard = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(dashboard);

            }
        });
    }
}