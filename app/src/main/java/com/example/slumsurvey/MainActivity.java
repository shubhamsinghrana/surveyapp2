package com.example.slumsurvey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//logn activity
public class MainActivity extends AppCompatActivity {
    Button btnlogin;
    private FirebaseAuth fauth;
    String email,password;
    EditText u,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin=findViewById(R.id.btnlogin);
        p=findViewById(R.id.pass1);
        u=findViewById(R.id.usname);
        fauth=FirebaseAuth.getInstance();
        if (fauth.getCurrentUser()!=null)
        {
            Intent a = new Intent(MainActivity.this,dashb.class);
            startActivity(a);
            MainActivity.this.finish();

        }
        setTitle("");


        // requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {


                if (u.getText().toString().trim().equals("")) {
                    u.setError("This field cannot be empty");
                } else if (u.getText().toString().trim().equals("")) {
                    p.setError("This field cannot be empty");
                } else {
                    fauth.signInWithEmailAndPassword(u.getText().toString(), p.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent a = new Intent(MainActivity.this, dashb.class);
                                startActivity(a);
                                MainActivity.this.finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }
            }
        });




    }
}
