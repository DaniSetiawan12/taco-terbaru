package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teco.History;
import com.example.teco.R;

import Pengguna.Menu_Gerbang;
import Pengguna.Menu_Pintu;
import User.MenuLogin;

public class menu_Admin extends AppCompatActivity {

    private Button buttonpin,buttonger,buttonhis ;
    TextView Logout;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__admin);
        getSupportActionBar().hide();

        buttonpin = (Button)findViewById(R.id.btnPintu);
        buttonger = (Button)findViewById(R.id.btnGerbang);
        buttonhis = (Button)findViewById(R.id.btnHistory);
        Logout  = (TextView)findViewById(R.id.Logout);

        buttonpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu_Admin.this, Menu_Pintu.class);
                startActivity(i);
                finish();
            }
        });
        buttonger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu_Admin.this, Menu_Gerbang.class);
                startActivity(i);
                finish();
            }
        });
        buttonhis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu_Admin.this, History.class);
                startActivity(i);
                finish();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu_Admin.this, MenuLogin.class);
                startActivity(i);
                finish();
            }
        });

    }

}