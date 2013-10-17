package com.example.DialogDroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class DialogDroidActivity extends Activity {

    private Button buttonYesNo;
    private Button buttonDialog;
    private Button buttonPicker;
    private View.OnClickListener yesNoListener;
    private View.OnClickListener dialogListener;
    private View.OnClickListener pickerListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initializeApp();
    }


    // Private members
    private void initializeApp() {
        buttonYesNo = (Button) findViewById(R.id.buttonYesNo);
        buttonDialog = (Button) findViewById(R.id.buttonDialog);
        buttonPicker = (Button) findViewById(R.id.buttonPicker);
        yesNoListener = new View.OnClickListener() {
            public void onClick(View view) {
                launchYesNo();
            }
        };
        buttonYesNo.setOnClickListener(yesNoListener);
        dialogListener = new View.OnClickListener() {
            public void onClick(View view) {
                launchDialogBox();
            }
        };
        buttonDialog.setOnClickListener(dialogListener);
        pickerListener = new View.OnClickListener() {
            public void onClick(View view) {
                launchDatePicker();
            }
        };
        buttonPicker.setOnClickListener(pickerListener);
    }

    private void launchYesNo() {
        String message = getString(R.string.labelMessageBoxBody);
        String title = getString(R.string.labelMessageBoxTitle);
        String yes = getString(R.string.labelYes);
        String no = getString(R.string.labelNo);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        youClickedYes();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void youClickedYes() {
        Toast.makeText(this, "You clicked YES", Toast.LENGTH_LONG).show();
    }

    private void launchDialogBox() {
        // Creation
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.newmatch);
        dialog.setTitle("New match...");

        // UI initialization
        final EditText home = (EditText) dialog.findViewById(R.id.home);
        home.setText("Home");
        final EditText visitors = (EditText) dialog.findViewById(R.id.visitors);
        visitors.setText("Visitors");

        // Buttons
        Button save = (Button) dialog.findViewById(R.id.ok);
        // if button is clicked, just close the custom dialog
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Access here data the user entered
                Toast.makeText(DialogDroidActivity.this,
                        home.getText() + " - " + visitors.getText(),
                        Toast.LENGTH_LONG)
                     .show();
                dialog.dismiss();
            }
        });

        // Display
        dialog.show();
    }

    private void launchDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create the actual date picker component
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker picker, int year, int month, int day) {
                Calendar c =  Calendar.getInstance();
                c.set(year, month, day);
                String nameOfMonth = String.format(Locale.US,"%tB", c);
                showToast(String.format("%d %s %d", day, nameOfMonth, year));
            }
        };
        DatePickerDialog picker = new DatePickerDialog(this, dateSetListener, year, month, day);
        picker.show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
