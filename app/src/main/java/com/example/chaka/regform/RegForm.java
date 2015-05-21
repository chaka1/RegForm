package com.example.chaka.regform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chaka.regform.obj.Person;
import com.iangclifton.android.floatlabel.FloatLabel;


public class RegForm extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    FloatLabel mName;
    FloatLabel mNI;
    FloatLabel mPass;
    RadioButton mMale;
    RadioButton mFemale;
    RadioGroup mGender;
    ImageView mImage;

    Bitmap bitmap = null;

    Button mOK;
    Button mReset;

    String imageLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);

        mName = (FloatLabel) findViewById(R.id.float_label_1);
        mNI = (FloatLabel) findViewById(R.id.float_label_2);
        mPass = (FloatLabel) findViewById(R.id.float_label_3);

        mMale = (RadioButton)findViewById(R.id.radioButton);
        mFemale = (RadioButton)findViewById(R.id.radioButton2);
        mGender = (RadioGroup)findViewById(R.id.radioGender);

        mImage = (ImageView)findViewById(R.id.imageView);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        mOK = (Button)findViewById(R.id.button);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(mGender.getCheckedRadioButtonId()==R.id.radioButton){
                    gender = "male";
                }else{
                    gender = "female";
                }
                Person person = new Person(mName.getEditText().getText().toString(),
                        mNI.getEditText().getText().toString(),
                        mPass.getEditText().getText().toString(),
                        gender);

                // Creating an intent to open the activity StudentViewActivity
                Intent intent = new Intent(getBaseContext(), ConfrimActivity.class);

                // Passing data as a parecelable object to StudentViewActivity
                intent.putExtra("person",person);
                intent.putExtra("BitmapImage", bitmap);
                // Opening the activity
                startActivity(intent);

            }
        });
        mReset = (Button)findViewById(R.id.button2);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.getEditText().setText("");
                mNI.getEditText().setText("");
                mPass.getEditText().setText("");
                mGender.clearCheck();
                mImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_account_box));
                bitmap = null;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reg_form, menu);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name",mName.getEditText().getText().toString());
        outState.putString("ni",mNI.getEditText().getText().toString());
        outState.putString("pass",mPass.getEditText().getText().toString());
        outState.putInt("gender", mGender.getCheckedRadioButtonId());
        outState.putParcelable("bitmap",bitmap);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mName.getEditText().setText(savedInstanceState.getString("name"));
        mNI.getEditText().setText(savedInstanceState.getString("ni"));
        mPass.getEditText().setText(savedInstanceState.getString("pass"));
        bitmap = savedInstanceState.getParcelable("bitmap");
        if(bitmap!=null) {
            mImage.setImageBitmap(bitmap);
        }
        mGender.check(savedInstanceState.getInt("gender"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            mImage.setImageBitmap(bitmap);
        }
    }
}
