package com.example.CoolDroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity {

    private TextView message;
    private ImageView droid;
    private View.OnClickListener droidTapListener;
    private int counter = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        initializeApp();
    }

    private void initializeApp() {
        message = (TextView) findViewById(R.id.message);
        droid = (ImageView) findViewById(R.id.imageView);

        droidTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                touchDroid();
            }
        };
        droid.setOnClickListener(droidTapListener);
    }

    private void touchDroid() {
        counter++;
        String temp = getStringForDisplay(counter);
        message.setText(String.format("You touched the droid %s", temp));
    }

    private String getStringForDisplay(int count) {
        String temp;
        switch (count) {
            case 1:
                temp = "once";

                break;
            case 2:
                temp = "twice";
                break;
            default:
                temp = String.format("%d times", count);
        }

        return temp;
    }


}
