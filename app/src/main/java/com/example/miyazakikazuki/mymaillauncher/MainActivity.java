package com.example.miyazakikazuki.mymaillauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import static android.provider.Telephony.Mms.Addr.ADDRESS;

public class MainActivity extends AppCompatActivity {
    final int hito = 0;
    final int zeni = 1;
    final int fushi = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 起動時にデータをクリアする
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        Intent intent = getIntent();
        int id = intent.getIntExtra("RadioButton",0);

        ImageView PokemonImageView = (ImageView) findViewById(R.id.imageView);

        switch (id){
            case R.id.radioButton1:
                PokemonImageView.setImageResource(R.drawable.hito);
                break;
            case R.id.radioButton2:
                PokemonImageView.setImageResource(R.drawable.zeni);
                break;
            case R.id.radioButton3:
                PokemonImageView.setImageResource(R.drawable.fushi);
                break;

        }

    }

    public void onSendButtonTapped(View view){
        EditText title = (EditText) findViewById(R.id.editText);
        String subject = String.valueOf(title.getText());
        EditText memo = (EditText) findViewById(R.id.editText2);
        String text = String.valueOf(memo.getText());

        Uri uri = Uri.parse("mailto:" + ADDRESS);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

}
