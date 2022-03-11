package com.examples.popularmoviesapp.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ActivityUtils {
    public static void replaceFragmentInActivity(FragmentManager fragmentManager, Fragment fragment,int containerId){

        fragmentManager.beginTransaction().replace(containerId,fragment).commit();
    }
}
