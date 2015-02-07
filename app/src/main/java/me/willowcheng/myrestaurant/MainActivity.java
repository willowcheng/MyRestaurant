package me.willowcheng.myrestaurant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mrengineer13.snackbar.SnackBar;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {

    private static final String GITHUB_URI = "https://github.com/willowcheng";
    private static final String TWITTER_URI = "https://twitter.com/Willow_Cheng";

    private static int count_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register EventBus to listen to message deliver
        EventBus.getDefault().register(this);

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
    }

    @Override
    public void onResume() {
        super.onResume();
        new SnackBar.Builder(this)
                .withMessage("Total placed orders: " + Integer.toString(count_order))
                .withActionMessage("CONFIRM")
                .withStyle(SnackBar.Style.CONFIRM)
                .withDuration(SnackBar.MED_SNACK)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Different action bar icon will be able to access different activity
        switch (item.getItemId()) {
            case R.id.action_director: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_menu: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.action_login: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FourthActivity.class);
                startActivity(intent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        // Unregister EventBus when destroy activity
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    // This method will be called when a MessageEvent is posted
    public void onEvent(MessageEvent event) {
        // Upon receiving new order message, order number will increase by 1
        if (event.message.equals("New Order!"))
            count_order = count_order + 1;
        else if (event.message.equals("Clear!"))
            count_order = 0;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Retrive count information
        if (savedInstanceState.getInt("Count") != 0)
            count_order = savedInstanceState.getInt("Count");
        else count_order = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        // Save count information in case that activity start again
        savedInstanceState.putInt("Count", count_order);
    }

}
