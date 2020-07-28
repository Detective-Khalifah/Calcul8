package hub.programs.calcul8;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashScreenFor2Seconds();
    }

    private void splashScreenFor2Seconds() {
        //The following code will execute after the 2 seconds.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //The following code will execute after the 2 seconds.
                try {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mWaitHandler.postDelayed(runnable, 2000);  // Give a 2 seconds delay.
    }

}