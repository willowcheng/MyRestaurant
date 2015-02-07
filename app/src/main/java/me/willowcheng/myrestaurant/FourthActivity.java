package me.willowcheng.myrestaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.github.mrengineer13.snackbar.SnackBar;


public class FourthActivity extends ActionBarActivity {

    private static final String GITHUB_URI = "https://github.com/willowcheng";
    private static final String TWITTER_URI = "https://twitter.com/Willow_Cheng";

    private AlertDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        
        // Initial dialog builder
        mBuilder = new AlertDialog.Builder(this);
        setContentView(R.layout.activity_fourth);
        
        final BootstrapEditText username = (BootstrapEditText) findViewById(R.id.username);
        final BootstrapEditText password = (BootstrapEditText) findViewById(R.id.password);

        // Set Github button to view website in browser
        final BootstrapButton githubButton = (BootstrapButton) findViewById(R.id.github_button);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URI));
                startActivity(intent);
            }
        });

        // Set Twitter button to view website in browser
        final BootstrapButton twitterButton = (BootstrapButton) findViewById(R.id.twitter_button);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, (Uri.parse(TWITTER_URI)));
                startActivity(intent);
            }
        });

        final BootstrapButton registerButton = (BootstrapButton) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                // Build dialog to prompt welcome message
                mBuilder.setIcon(R.drawable.ic_launcher)
                        .setTitle("Peng Lai")
                        .setMessage("Welcome to Peng Lai restaurant, I'm pleasure on your serve. Enjoy your nice time!").
                        setPositiveButton("Dismiss",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog,
                                                        int id) {
                                        dialog.dismiss();
                                    }
                                });
                // Show dialog
                mBuilder.create().show();
                
                // Clear text
                username.setText("");
                password.setText("");

                // Hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
            }
        });
        
        final BootstrapButton loginButton = (BootstrapButton) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear text
                username.setText("");
                password.setText("");

                // Hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                
                // Use SnackBar to prompt login information
                new SnackBar.Builder(FourthActivity.this)
                        .withMessage("Login Successful! ")
                        .withActionMessage("CONFIRM")
                        .withStyle(SnackBar.Style.CONFIRM)
                        .withDuration(SnackBar.MED_SNACK)
                        .show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_restaurant: {
                Intent intent = new Intent();
                intent.setClass(FourthActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_director: {
                Intent intent = new Intent();
                intent.setClass(FourthActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.action_menu: {
                Intent intent = new Intent();
                intent.setClass(FourthActivity.this, ThirdActivity.class);
                startActivity(intent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
