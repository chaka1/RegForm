package com.example.chaka.regform;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chaka.regform.obj.Person;


public class ConfrimActivity extends Activity {

    ImageView mPhoto;
    TextView mName;
    TextView mNI;
    TextView mPassport;
    TextView mGender;
    Button mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim);

        // Fetching data from a parcelable object passed from MainActivity
        Person person = getIntent().getParcelableExtra("person");

        mPhoto = (ImageView)findViewById(R.id.imageView2);
        mName = (TextView)findViewById(R.id.textView2);
        mNI = (TextView)findViewById(R.id.textView3);
        mPassport = (TextView)findViewById(R.id.textView4);
        mGender = (TextView)findViewById(R.id.textView5);
        mBack = (Button)findViewById(R.id.button3);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(person!=null){
            mName.setText(person.getName());
            mNI.setText(person.getNi());
            mPassport.setText(person.getPassport());
            mGender.setText(person.getGender());
        }

        Bitmap bitmap = getIntent().getParcelableExtra("BitmapImage");

        if(bitmap!=null){
            mPhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confrim, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
