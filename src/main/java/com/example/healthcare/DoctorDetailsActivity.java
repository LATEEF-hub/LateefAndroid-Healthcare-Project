package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Adesina Remi ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Lotanna Han ", "Hospital Address : Poltova", "Exp : 6yrs", "Mobile No: 707 455 2234", "600"},
                    {"Doctor Name : Olga Ugo ", "Hospital Address : Airport Road", "Exp : 20yrs", "Mobile No: 237 095 0054", "1000"},
                    {"Doctor Name : May Anna ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Tetiana Svelta ", "Hospital Address : Olympiska", "Exp : 5yrs", "Mobile No: 987 455 1234", "500"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Basirat Adesina ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : January Han ", "Hospital Address : Poltova", "Exp : 6yrs", "Mobile No: 707 455 2234", "600"},
                    {"Doctor Name : Olga Ugo ", "Hospital Address : Airport Road", "Exp : 20yrs", "Mobile No: 237 095 0054", "1000"},
                    {"Doctor Name : Anthony Anna ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Olabanji Svelta ", "Hospital Address : Olympiska", "Exp : 5yrs", "Mobile No: 987 455 1234", "500"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Hoodie Ann ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Anna Ola Han ", "Hospital Address : Poltova", "Exp : 6yrs", "Mobile No: 707 455 2234", "600"},
                    {"Doctor Name : Sunkanmi Angelina ", "Hospital Address : Airport Road", "Exp : 20yrs", "Mobile No: 237 095 0054", "1000"},
                    {"Doctor Name : Marina Anna ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Tetiana Svelta ", "Hospital Address : Olympiska", "Exp : 5yrs", "Mobile No: 987 455 1234", "500"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Brenda Michael ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Chloe Cahill ", "Hospital Address : Poltova", "Exp : 6yrs", "Mobile No: 707 455 2234", "600"},
                    {"Doctor Name : Brian Cahill ", "Hospital Address : Airport Road", "Exp : 20yrs", "Mobile No: 237 095 0054", "1000"},
                    {"Doctor Name : Anthony Anna ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Tetiana Svelta ", "Hospital Address : Olympiska", "Exp : 5yrs", "Mobile No: 987 455 1234", "500"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Linker Obi ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Mariana Hut ", "Hospital Address : Poltova", "Exp : 6yrs", "Mobile No: 707 455 2234", "600"},
                    {"Doctor Name : Inna Lot ", "Hospital Address : Airport Road", "Exp : 20yrs", "Mobile No: 237 095 0054", "1000"},
                    {"Doctor Name : Sveltana Anna ", "Hospital Address : Kyiv Oblast", "Exp : 10yrs", "Mobile No: 987 455 1234", "700"},
                    {"Doctor Name : Sope Opera ", "Hospital Address : Olympiska", "Exp : 5yrs", "Mobile No: 987 455 1234", "500"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;  //declare the object here
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();

        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        //Create object of the LIST
        list = new ArrayList();
        // HERE I USE FOR loop for the HASHMAP
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String,String>();
            item.put( "line1", doctor_details[i][0]);
            item.put( "line2", doctor_details[i][1]);
            item.put( "line3", doctor_details[i][2]);
            item.put( "line4", doctor_details[i][3]);
            item.put( "line5", "Cons Fees:"+doctor_details[i][4]+"€");
            list.add(item);
            
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"}, // FROM
                new  int[]{R.id.line_a, R.id.line_b,R.id.line_c,R.id.line_d, R.id.line_e});
        // {TO} This is Mapping| We only map the TV and button not the ListView
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}