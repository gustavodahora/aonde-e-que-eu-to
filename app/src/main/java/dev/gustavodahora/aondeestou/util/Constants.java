package dev.gustavodahora.aondeestou.util;

import android.Manifest;

public class Constants {
    final static public int COARSE_AND_FINE_LOCATION = 299;
    final static public String[] perms = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
}
