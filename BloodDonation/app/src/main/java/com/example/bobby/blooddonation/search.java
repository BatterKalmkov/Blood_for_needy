package com.example.bobby.blooddonation;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bobby.blooddonation.activity.LoginActivity2;
import com.example.bobby.blooddonation.activity.availabledonors;
import com.example.bobby.blooddonation.app.MyApplication;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner1,spinner2,spinner3;
    Button b1;
    public  static String item,district,bloodgroup;
    AlertDialog b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        b1=(Button)findViewById(R.id.button8) ;

        spinner1 = (Spinner) findViewById(R.id.spinner11);
        spinner2 = (Spinner) findViewById(R.id.spinner12);
        spinner3 = (Spinner) findViewById(R.id.spinner21);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select State-");
        categories.add("Andaman and Nicobar Islands");
        categories.add("Andhra Pradesh");
        categories.add("Arunachal Pradesh");
        categories.add("Assam");
        categories.add("Bihar");
        categories.add("Chhattisgarh");
        categories.add("Goa");
        categories.add("Gujarat");
        categories.add("Haryana");
        categories.add("Himachal Pradesh");
        categories.add("Jammu and Kashmir");
        categories.add("Jharkhand");
        categories.add("Karnataka");
        categories.add("Kerala");
        categories.add("Madhya Pradesh");
        categories.add("Maharashtra");
        categories.add("Manipur");
        categories.add("Meghalaya");
        categories.add("Mizoram");
        categories.add("Nagaland");
        categories.add("New Delhi");
        categories.add("Odisha");
        categories.add("Puducherry");
        categories.add("Punjab");
        categories.add("Rajasthan");
        categories.add("Sikkim");
        categories.add("Tamil Nadu");
        categories.add("Telangana");
        categories.add("Tripura");
        categories.add("Uttar Pradesh");
        categories.add("Uttarakhand");
        categories.add("West Bengal");

        // Creating adapter for spinner

        List<String> categories2 = new ArrayList <String>();
        categories2.add("Select District-");

        List<String> categories3 = new ArrayList <String>();
        categories3.add("Select Blood Group-");
        categories3.add("A+");
        categories3.add("A-");
        categories3.add("B+");
        categories3.add("B-");
        categories3.add("O+");
        categories3.add("O-");
        categories3.add("AB+");
        categories3.add("AB-");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter <String> dataAdapter2 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories2);
        ArrayAdapter <String> dataAdapter3 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(district.equals("Select District-") ||item.equals("Select State-") || bloodgroup.equals("Select Blood Group-") )
                {
                    Toast.makeText(search.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(search.this,LoginActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.toString().contains("spinner11")) {
            item = parent.getItemAtPosition(position).toString();

            if (item.equals("Andhra Pradesh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Anantapur");
                categories2.add("Chittoor");
                categories2.add("East Godavari");
                categories2.add("Guntur");
                categories2.add("Kadapa");
                categories2.add("Krishna");
                categories2.add("Kurnool");
                categories2.add("Nellore");
                categories2.add("Prakasam");
                categories2.add("Srikakulam");
                categories2.add("Visakhapatnam");
                categories2.add("Vizianagaram");
                categories2.add("West Godavari");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Arunachal Pradesh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Anjaw");
                categories2.add("Changlang");
                categories2.add("East Kameng");
                categories2.add("East Siang");
                categories2.add("Lohit");
                categories2.add("Longding");
                categories2.add("Lower Subansiri");
                categories2.add("Papum Pare");
                categories2.add("Tawang");
                categories2.add("Tirap");
                categories2.add("Lower Dibang Valley");
                categories2.add("Upper Siang");
                categories2.add("Upper Subansiri");
                categories2.add("West Kameng");
                categories2.add("West Siang");
                categories2.add("Upper Dibang Valley");
                categories2.add("Kurung Kumey");
                categories2.add("Namsai");
                categories2.add("Kra Daadi");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Assam")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Barpeta");
                categories2.add("Bongaigaon");
                categories2.add("Cachar");
                categories2.add("Darrang");
                categories2.add("Dhemaji");
                categories2.add("Dhubri");
                categories2.add("Dibrugarh");
                categories2.add("Goalpara");
                categories2.add("Golaghat");
                categories2.add("Hailakandi");
                categories2.add("Jorhat");
                categories2.add("Karbi Anglong");
                categories2.add("Karimganj");
                categories2.add("Kokrajhar");
                categories2.add("Lakhimpur");
                categories2.add("Morigaon");
                categories2.add("Nagaon");
                categories2.add("Nalbari");
                categories2.add("Dima Hasao");
                categories2.add("Sivasagar");
                categories2.add("Sonitpur");
                categories2.add("Tinsukia");
                categories2.add("Kamrup");
                categories2.add("Kamrup Metropolitan");
                categories2.add("Baksa");
                categories2.add("Udalguri");
                categories2.add("Chirang");
                categories2.add("West Karbi Anglong");
                categories2.add("Biswanath");
                categories2.add("Hojai");
                categories2.add("Charaideo");
                categories2.add("South Salmara-Mankachar");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Bihar")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Araria");
                categories2.add("Arwal");
                categories2.add("Aurangabad");
                categories2.add("Banka");
                categories2.add("Begusarai");
                categories2.add("Bhagalpur");
                categories2.add("Bhojpur");
                categories2.add("Buxar");
                categories2.add("Darbhanga");
                categories2.add("East Champaran");
                categories2.add("Gaya");
                categories2.add("Gopalganj");
                categories2.add("Jamui");
                categories2.add("Jehanabad");
                categories2.add("Khagaria");
                categories2.add("Kishanganj");
                categories2.add("Kaimur");
                categories2.add("Katihar");
                categories2.add("Lakhisarai");
                categories2.add("Madhubani");
                categories2.add("Munger");
                categories2.add("Madhepura");
                categories2.add("Muzaffarpur");
                categories2.add("Nalanda");
                categories2.add("Nawada");
                categories2.add("Patna");
                categories2.add("Purnia");
                categories2.add("Rohtas");
                categories2.add("Saharsa");
                categories2.add("Samastipur");
                categories2.add("Sheohar");
                categories2.add("Sheikhpura");
                categories2.add("Saran");
                categories2.add("Sitamarhi");
                categories2.add("Supaul");
                categories2.add("Siwan");
                categories2.add("Vaishali");
                categories2.add("West Champaran");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Chhattisgarh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add(0,"Select district");
                categories2.add("Balod");
                categories2.add("Baloda Bazar");
                categories2.add("Balrampur");
                categories2.add("Bastar");
                categories2.add("Bemetara");
                categories2.add("Bijapur");
                categories2.add("Bilaspur");
                categories2.add("Dantewada");
                categories2.add("Dhamtari");
                categories2.add("Durg");
                categories2.add("Gariaband");
                categories2.add("Janjgir-Champa");
                categories2.add("Jashpur");
                categories2.add("Kabirdham");
                categories2.add("Kanker");
                categories2.add("Kondagaon");
                categories2.add("Korba");
                categories2.add("Koriya");
                categories2.add("Mahasamund");
                categories2.add("Mungeli");
                categories2.add("Narayanpur");
                categories2.add("Raigarh");
                categories2.add("Raipur");
                categories2.add("Rajnandgaon");
                categories2.add("Sukma");
                categories2.add("Surajpur");
                categories2.add("Surguja");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Goa")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("North Goa");
                categories2.add("South Goa");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Gujarat")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ahmedabad");
                categories2.add("Amreli");
                categories2.add("Anand");
                categories2.add("Aravalli");
                categories2.add("Banaskantha");
                categories2.add("Bharuch");
                categories2.add("Bhavnagar");
                categories2.add("Botad");
                categories2.add("Chhota Udaipur");
                categories2.add("Dahod");
                categories2.add("Dang");
                categories2.add("Devbhoomi Dwarka");
                categories2.add("Gandhinagar");
                categories2.add("Gir Somnath");
                categories2.add("Jamnagar");
                categories2.add("Junagadh");
                categories2.add("Kutch");
                categories2.add("Kheda");
                categories2.add("Mahisagar");
                categories2.add("Mehsana");
                categories2.add("Morbi");
                categories2.add("Narmada");
                categories2.add("Navsari");
                categories2.add("Panchmahal");
                categories2.add("Patan");
                categories2.add("Porbandar");
                categories2.add("Rajkot");
                categories2.add("Sabarkantha");
                categories2.add("Surat");
                categories2.add("Surendranagar");
                categories2.add("Tapi");
                categories2.add("Vadodara");
                categories2.add("Valsad");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Haryana")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ambala");
                categories2.add("Bhiwani");
                categories2.add("Faridabad");
                categories2.add("Fatehabad");
                categories2.add("Gurgaon");
                categories2.add("Hisar");
                categories2.add("Jhajjar");
                categories2.add("Jind");
                categories2.add("Kaithal");
                categories2.add("Karnal");
                categories2.add("Kurukshetra");
                categories2.add("Mahendragarh");
                categories2.add("Mewat");
                categories2.add("Palwal");
                categories2.add("Panchkula");
                categories2.add("Panipat");
                categories2.add("Rewari");
                categories2.add("Rohtak");
                categories2.add("Sirsa");
                categories2.add("Sonipat");
                categories2.add("Yamuna Nagar");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Himachal Pradesh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bilaspur");
                categories2.add("Chamba");
                categories2.add("Hamirpur");
                categories2.add("Kangra");
                categories2.add("Kinnaur");
                categories2.add("Kullu");
                categories2.add("Lahaul and Spiti");
                categories2.add("Mandi");
                categories2.add("Shimla");
                categories2.add("Sirmaur");
                categories2.add("Solan");
                categories2.add("Una");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Jammu and Kashmir")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Jammu");
                categories2.add("Doda");
                categories2.add("Kishtwar");
                categories2.add("Rajouri");
                categories2.add("Reasi");
                categories2.add("Udhampur");
                categories2.add("Ramban");
                categories2.add("Kathua");
                categories2.add("Samba");
                categories2.add("Poonch");
                categories2.add("Srinagar");
                categories2.add("Anantnag");
                categories2.add("Kulgam");
                categories2.add("Pulwama");
                categories2.add("Shopian");
                categories2.add("Budgam");
                categories2.add("Ganderbal");
                categories2.add("Bandipora");
                categories2.add("Baramulla");
                categories2.add("Kupwara");
                categories2.add("Kargil");
                categories2.add("Leh");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Jharkhand")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Garhwa");
                categories2.add("Palamu");
                categories2.add("Latehar");
                categories2.add("Chatra");
                categories2.add("Hazaribagh");
                categories2.add("Koderma");
                categories2.add("Giridih");
                categories2.add("Ramgarh");
                categories2.add("Bokaro");
                categories2.add("Dhanbad district");
                categories2.add("Lohardaga");
                categories2.add("Gumla");
                categories2.add("Simdega");
                categories2.add("Ranchi");
                categories2.add("Khunti");
                categories2.add("West Singhbhum");
                categories2.add("Saraikela Kharsawan");
                categories2.add("East Singhbhum");
                categories2.add("Jamtara");
                categories2.add("Deoghar");
                categories2.add("Dumka");
                categories2.add("Pakur");
                categories2.add("Godda");
                categories2.add("Sahebganj");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Karnataka"))
            {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bagalkot");
                categories2.add("Bengaluru Urban");
                categories2.add("Bengaluru Rural");
                categories2.add("Belagavi");
                categories2.add("Bellary");
                categories2.add("Bidar");
                categories2.add("Vijayapura");
                categories2.add("Chamarajanagar");
                categories2.add("Chikballapur");
                categories2.add("Chikkamagaluru");
                categories2.add("Chitradurga");
                categories2.add("Dakshina Kannada");
                categories2.add("Davanagere");
                categories2.add("Dharwad");
                categories2.add("Gadag");
                categories2.add("Kalaburagi");
                categories2.add("Hassan");
                categories2.add("Haveri");
                categories2.add("Kodagu");
                categories2.add("Kolar");
                categories2.add("Koppal");
                categories2.add("Mandya");
                categories2.add("Mysuru");
                categories2.add("Raichur");
                categories2.add("Ramanagara");
                categories2.add("Shivamogga");
                categories2.add("Tumakuru");
                categories2.add("Udupi");
                categories2.add("Uttara Kannada");
                categories2.add("Yadgir");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Kerala")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Alappuzha");
                categories2.add("Ernakulam");
                categories2.add("Idukki");
                categories2.add("Kannur");
                categories2.add("Kasaragod");
                categories2.add("Kollam");
                categories2.add("Kottayam");
                categories2.add("Kozhikode");
                categories2.add("Malappuram");
                categories2.add("Palakkad");
                categories2.add("Pathanamthitta");
                categories2.add("Thiruvananthapuram");
                categories2.add("Thrissur");
                categories2.add("Wayanad");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Madhya Pradesh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bhopal district");
                categories2.add("Raisen district");
                categories2.add("Rajgarh district");
                categories2.add("Sehore district");
                categories2.add("Vidisha district");
                categories2.add("Morena district");
                categories2.add("Sheopur district");
                categories2.add("Bhind district");
                categories2.add("Gwalior district");
                categories2.add("Ashoknagar district");
                categories2.add("Shivpuri district");
                categories2.add("Datia district");
                categories2.add("Guna district");
                categories2.add("Alirajpur district");
                categories2.add("Barwani district");
                categories2.add("Burhanpur district ");
                categories2.add("Dhar district");
                categories2.add("Indore district");
                categories2.add("Jhabua district");
                categories2.add("Khandwa district ");
                categories2.add("Khargone district ");
                categories2.add("Balaghat district");
                categories2.add("Chhindwara district");
                categories2.add("Jabalpur district");
                categories2.add("Katni district");
                categories2.add("Mandla district");
                categories2.add("Dindori district");
                categories2.add("Narsinghpur district");
                categories2.add("Seoni district");
                categories2.add("Betul district");
                categories2.add("Harda district");
                categories2.add("Hoshangabad district");
                categories2.add("Rewa district");
                categories2.add("Satna district");
                categories2.add("Sidhi district");
                categories2.add("Singrauli district");
                categories2.add("Chhatarpur district");
                categories2.add("Damoh district");
                categories2.add("Panna district");
                categories2.add("Sagar district");
                categories2.add("Tikamgarh district");
                categories2.add("Anuppur district");
                categories2.add("Shahdol district");
                categories2.add("Umaria district");
                categories2.add("Agar Malwa district");
                categories2.add("Dewas district");
                categories2.add("Mandsaur district");
                categories2.add("Neemuch district");
                categories2.add("Ratlam district");
                categories2.add("Shajapur district");
                categories2.add("Ujjain district");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Maharashtra")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Akola");
                categories2.add("Amravati");
                categories2.add("Buldana");
                categories2.add("Yavatmal");
                categories2.add("Washim");
                categories2.add("Aurangabad");
                categories2.add("Beed");
                categories2.add("Hingoli");
                categories2.add("Jalna");
                categories2.add("Latur");
                categories2.add("Nanded");
                categories2.add("Osmanabad");
                categories2.add("Parbhani");
                categories2.add("Mumbai");
                categories2.add("Mumbai Suburban District");
                categories2.add("Thane");
                categories2.add("Palghar");
                categories2.add("Raigad");
                categories2.add("Ratnagiri");
                categories2.add("Sindhudurg");
                categories2.add("Bhandara");
                categories2.add("Chandrapur");
                categories2.add("Gadchiroli");
                categories2.add("Gondia");
                categories2.add("Nagpur");
                categories2.add("Wardha");
                categories2.add("Ahmednagar");
                categories2.add("Dhule");
                categories2.add("Jalgaon");
                categories2.add("Nandurbar");
                categories2.add("Nashik");
                categories2.add("Kolhapur");
                categories2.add("Pune");
                categories2.add("Sangli");
                categories2.add("Satara");
                categories2.add("Solapur");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Manipur")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bishnupur");
                categories2.add("Churachandpur");
                categories2.add("Chandel");
                categories2.add("Imphal East");
                categories2.add("Senapati");
                categories2.add("Tamenglong");
                categories2.add("Thoubal");
                categories2.add("Ukhrul");
                categories2.add("Imphal West");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Meghalaya")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("West Jaintia Hills (Jowai)");
                categories2.add("East Jaintia Hills (Khliehriat)");
                categories2.add("East Khasi Hills (Shillong)");
                categories2.add("West Khasi Hills (Nongstoin)");
                categories2.add("South West Khasi Hills (Mawkyrwat)");
                categories2.add("Ri-Bhoi (Nongpoh)");
                categories2.add("North Garo Hills (Resubelpara)");
                categories2.add("East Garo Hills (Williamnagar)");
                categories2.add("South Garo Hills (Baghmara)");
                categories2.add("West Garo Hills (Tura)");
                categories2.add("South West Garo Hills (Ampati)");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Mizoram")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Aizawl");
                categories2.add("Kolasib");
                categories2.add("Lawngtlai");
                categories2.add("Lunglei");
                categories2.add("Mamit");
                categories2.add("Siaha");
                categories2.add("Serchhip");
                categories2.add("Champhai");
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Nagaland")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Dimapur");
                categories2.add("Kiphire");
                categories2.add("Kohima");
                categories2.add("Longleng");
                categories2.add("Mokokchung");
                categories2.add("Mon");
                categories2.add("Peren");
                categories2.add("Phek");
                categories2.add("Tuensang");
                categories2.add("Wokha");
                categories2.add("Zunheboto");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Odisha")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Angul");
                categories2.add("Balangir");
                categories2.add("Balasore");
                categories2.add("Bargarh");
                categories2.add("Bhadrak");
                categories2.add("Boudh");
                categories2.add("Cuttack");
                categories2.add("Debagarh");
                categories2.add("Dhenkanal");
                categories2.add("Gajapati");
                categories2.add("Ganjam");
                categories2.add("Jagatsinghpur");
                categories2.add("Jajpur");
                categories2.add("Jharsuguda");
                categories2.add("Kalahandi");
                categories2.add("Kandhamal");
                categories2.add("Kendrapara");
                categories2.add("Kendujhar");
                categories2.add("Khordha");
                categories2.add("Koraput");
                categories2.add("Malkangiri");
                categories2.add("Mayurbhanj");
                categories2.add("Nabarangapur");
                categories2.add("Nayagarh");
                categories2.add("Nuapada");
                categories2.add("Puri");
                categories2.add("Rayagada");
                categories2.add("Sambalpur");
                categories2.add("Subarnapur");
                categories2.add("Sundergarh");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Punjab")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Amritsar");
                categories2.add("Barnala");
                categories2.add("Bathinda");
                categories2.add("Firozpur");
                categories2.add("Faridkot");
                categories2.add("Fatehgarh Sahib");
                categories2.add("Fazilka");
                categories2.add("Gurdaspur");
                categories2.add("Hoshiarpur");
                categories2.add("Jalandhar");
                categories2.add("Kapurthala");
                categories2.add("Ludhiana");
                categories2.add("Mansa");
                categories2.add("Moga");
                categories2.add("Sri Muktsar Sahib");
                categories2.add("Pathankot");
                categories2.add("Patiala");
                categories2.add("Rupnagar");
                categories2.add("Sahibzada Ajit Singh Nagar");
                categories2.add("Sangrur");
                categories2.add("Shahid Bhagat Singh Nagar");
                categories2.add("Tarn Taran");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Rajasthan")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ajmer");
                categories2.add("Alwar");
                categories2.add("Banswara");
                categories2.add("Baran");
                categories2.add("Barmer");
                categories2.add("Bharatpur");
                categories2.add("Bhilwara");
                categories2.add("Bikaner");
                categories2.add("Bundi");
                categories2.add("Chittorgarh");
                categories2.add("Churu");
                categories2.add("Dausa");
                categories2.add("Dholpur");
                categories2.add("Dungarpur");
                categories2.add("Hanumangarh");
                categories2.add("Jaipur");
                categories2.add("Jaisalmer");
                categories2.add("Jalor");
                categories2.add("Jhalawar");
                categories2.add("Jhunjhunu");
                categories2.add("Jodhpur");
                categories2.add("Karauli");
                categories2.add("Kota");
                categories2.add("Nagaur");
                categories2.add("Pali");
                categories2.add("Pratapgarh");
                categories2.add("Rajsamand");
                categories2.add("Sawai Madhopur");
                categories2.add("Sikar");
                categories2.add("Sirohi");
                categories2.add("Sri Ganganagar");
                categories2.add("Tonk");
                categories2.add("Udaipur");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Sikkim")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("East Sikkim");
                categories2.add("North Sikkim");
                categories2.add("South Sikkim");
                categories2.add("West Sikkim");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Tamil Nadu")) {
                List<String>
                        categories2 = new ArrayList<String>();
                categories2.add("Ariyalur");
                categories2.add("Karur");
                categories2.add("Nagapattinam");
                categories2.add("Perambalur");
                categories2.add("Pudukkottai");
                categories2.add("Thanjavur");
                categories2.add("Tiruchirappalli");
                categories2.add("Tiruvarur");
                categories2.add("Dharmapuri");
                categories2.add("Coimbatore");
                categories2.add("Erode");
                categories2.add("Krishnagiri");
                categories2.add("Namakkal");
                categories2.add("The Nilgiris");
                categories2.add("Salem");
                categories2.add("Tiruppur");
                categories2.add("Dindigul");
                categories2.add("Kanyakumari");
                categories2.add("Madurai");
                categories2.add("Ramanathapuram");
                categories2.add("Sivaganga");
                categories2.add("Theni");
                categories2.add("Thoothukudi");
                categories2.add("Tirunelveli");
                categories2.add("Virudhunagar");
                categories2.add("Chennai");
                categories2.add("Cuddalore");
                categories2.add("Kanchipuram");
                categories2.add("Tiruvallur");
                categories2.add("Tiruvannamalai");
                categories2.add("Vellore");
                categories2.add("Viluppuram");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Telangana")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Adilabad");
                categories2.add("Hyderabad");
                categories2.add("Karimnagar");
                categories2.add("Khammam");
                categories2.add("Mahbubnagar");
                categories2.add("Medak");
                categories2.add("Nalgonda");
                categories2.add("Nizamabad");
                categories2.add("Ranga Reddy");
                categories2.add("Warangal");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Tripura")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Dhalai");
                categories2.add("Sipahijala");
                categories2.add("Khowai");
                categories2.add("Gomati");
                categories2.add("Unakoti");
                categories2.add("North Tripura");
                categories2.add("South Tripura");
                categories2.add("West Tripura");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Uttar Pradesh")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Agra");
                categories2.add("Firozabad");
                categories2.add("Mainpuri");
                categories2.add("Mathura");
                categories2.add("Aligarh");
                categories2.add("Etah");
                categories2.add("Hathras");
                categories2.add("Kasganj");
                categories2.add("Allahabad");
                categories2.add("Fatehpur");
                categories2.add("Kaushambi");
                categories2.add("Pratapgarh");
                categories2.add("Azamgarh");
                categories2.add("Ballia");
                categories2.add("Mau");
                categories2.add("Budaun");
                categories2.add("Bareilly");
                categories2.add("Pilibhit");
                categories2.add("Shahjahanpur");
                categories2.add("Basti");
                categories2.add("Sant Kabir Nagar");
                categories2.add("Siddharthnagar");
                categories2.add("Banda");
                categories2.add("Chitrakoot");
                categories2.add("Hamirpur");
                categories2.add("Mahoba");
                categories2.add("Bahraich");
                categories2.add("Balarampur");
                categories2.add("Gonda");
                categories2.add("Shravasti");
                categories2.add("Ambedkar Nagar");
                categories2.add("Amethi");
                categories2.add("Barabanki");
                categories2.add("Faizabad");
                categories2.add("Sultanpur");
                categories2.add("Deoria");
                categories2.add("Gorakhpur");
                categories2.add("Kushinagar");
                categories2.add("Maharajganj");
                categories2.add("Jalaun");
                categories2.add("Jhansi");
                categories2.add("Lalitpur");
                categories2.add("Auraiya");
                categories2.add("Etawah");
                categories2.add("Farrukhabad");
                categories2.add("Kannauj");
                categories2.add("Kanpur Dehat");
                categories2.add("Kanpur Nagar");
                categories2.add("Hardoi");
                categories2.add("Lakhimpur Kheri");
                categories2.add("Lucknow");
                categories2.add("Raebareli");
                categories2.add("Sitapur");
                categories2.add("Unnao");
                categories2.add("Bagpat");
                categories2.add("Bulandshahr");
                categories2.add("Gautam Buddha Nagar");
                categories2.add("Ghaziabad");
                categories2.add("Hapur");
                categories2.add("Meerut");
                categories2.add("Mirzapur");
                categories2.add("Sant Ravidas Nagar");
                categories2.add("Sonbhadra");
                categories2.add("Amroha");
                categories2.add("Bijnor");
                categories2.add("Moradabad");
                categories2.add("Rampur");
                categories2.add("Sambhal");
                categories2.add("Muzaffarnagar");
                categories2.add("Saharanpur");
                categories2.add("Shamli");
                categories2.add("Chandauli");
                categories2.add("Ghazipur");
                categories2.add("Jaunpur");
                categories2.add("Varanasi");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Uttarakhand")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Almora");
                categories2.add("Bageshwar");
                categories2.add("Chamoli");
                categories2.add("Champawat");
                categories2.add("Dehradun");
                categories2.add("Haridwar");
                categories2.add("Nainital");
                categories2.add("Pauri Garhwal");
                categories2.add("Pithoragarh");
                categories2.add("Rudraprayag");
                categories2.add("Tehri Garhwal");
                categories2.add("Udham Singh Nagar");
                categories2.add("Uttarkashi");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("West Bengal")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Alipurduar");
                categories2.add("Bankura");
                categories2.add("Bardhaman");
                categories2.add("Birbhum");
                categories2.add("Cooch Behar");
                categories2.add("Darjeeling");
                categories2.add("East Midnapore");
                categories2.add("Hooghly");
                categories2.add("Howrah");
                categories2.add("Jalpaiguri");
                categories2.add("Kolkata");
                categories2.add("Malda");
                categories2.add("Murshidabad");
                categories2.add("Nadia");
                categories2.add("North 24 Parganas");
                categories2.add("North Dinajpur");
                categories2.add("Purulia");
                categories2.add("South 24 Parganas");
                categories2.add("South Dinajpur");
                categories2.add("West Midnapore");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Andaman and Nicobar Islands")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("North and Middle Andaman district");
                categories2.add("South Andaman district");
                categories2.add("Nicobar district");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("New Delhi")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("New Delhi");
                categories2.add("North Delhi");
                categories2.add("North West Delhi");
                categories2.add("West Delhi");
                categories2.add("South West Delhi");
                categories2.add("South Delhi");
                categories2.add("South East Delhi");
                categories2.add("Central Delhi");
                categories2.add("North East Delhi");
                categories2.add("Shahdara");
                categories2.add("East Delhi");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Puducherry")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Karaikal");
                categories2.add("Mahe");
                categories2.add("Puducherry");
                categories2.add("Yanam");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select district");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Select district");
                java.util.Collections.sort(categories2, Collator.getInstance());

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
        }
        else if(parent.toString().contains("spinner12")) {
            district=parent.getItemAtPosition(position).toString();
        }
        else
        {
            bloodgroup=parent.getItemAtPosition(position).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
