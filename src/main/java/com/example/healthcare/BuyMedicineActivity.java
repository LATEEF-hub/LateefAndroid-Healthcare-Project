package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Paracetamol 500mg Capsule", "", "", "", "25"},
                    {"Metronidazole 500mg Capsule", "", "", "", "100"},
                    {"Vitamin B Complex Capsule", "", "", "", "300"},
                    {"Doxycycline 100mg Capsule", "", "", "", "100"},
                    {"Diabetone 300mg Capsule", "", "", "", "100"},
                    {"Ampiclox 500mg Capsule", "", "", "", "100"},
                    {"Alendronate Acid 500mg Capsule", "", "", "", "100"},
                    {"Chloroquine 100mg Capsule", "", "", "", "100"},
                    {"Tetracycline 500mg Capsule", "", "", "", "50"},

            };
    private String[] package_details ={
            "It helps to reduce Body pain and fever all over the body\n" +
                    "It makes sleep better and reduce headache and fatigue\n" +
                    "It helps with direct and indirect pains",
            "It helps with loose bowels and reduces purging\n" +
                    "used to treat trichomoniasis, a sexually transmitted disease caused by a parasite",
            "Provide reliefs from Vitamin B deficiencies\n" +
                    "It helps with formation of Red blood cells\n" +
                    "Maintain Healthy nervous system",
            "It's use to treats infections including chest infections, dental infections and rosacea\n" +
                    "It prevents Chest and Dental infection",
            "It prevents diabetic complications and acts as adjuvant therapy for diabetic patients\n " +
                    " \n" +
                    "It works by improving insulin sensitivity and preventing nerve damage due to increased blood sugar",
            "Ampiclox capsule is used for the treatment of bacterial infections of the nose, ear, throat, airways, lungs and bone\n" +
                    "It is used to treat urinary tract and respiratory tract infections, meningitis, gonorrhea and infections of the stomach or intestine",
            "Alendronic acid is a type of medicine called a bisphosphonate. Bisphosponates are prescribed to help your bones stay as strong as possible\n" +
                    " \n" +
                    "It can help to strengthen bones, making them less likely to break",
            "Chloroquine phosphate is used to prevent and treat malaria. It is also used to treat amebiasis\n" +
                    "It works by killing the organisms that cause malaria and inflammatory diseases and amebiasis",
            " It works by preventing the growth and spread of bacteria and treat a wide variety of infections, including acne"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnGoToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBMCart);
        btnBack = findViewById(R.id.buttonBMCartBack);
        btnGoToCart = findViewById(R.id.buttonBMCartCheckout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        //We need to create Object of List
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " + packages[i][4] + "€");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] { "line1", "line2", "line3", "line4", "line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
    }
}