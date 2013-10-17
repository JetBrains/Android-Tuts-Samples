package com.example.MenuDroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

public class AnotherActivity extends Activity {

    private boolean autoSavePeriodically = false;
    private boolean autoSaveUponExit = false;
    private CheckBox checkBoxMore;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.another);

        // More initialization
        checkBoxMore = (CheckBox) findViewById(R.id.largeMenuOn);
    }
        /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.another, menu);
        return true;
    }                        */

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        Boolean requiresExtendedMenu =  checkBoxMore.isChecked();
        int menuId = requiresExtendedMenu ?R.menu.anotherextended :R.menu.another;

        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menuId, menu);

        if (requiresExtendedMenu) {
            MenuItem mi1 = menu.findItem(R.id.anotherMenuItemAutoSavePeriodically);
            mi1.setChecked(autoSavePeriodically);
            MenuItem mi2 = menu.findItem(R.id.anotherMenuItemAutoSaveUponExit);
            mi2.setChecked(autoSaveUponExit);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.anotherMenuItemAutoSavePeriodically:
                autoSavePeriodically = !item.isChecked();
                item.setChecked(autoSavePeriodically);
                return true;
            case R.id.anotherMenuItemAutoSaveUponExit:
                autoSaveUponExit = !item.isChecked();
                item.setChecked(autoSaveUponExit);
                return true;
            case R.id.anotherMenuItemShare:
                Toast.makeText(this, "Sharing content ...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.groupShareFacebook:
                Toast.makeText(this, "Sharing content to Facebook ...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.groupShareTwitter:
                Toast.makeText(this, "Sharing content to Twitter ...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.groupShareGooglePlus:
                Toast.makeText(this, "Sharing content to Google+ ...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
