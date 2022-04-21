package dev.gustavodahora.aondeestou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.gustavodahora.aondeestou.util.Constants;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    TextView tvValuePermission;
    TextView tvAccept;
    TextView tvDeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        findItems();
        setupClickListener();
    }

    public void findItems() {
        tvAccept = findViewById(R.id.tv_accept);
        tvDeny = findViewById(R.id.tv_deny);
    }

    public void setupClickListener() {
        tvAccept.setOnClickListener(v -> methodRequiresTwoPermission());
        tvDeny.setOnClickListener(v -> callCloseApp());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults,
                this);
    }

    @AfterPermissionGranted(Constants.COARSE_AND_FINE_LOCATION)
    private void methodRequiresTwoPermission() {
        if (EasyPermissions.hasPermissions(this, Constants.perms)) {
            validatePermission();
        } else {
            EasyPermissions.requestPermissions(this, "porque SIM",
                    299, Constants.perms);
        }
    }

    public void validatePermission() {
        if (EasyPermissions.hasPermissions(this, Constants.perms)) {
            startActivity(new Intent(PermissionActivity.this, MainActivity.class));
        } else {
            callCloseApp();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        validatePermission();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        validatePermission();
    }

    public void callCloseApp() {
        Toast.makeText(this, getString(R.string.permission_required), Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            PermissionActivity.this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }, 900);
    }
}