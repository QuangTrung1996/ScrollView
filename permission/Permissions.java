package com.trung.example.demoapp.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Permissions
{
    public static void check(Context context, String[] permissions, PermissionHandler handler)
    {
        // Android version < 23
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            handler.onGranted();
        }
        else
        {
            boolean allPermissionProvided = true;
            for (String permission : permissions)
            {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                {
                    allPermissionProvided = false;
                    break;
                }
            }

            if (allPermissionProvided != false)
            {
                handler.onGranted();
            }
            else
            {
                PermissionsActivity.permissionHandler = handler;
                Set<String> permissionsSet = new LinkedHashSet<>();
                Collections.addAll(permissionsSet, permissions);
                ArrayList<String> permissionsList = new ArrayList<String>(permissionsSet);
                Intent intent = new Intent(context, PermissionsActivity.class);
                intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, permissionsList);
                context.startActivity(intent);
            }
        }
    }
}
