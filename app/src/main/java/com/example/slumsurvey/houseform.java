package com.example.slumsurvey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.FileVisitOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class
houseform extends AppCompatActivity {
    Spinner conhouse,room,toilet,kitchen, yosspinner;
    ImageView cameraBtn12, imageBox12;
    Bitmap bitmap1;
    Button exit;
    EditText area,areabuilt,yearofstaying,consent;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houseform);
        conhouse=findViewById(R.id.conhouse);

        cameraBtn12=findViewById(R.id.cameraBtn12);
        imageBox12=findViewById(R.id.imageBox12);
        imageBox12.setVisibility(View.GONE);
        exit= findViewById(R.id.saveexit);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("forms").child(firebaseAuth.getUid().toString());

        setTitle("");

        // requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        room=findViewById(R.id.room);
        toilet=findViewById(R.id.toilet);
        kitchen=findViewById(R.id.kitchen);
        yosspinner=findViewById(R.id.yosspinner);

        List<String> r_options = new ArrayList<String>();
        r_options.add("0");
        r_options.add("1");
        r_options.add("2");
        r_options.add("3");
        r_options.add("4");

        List<String> options = new ArrayList<String>();
        options.add("0");
        options.add("1");
        options.add("2");

        List<String> option = new ArrayList<String>();
        option.add("Kutcha");
        option.add("Semi-Pakka");
        option.add("Pakka");

        //ADDED
        List<String> yos = new ArrayList<String>();
        yos.add("Less than or equal to 5");
        yos.add("Greater than 5");

        conhouse.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option));

        room.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,r_options));
        toilet.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options));
        kitchen.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options));
        yosspinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,yos));




        cameraBtn12.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

            //imageBox12.setVisibility(View.VISIBLE);
//                Intent cameraIntent23 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent23, 0);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                String id2= addcategory();

                Intent a = new Intent(houseform.this,dashb.class);
                startActivity(a);
                houseform.this.finish();


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageBox12.setVisibility(View.VISIBLE);
            imageBox12.setImageBitmap(imageBitmap);
        }


    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(houseform.this,applicationform.class);
        startActivity(a);
        houseform.this.finish();

    }
    public String  addcategory()
    {

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
        db.child(id).child("houseoffamily").setValue(awad);
        Toast.makeText(houseform.this," House of family added succesfully ",Toast.LENGTH_SHORT).show();


        return id;
    }

}
