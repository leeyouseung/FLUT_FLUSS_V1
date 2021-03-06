package com.example.flut_fluss.base;

import android.util.Log;

import com.example.flut_fluss.utils.Utils;

import org.json.JSONObject;
import com.example.flut_fluss.network.Response;

import java.util.Objects;

import io.reactivex.functions.Function;

public abstract class BaseClient<V> {

    protected V api;

    protected abstract Class api();

    public BaseClient() {

        api = (V) Utils.RETROFIT.create(api());
    }

    protected <T> Function<retrofit2.Response<Response<T>>, T> getResponseObjectsFunction() {

        return response -> {

            if (!response.isSuccessful()) {

                JSONObject errorBody = new JSONObject(Objects.requireNonNull(response.errorBody()).string());

                Log.e("message", errorBody.getString("message"));

                throw new Exception(errorBody.getString("message"));
            }

            Log.e("message", response.body().getStatus() + "");

            return (T) response.body().getData();
        };
    }
}
