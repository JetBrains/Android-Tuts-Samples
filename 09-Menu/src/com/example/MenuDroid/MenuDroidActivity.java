package com.example.MenuDroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuDroidActivity extends Activity {

    private Button buttonJump;
    private ImageView imageDroid;
    private View.OnClickListener jumpTapListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initializeWindow();
        initializeApp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.mainMenuItemSettings:
                return true;
            case R.id.mainMenuItemAbout:
                Toast.makeText(this, "About me ...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        /*
        menu.add(0, v.getId(), 0, "Say 'Hi' to the droid");
        menu.add(0, v.getId(), 0, "Just nothing"); */

        menu.setHeaderTitle("What would you like to do?");
        menu.setHeaderIcon(R.drawable.about);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.droidcontext, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.droidActionDoNothing:
                return true;
            case R.id.droidActionSayHi:
                Toast.makeText(this, "Hello, droid", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    // Private members
    private void initializeWindow() {
        setContentView(R.layout.main);
    }

    private void initializeApp() {
        buttonJump = (Button) findViewById(R.id.buttonConnect);
        jumpTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                launchAnother();
            }
        };
        buttonJump.setOnClickListener(jumpTapListener);

        // Add a context menu trigger to the droid imageview  (long-click)
        imageDroid = (ImageView) findViewById(R.id.imageView);
        registerForContextMenu(imageDroid);
    }

    private void launchAnother() {
        Intent i = new Intent(this, AnotherActivity.class);
        startActivity(i);
    }
}
