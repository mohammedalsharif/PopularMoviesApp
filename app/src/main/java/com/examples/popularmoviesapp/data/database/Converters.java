package com.examples.popularmoviesapp.data.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converters {

    @TypeConverter
    public byte[] fromBitmap(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }

    @TypeConverter
    public Bitmap toBitmap(byte[] byteArray){

        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
    }
}
