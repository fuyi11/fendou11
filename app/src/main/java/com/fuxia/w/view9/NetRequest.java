package com.fuxia.w.view9;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Bob on 2017/1/3.
 */

public class NetRequest {

    private static final int DEFAULT_TIMEOUT = 5;
    private final OkHttpClient.Builder builder;
    public static final String BASE_URL ="http://wthrcdn.etouch.cn/";
    private final Retrofit retrofit;

    //在访问NetRequest时创建单例
    private static class Singleton {
        private static final NetRequest INSTANCE = new NetRequest();
    }

    //获取单例
    public static NetRequest getInstance() {
        return Singleton.INSTANCE;
    }

    private NetRequest() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

  public Object setService(Class service){
      Object o = retrofit.create(service);
      return o;
  }
    public  <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
