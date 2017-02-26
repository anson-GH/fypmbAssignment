package com.example.winnie.fypmbassignment.Crop;

/**
 * Created by MSI on 2017-02-13.
 */

import android.net.Uri;

import java.io.File;

/**
 * @author albin
 * @date 24/6/15
 */
public class Utils {

    public static Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }
}