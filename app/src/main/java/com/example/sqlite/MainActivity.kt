package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var username:EditText = findViewById(R.id.name)
        var userYOB:EditText = findViewById(R.id.yob)
        var userEmail:EditText = findViewById(R.id.email)

        var btnSave:Button=findViewById(R.id.save)
        var btnView:Button=findViewById(R.id.view)
        var TVshowRecords:TextView=findViewById(R.id.textView)

        btnSave.setOnClickListener {
            var name = username.text.toString().trim()
            var yob=userYOB.text.toString().trim()
            var email=userEmail.text.toString().trim()

            val databaseHelper = DatabaseHelper(this)
            if(yob!="" && name!="" && email!=""){
                databaseHelper.addRecord(name,yob,email)
                Toast.makeText(this,"Record added to database successfully",Toast.LENGTH_LONG).show()
                username.text.clear()
                userYOB.text.clear()
                userEmail.text.clear()
            } else {
                Toast.makeText(this,"You have an empty field",Toast.LENGTH_LONG).show()
            }
        }

        btnView.setOnClickListener {
            val databaseHelper=DatabaseHelper(this)
            val cursor=databaseHelper.getRecords()
            while(cursor.moveToNext()){
                var name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME_COLUMN))
                var yob = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.YOB_COLUMN))
                var email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.EMAIL_COLUMN))
                TVshowRecords.text="$name, $yob, $email "

            }
            cursor.close()
        }


    }
}