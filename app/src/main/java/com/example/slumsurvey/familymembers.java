package com.example.slumsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class familymembers extends AppCompatActivity {
    Spinner genspinner1;
    String gender1,id,s,count;
    Button b1,b2;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familymembers);
        genspinner1=findViewById(R.id.memberspinner);
        setTitle("");
        // requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b2.setVisibility(View.GONE);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("forms").child(firebaseAuth.getUid().toString());


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            id=(String) b.get("id");
            s=(String)b.get("membercount");
            count=(String)b.get("memberno");
            // Toast.makeText(this, j, Toast.LENGTH_SHORT).show();
        }

        if(s.equals(count)==false)
        {
            b1.setVisibility(View.VISIBLE);
        }

        if(s.equals(count))
        {
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.VISIBLE);
        }
       // Toast.makeText(this, j+"  " + s, Toast.LENGTH_SHORT).show();
        List<String> gen = new ArrayList<String>();
        gen.add("Male");
        gen.add("Female");
        gen.add("Other");

        genspinner1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gen));
        genspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //  Toast.makeText(ShowingTimeTable.this, String.valueOf(i), Toast.LENGTH_SHORT).show();

                gender1= genspinner1.getItemAtPosition(i).toString();

                // Showing selected spinner item
                //Toast.makeText(applicationform.this, g Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                addcategory();
                int v=Integer.parseInt(count);
                v=v+1;
                Intent a = new Intent(familymembers.this,familymembers.class);
                a.putExtra("membercount",  s);
                a.putExtra("id", id);
                a.putExtra("memberno", String.valueOf(v));
                startActivity(a);
               familymembers.this.finish();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                addcategory();
                Intent a = new Intent(familymembers.this,houseform.class);
                a.putExtra("id", id);
                startActivity(a);
               familymembers.this.finish();

            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(familymembers.this,applicationform.class);
        startActivity(a);
        familymembers.this.finish();

    }
    public String  addcategory()
    {

        Long tt= Long.valueOf(1000000000);
        Long tsLong = System.currentTimeMillis()/1000;
        tsLong=tsLong-tt;
        tsLong=tt-tsLong;
        String id2 = tsLong.toString();

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            id=(String) b.get("id");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());

        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        String month = sdf1.format(new Date());

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
        String day = sdf3.format(new Date());
        SimpleDateFormat sdf4 = new SimpleDateFormat("HH");
        String hours = sdf4.format(new Date());
        SimpleDateFormat sdf5 = new SimpleDateFormat("mm");
        String min = sdf5.format(new Date());
        String currentDateandTime=day+"-"+month+"-"+year+"  "+ hours+":"+min;


        addnott awad =new addnott( "cgvhb","ccx","slum");
        db.child(id).child("members").child(id2).setValue(awad);
        Toast.makeText(familymembers.this," Member of family added succesfully ",Toast.LENGTH_SHORT).show();


        return id;
    }
}
