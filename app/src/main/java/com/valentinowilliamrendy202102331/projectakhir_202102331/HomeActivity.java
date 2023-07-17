package com.valentinowilliamrendy202102331.projectakhir_202102331;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button obat, tampil;
    DBHelper DB;

    private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        obat = findViewById(R.id.obat);
        tampil = findViewById(R.id.btntampil);

        DB = new DBHelper(this);


        obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ObatActivity.class);
                startActivity(intent);
            }
        });

        imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slide1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.tampilDataOBAT();
                if (res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer =  new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Kode Obat     : "+res.getString(0)+"\n");
                    buffer.append("Nama Obat     : "+res.getString(1)+"\n");
                    buffer.append("Jenis Obat    : "+res.getString(2)+"\n");
                    buffer.append("Golongan Obat : "+res.getString(3)+"\n");
                    buffer.append("Stok Obat     : "+res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Stok Obat");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}