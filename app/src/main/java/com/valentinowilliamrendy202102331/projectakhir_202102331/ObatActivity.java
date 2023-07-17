package com.valentinowilliamrendy202102331.projectakhir_202102331;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ObatActivity extends AppCompatActivity {
    EditText kode, nama, jenis, golongan, stok;
    Button simpan, tampil, edit, hapus;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);

        kode = findViewById(R.id.kodeobt);
        nama = findViewById(R.id.namaobt);
        jenis = findViewById(R.id.jenisobt);
        golongan = findViewById(R.id.golonganobt);
        stok = findViewById(R.id.stokobt);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        edit = findViewById(R.id.btnedit);
        hapus = findViewById(R.id.btnhapus);

        DB = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String k = kode.getText().toString();
                String nm = nama.getText().toString();
                String jo = jenis.getText().toString();
                String gol = golongan.getText().toString();
                String st = stok.getText().toString();

                if (TextUtils.isEmpty(k) || TextUtils.isEmpty(nm) || TextUtils.isEmpty(jo) || TextUtils.isEmpty(gol) || TextUtils.isEmpty(st))
                    Toast.makeText(ObatActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                else {
                    Boolean cekkodeOBAT= DB.cekkode(k);
                    if (cekkodeOBAT == false){
                        Boolean insert = DB.insertDataOBAT(k,nm,jo,gol,st);
                        if (insert == true){
                            Toast.makeText(ObatActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ObatActivity.this,"Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ObatActivity.this,"Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.tampilDataOBAT();
                if (res.getCount()==0){
                    Toast.makeText(ObatActivity.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ObatActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Stok Obat");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kb = kode.getText().toString();
                Boolean cekHapusDataOBAT = DB.hapusDataOBAT(kb);
                if (cekHapusDataOBAT == true)
                    Toast.makeText(ObatActivity.this, "Data Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ObatActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String k_ = kode.getText().toString();
                String nm_ = nama.getText().toString();
                String jo_ = jenis.getText().toString();
                String gol_ = golongan.getText().toString();
                String st_ = stok.getText().toString();

                if (TextUtils.isEmpty(k_) || TextUtils.isEmpty(nm_) || TextUtils.isEmpty(jo_) || TextUtils.isEmpty(gol_) || TextUtils.isEmpty(st_))
                    Toast.makeText(ObatActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                else {
                    Boolean edit = DB.editDataOBAT(k_,nm_,jo_,gol_,st_);
                    if (edit == false){
                        Toast.makeText(ObatActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ObatActivity.this,"Data gagal diedit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}