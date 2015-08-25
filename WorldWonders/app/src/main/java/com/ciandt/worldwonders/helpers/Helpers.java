package com.ciandt.worldwonders.helpers;

import android.content.Context;

/**
 * Created by jfranco on 8/25/15.
 */
public class Helpers {

    /**
     * Get a raw resource from context.
     *
     * @author Jonathan Franco
     * @param context
     * @param rawResourceName
     * @return int
     */
    public static int getRawResourceID(Context context, String rawResourceName) {
        return context.getResources().getIdentifier(rawResourceName, "raw", context.getPackageName());
    }

    /**
     * Get a drawable resource from context.
     *
     * @author Jonathan Franco
     * @param context
     * @param drawableResourceName
     * @return
     */
    public static int getDrawableResourceID(Context context, String drawableResourceName) {
        return context.getResources().getIdentifier(drawableResourceName, "drawable", context.getPackageName());
    }
}
