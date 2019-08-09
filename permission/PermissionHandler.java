package com.trung.example.demoapp.permission;

import android.content.Context;

import java.util.ArrayList;

public abstract class PermissionHandler
{
    public abstract void onGranted();

    public abstract void onDenied(ArrayList<String> deniedPermissions);
}
