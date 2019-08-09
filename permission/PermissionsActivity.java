package com.trung.example.demoapp.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.M)
public class PermissionsActivity extends Activity
{
    public static final String EXTRA_PERMISSIONS = "permissions";

    public static PermissionHandler permissionHandler = null;

    private ArrayList<String> permissions = null;
    private ArrayList<String> deniedPermissions = null;
    private ArrayList<String> noRationaleList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);

        Intent intent = getIntent();
        if (intent == null || intent.hasExtra(EXTRA_PERMISSIONS) == false)
        {
            finish();
            return;
        }

        deniedPermissions = new ArrayList<>();

        permissions = (ArrayList<String>) intent.getSerializableExtra(EXTRA_PERMISSIONS);
        for (String permission : permissions)
        {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
            {
                deniedPermissions.add(permission);
            }
        }

        if (deniedPermissions.isEmpty())
        {
            grant();
            return;
        }

        requestPermissions(toArray(deniedPermissions), 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if (grantResults.length == 0)
        {
            deny();
        }
        else
        {
            deniedPermissions.clear();
            for (int i = 0; i < grantResults.length; i++)
            {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.size() == 0)
            {
                grant();
            }
            else
            {
                deny();
            }
        }
    }

    private String[] toArray(ArrayList<String> arrayList)
    {
        int size = arrayList.size();
        String[] array = new String[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = arrayList.get(i);
        }

        return array;
    }

    @Override
    public void finish()
    {
        permissionHandler = null;
        super.finish();
    }

    private void deny()
    {
        PermissionHandler permissionHandler = PermissionsActivity.permissionHandler;
        if (permissionHandler != null)
        {
            permissionHandler.onDenied(deniedPermissions);
        }
        finish();
    }

    private void grant()
    {
        PermissionHandler permissionHandler = PermissionsActivity.permissionHandler;
        if (permissionHandler != null)
        {
            permissionHandler.onGranted();
        }
        finish();
    }
}
