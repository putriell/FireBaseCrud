package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText mNameEditText, mMatkulEditText, mUpdateNameEditText, mUpdateMatkulEditText;

    DatabaseReference mDatabaseReferenceStudent, mDatabaseReferenceTeacher;
    Student mStudent;
    Teacher mTeacher;
    //untuk menyimpan key
    String key, key2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReferenceStudent = FirebaseDatabase.getInstance().getReference(Student.class.getSimpleName());
        mDatabaseReferenceTeacher = FirebaseDatabase.getInstance().getReference(Teacher.class.getSimpleName());

        mNameEditText = findViewById(R.id.name_edittext);
        mMatkulEditText = findViewById(R.id.matkul_edittext);
        mUpdateNameEditText = findViewById(R.id.update_name_edittext);
        mUpdateMatkulEditText = findViewById(R.id.update_address_edittext);

        findViewById(R.id.insert_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataStudent();
            }
        });

        findViewById(R.id.read_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readDataStudent();
            }
        });

        findViewById(R.id.update_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataStudent();
            }
        });

        findViewById(R.id.delete_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataStudent();
            }
        });

        //TEACHER
        findViewById(R.id.insert_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataTeacher();
            }
        });
        findViewById(R.id.read_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readDataTeacher();
            }
        });
        findViewById(R.id.update_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataTeacher();
            }
        });

        findViewById(R.id.delete_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataTeacher();
            }
        });

    }



    private void insertDataStudent() {
        Student newStudent = new Student();
        String name = mNameEditText.getText().toString();
        String matkul = mMatkulEditText.getText().toString();
        if(name != "" && matkul != ""){
            newStudent.setName(name);
            newStudent.setMatkul(matkul);

            mDatabaseReferenceStudent.push().setValue(newStudent);
            Toast.makeText(this, "Successfully insert data!", Toast.LENGTH_SHORT).show();
        }
    }

    private void readDataStudent() {
        mStudent = new Student();
        mDatabaseReferenceStudent.addListenerForSingleValueEvent(new ValueEventListener() {
            //data snapshot adl data yang disimpan
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               //apakah punya childern atau tidak
                if (snapshot.hasChildren()) {
                    for (DataSnapshot currentData : snapshot.getChildren()){
                        key = currentData.getKey();
                        mStudent.setName(currentData.child("name").getValue().toString());
                        mStudent.setMatkul(currentData.child("matkul").getValue().toString());

                    }
                }

                //menampilkan di edittext yang kedua
                mUpdateNameEditText.setText(mStudent.getName());
                mUpdateMatkulEditText.setText(mStudent.getMatkul());
                Toast.makeText(MainActivity.this, "Data has been shown!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void updateDataStudent() {
        //untuk mengupdate, keep dulu id nya
        Student updateData = new Student();
        updateData.setName(mUpdateNameEditText.getText().toString());
        updateData.setMatkul(mUpdateMatkulEditText.getText().toString());
        Toast.makeText(MainActivity.this, "Data telah diupdate!", Toast.LENGTH_SHORT).show();

        mDatabaseReferenceStudent.child(key).setValue(updateData);
//
//        //menghapus data
//        mDatabaseReference.child(key).removeValue();
    }

    private void deleteDataStudent(){
        Student updateData = new Student();
        updateData.setName(mUpdateNameEditText.getText().toString());
        updateData.setMatkul(mUpdateMatkulEditText.getText().toString());
        Toast.makeText(MainActivity.this, "Data telah dihapus!", Toast.LENGTH_SHORT).show();
        //menghapus data
        mDatabaseReferenceStudent.child(key).removeValue();
    }

    private void insertDataTeacher() {
        Teacher newTeacher = new Teacher();
        String name = mNameEditText.getText().toString();
        String matkul = mMatkulEditText.getText().toString();
        if(name != "" && matkul != ""){
            newTeacher.setName(name);
            newTeacher.setMatkul(matkul);

            mDatabaseReferenceTeacher.push().setValue(newTeacher);
            Toast.makeText(this, "Successfully insert data!", Toast.LENGTH_SHORT).show();
        }

    }

    private void readDataTeacher() {
        mTeacher = new Teacher();
        mDatabaseReferenceTeacher.addListenerForSingleValueEvent(new ValueEventListener() {
            //data snapshot adl data yang disimpan
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //apakah punya childern atau tidak
                if (snapshot.hasChildren()) {
                    for (DataSnapshot currentData : snapshot.getChildren()){
                        key2 = currentData.getKey();
                        mTeacher.setName(currentData.child("name").getValue().toString());
                        mTeacher.setMatkul(currentData.child("matkul").getValue().toString());

                    }
                }

                //menampilkan di edittext yang kedua
                mUpdateNameEditText.setText(mTeacher.getName());
                mUpdateMatkulEditText.setText(mTeacher.getMatkul());
                Toast.makeText(MainActivity.this, "Data has been shown!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateDataTeacher() {
        //untuk mengupdate, keep dulu id nya
        Teacher updateDataTeacher = new Teacher();
        updateDataTeacher.setName(mUpdateNameEditText.getText().toString());
        updateDataTeacher.setMatkul(mUpdateMatkulEditText.getText().toString());
        Toast.makeText(MainActivity.this, "Data telah diupdate!", Toast.LENGTH_SHORT).show();

        mDatabaseReferenceTeacher.child(key2).setValue(updateDataTeacher);
//
//        //menghapus data
//        mDatabaseReference.child(key).removeValue();
    }

    private void deleteDataTeacher(){
        Teacher updateDataTeacher = new Teacher();
        updateDataTeacher.setName(mUpdateNameEditText.getText().toString());
        updateDataTeacher.setMatkul(mUpdateMatkulEditText.getText().toString());
        Toast.makeText(MainActivity.this, "Data telah dihapus!", Toast.LENGTH_SHORT).show();
        //menghapus data
        mDatabaseReferenceTeacher.child(key2).removeValue();
    }
}