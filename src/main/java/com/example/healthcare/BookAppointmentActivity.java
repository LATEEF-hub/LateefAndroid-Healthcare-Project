package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BookAppointmentActivity extends AppCompatActivity {
    //Creating Objects!!!
    EditText ed1, ed2, ed3, ed4;
    TextView tv;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack; // button for date and time Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);



        tv = findViewById(R.id.textViewAPPPackageName);
        ed1 = findViewById(R.id.editTextAPPFullName);
        ed2 = findViewById(R.id.editTextAPPAddress);
        ed3 = findViewById(R.id.editTextAPPContact);
        ed4 = findViewById(R.id.editTextAPPFees);
        dateButton = findViewById(R.id.buttonAPPDate);
        timeButton = findViewById(R.id.buttonAPPTime);
        btnBook = findViewById(R.id.buttonAPPBooking);
        btnBack = findViewById(R.id.buttonAppBack);


     //Set the edittext listener to NULL(Means not editable)!!! Just display previous activity to
        //next activity
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);


        //Set the intent

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");


        // Then we set/display the variable Data into each values
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons fees: "+fees+"€");

        //DatePicker click event

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateDialog();
            }
        });

        //TimePicker click event

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeDialog();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthcare", null, 2);
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();
                //check if appt exist
                if (db.checkAppointmentExists(username, title+"=> "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                   Toast.makeText(getApplicationContext(),"Appointment already booked",Toast.LENGTH_LONG).show();
                }else{
                    db.addOrder(username,title+" => "+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Appointment Booked!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                }


            }
        });

    }
    //Create function init Datepicker
    private void dateDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateButton.setText(String.valueOf(year)+":"+String.valueOf(month+1)+":"+String.valueOf(dayOfMonth));
            }
        },2022, 0, 15);
        dialog.show();
    }


    //Create function init Timepicker

    private void timeDialog() {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));

            }
        }, 20, 15, false);
        dialog.show();
    }

//
}