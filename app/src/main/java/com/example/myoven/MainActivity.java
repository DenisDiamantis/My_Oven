package com.example.myoven;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    Button UpDown;
    Button HotAir;
    Button Grill;
    Button Defrost;
    ImageButton info1;
    ImageButton info2;
    ImageButton info3;
    ImageButton info4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpDown=findViewById(R.id.button);
        HotAir=findViewById(R.id.button2);
        Grill=findViewById(R.id.button3);
        Defrost=findViewById(R.id.button4);
        info1=findViewById(R.id.imageButton4);
        info2=findViewById(R.id.imageButton5);
        info3=findViewById(R.id.imageButton6);
        info4=findViewById(R.id.imageButton7);
        UpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMode("Λειτουργία: Επάνω/Κάτω ψήσιμο");
            }
        });
        HotAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMode("Λειτουργία: Θερμός Αέρας");
            }
        });
        Grill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMode("Λειτουργία: Grill");
            }
        });
        Defrost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMode("Λειτουργία: Ξεπάγωμα");
            }
        });
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(1);
            }

        });
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(2);
            }

        });
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(3);
            }

        });
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(4);
            }

        });
    }

    public void SelectMode(String mode) {
        Intent intent = new Intent(this,Temperature.class);
        intent.putExtra(Temperature.MODE,mode);
        startActivity(intent);
    }
    public void showInfo(int option) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        if(option==1) {
            AndroidUtil.showDialog(this,
                    "Λειτουργία: Επάνω/Κάτω ψήσιμο",
                    "Για κέικ σε φόρμες, γλυκίσματα " +
                            "ταψιού, σουφλέ, μουσακά, παστίτσιο, φαγητά με κρέας " +
                            "από μοσχάρι, χοιρινό, κοτόπουλο, αρνί και θηράματα",
                    "Εντάξει",
                    runnable);
        }else if(option==2){
            AndroidUtil.showDialog(this,
                    "Λειτουργία: Θερμός Αέρας",
                    "Για γλυκά και πίτσα, ζύμη σφολιάτας και" +
                            "μπισκότα κουλουράκια και για ξήρανση",
                    "Εντάξει",
                    runnable);
        }else if(option==3){
            AndroidUtil.showDialog(this,
                    "Λειτουργία: Grill",
                    "Για ψήσιμο στο γκριλ μεγάλων κομματιών κρέατος",
                    "Εντάξει",
                    runnable);
        }
        else{
            AndroidUtil.showDialog(this,
                    "Λειτουργία: Ξεπάγωμα",
                    "Για ξεπάγωμα κρεάτων, γλυκών και πίτσας",
                    "Εντάξει",
                    runnable);
        }
    }
}