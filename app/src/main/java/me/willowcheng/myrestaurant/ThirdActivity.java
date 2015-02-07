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


public class ThirdActivity extends ActionBarActivity {

    private static final String GITHUB_URI = "https://github.com/willowcheng";
    private static final String TWITTER_URI = "https://twitter.com/Willow_Cheng";
    
    private static int count_order;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        count_order = 0;
                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

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
        
        final BootstrapButton orderButton = (BootstrapButton) findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase number of orders
                count_order = count_order + 1;
                // Post new order event
                EventBus.getDefault().post(new MessageEvent("New Order!"));
                // Display SnackBar to prompt new order and placed order
                new SnackBar.Builder(ThirdActivity.this)
                        .withMessage("New order generates, placed " + Integer.toString(count_order) + " orders.")
                        .withActionMessage("OK")
                        .withStyle(SnackBar.Style.ALERT)
                        .withDuration(SnackBar.SHORT_SNACK)
                        .show();
            }
        });
        
        final BootstrapButton clearButton = (BootstrapButton) findViewById((R.id.clear_button));
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear current and total placed orders
                EventBus.getDefault().post(new MessageEvent("Clear!"));
                
                new SnackBar.Builder(ThirdActivity.this)
                        .withMessage("Clear all orders!")
                        .withActionMessage("OK")
                        .withStyle(SnackBar.Style.ALERT)
                        .withDuration(SnackBar.SHORT_SNACK)
                        .show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Force count order as 0 if activity is paused
        count_order = 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Different action bar icon will be able to access different activity
        switch (item.getItemId()) {
            case R.id.action_restaurant: {
                Intent intent = new Intent();
                intent.setClass(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_director: {
                Intent intent = new Intent();
                intent.setClass(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.action_login: {
                Intent intent = new Intent();
                intent.setClass(ThirdActivity.this, FourthActivity.class);
                startActivity(intent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
