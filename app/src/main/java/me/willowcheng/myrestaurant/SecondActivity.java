package me.willowcheng.myrestaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class SecondActivity extends ActionBarActivity {

    private static final String GITHUB_URI = "https://github.com/willowcheng";
    private static final String TWITTER_URI = "https://twitter.com/Willow_Cheng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        // Apply roboto font to director name
        TextView txt = (TextView) findViewById(R.id.director_name);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        txt.setTypeface(font);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Different action bar icon will be able to access different activity
        switch (item.getItemId()) {
            case R.id.action_restaurant: {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_menu: {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.action_login: {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, FourthActivity.class);
                startActivity(intent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
