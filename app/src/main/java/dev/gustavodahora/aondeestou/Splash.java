package dev.gustavodahora.aondeestou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.gustavodahora.aondeestou.util.Constants;
import pub.devrel.easypermissions.EasyPermissions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startNextActivity();
    }

    public void startNextActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (hasPermissions()) {
                startActivity(new Intent(Splash.this, MainActivity.class));
            } else {
                startActivity(new Intent(Splash.this, PermissionActivity.class));
            }
        }, 1000);
    }

    public boolean hasPermissions() {
        return EasyPermissions.hasPermissions(this, Constants.perms);
    }
}