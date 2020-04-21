package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    EditText etID;
    Button btnSubmit;
    TextView tvInfo;

    public boolean validateDate(String strDate)
    {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
        sdfrmt.setLenient(false);
        boolean l = true;
        try
        {
            Date javaDate = sdfrmt.parse(String.valueOf(strDate));
            System.out.println(strDate+" is valid date format");
        }

        catch (ParseException e)
        {
            System.out.println(strDate+" is Invalid Date format");
            l=false;
        }

        return l;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.etID);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvInfo = findViewById(R.id.tVInfo);


        etID.addTextChangedListener(new TextWatcher() {

            @Override


            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 13)
                    etID.setError("Length Should be 13");
                else{
                    etID.setError(null);
                }
            }
        });
        tvInfo.setText(null);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etID.getText())){
                    Toast.makeText(MainActivity.this,"No Data!",
                            Toast.LENGTH_LONG).show();
                    tvInfo.setText(R.string.nV);
                }
                else {

                    String idNumber = etID.getText().toString().trim();
                    int ch = Integer.parseInt(idNumber.substring(2, 4));
                    int ch1 = Integer.parseInt(idNumber.substring(4, 6));
                    int yy = Integer.parseInt("20" + idNumber.substring(0, 2));
                    int mm = Integer.parseInt(idNumber.substring(2, 4));
                    int dd = Integer.parseInt(idNumber.substring(4, 6));
                    boolean f = false;
                    boolean g = false;
                    Date strDate;
                    Boolean l = false;
                    String dob = idNumber.substring(0, 6);
                    String gender;
                    Boolean d = false;
                    String citizen = null;
                    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                    int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
                            27, 28, 29, 30, 31};
                    char t;
                    String text = "";
                    int mF = Integer.parseInt(String.valueOf(idNumber.charAt(6)));

                    if (mF < 5) {
                        gender = "Female";
                    } else {
                        gender = "Male";
                    }

                    t = idNumber.charAt(10);
                    if (t == '0') {
                        citizen = "SA Citizen";
                    } else if (t == '1') {
                        citizen = "Permanent Citizen!";
                    } else {
                        Toast.makeText(MainActivity.this, "Check Nationality!",
                                Toast.LENGTH_LONG).show();
                        tvInfo.setText(R.string.nV);
                    }
                    String datum = String.valueOf(mm) + "/" + String.valueOf(dd) + "/" + String.valueOf(yy);
                    Log.d("date", datum);
                    l = validateDate(datum);
                    Log.d("date", l.toString());
                    if (l) {
                        text = getString(R.string.dob) + dob + "\n" +
                                getString(R.string.gen) + gender + "\n" +
                                getString(R.string.ant) + citizen;
                    } else {
                        Toast.makeText(MainActivity.this, "Enter Valid Date!",
                                Toast.LENGTH_LONG).show();
                        tvInfo.setText(R.string.nV);
                    }
                    for (int element : arr) {
                        if (element == ch) {
                            f = true;
                        }
                    }
                    for (int element : arr1) {
                        if (element == ch1) {
                            g = true;
                        }
                    }


                    if (!f) {
                        Toast.makeText(MainActivity.this, "Enter Valid Month!",
                                Toast.LENGTH_LONG).show();
                        tvInfo.setText(R.string.nV);
                    } else if (!g) {
                        Toast.makeText(MainActivity.this, "Enter Valid Date!",
                                Toast.LENGTH_LONG).show();
                        tvInfo.setText(R.string.nV);
                    } else {
                        tvInfo.setText(text);
                    }
                }




            }
        });


    }
}
