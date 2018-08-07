package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnGo;
    TextView tvC, tvSC;
    Spinner spn1, spn2;


    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.buttonGo);
        tvC = findViewById(R.id.textViewCategory);
        tvSC = findViewById(R.id.textViewSCategory);
        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);


        alNumbers = new ArrayList<>();
        aaNumbers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNumbers);

        spn2.setAdapter(aaNumbers);


        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] strNumbers;
                int pos = spn1.getSelectedItemPosition();
                alNumbers.clear();

                switch (i) {
                    case 0:
                        strNumbers = getResources().getStringArray(R.array.subcategory1);
                        alNumbers.addAll(Arrays.asList(strNumbers));
//

                        break;

                    case 1:
                        strNumbers = getResources().getStringArray(R.array.subcategory2);
                        alNumbers.addAll(Arrays.asList(strNumbers));
//
                        break;
                }


                aaNumbers.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = spn1.getSelectedItemPosition();
                int pos2 = spn2.getSelectedItemPosition();

                alNumbers.clear();


                String[][] sites = {
                        {
                                "https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
                        }
                };
                String theURL = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent intentNewAct = new Intent(getBaseContext(), WebPage.class);
                intentNewAct.putExtra("URL", theURL);
                startActivity(intentNewAct);

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        int p1 = spn1.getSelectedItemPosition();
        int p2 = spn2.getSelectedItemPosition();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt("first", p1);
        prefEdit.putInt("second", p2);

        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        int thePos = pref.getInt("first", 0);
        int thePos2 = pref.getInt("second", 0);

        spn1.setSelection(thePos);

        alNumbers.clear();

        if (thePos == 0) {
            String[] strSubMenu = getResources().getStringArray(R.array.subcategory1);
            alNumbers.addAll(Arrays.asList(strSubMenu));
        } else if (thePos==1) {
            String[] strSubMenu = getResources().getStringArray(R.array.subcategory2);
            alNumbers.addAll(Arrays.asList(strSubMenu));
        }


        aaNumbers.notifyDataSetChanged();
        spn2.setSelection(thePos2);
    }
}
