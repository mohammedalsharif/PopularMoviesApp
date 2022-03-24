package com.examples.popularmoviesapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.TypeConverter;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import coil.Coil;
import coil.ComponentRegistry;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import kotlin.coroutines.Continuation;
import retrofit2.http.Url;

public class ActivityUtils {
    public static void replaceFragmentInActivity(FragmentManager fragmentManager, Fragment fragment,int containerId){
        fragmentManager.beginTransaction().replace(containerId,fragment).commit();
    }





    public static URL createUrl(String urlImage) {
        try {
            return new URL(urlImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
