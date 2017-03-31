package com.fuxia.w.view9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.fuxia.w.R;

import rx.Observable;

/**
 * Created by fuyi on 2017/1/3.
 */
public class RetrofitViewsActivity extends AppCompatActivity{

    public String url = "http://wthrcdn.etouch.cn/";
    private TextView tv1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_layout);
        tv1 = (TextView) findViewById(R.id.tv1);
        loadDate();
    }

    private void loadDate() {
       /* OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WeatherServer weatherServer = retrofit.create(WeatherServer.class);
        Observable<Weatherbean> bj = weatherServer.getWeatherDate("北京");
        bj.subscribeOn(rx.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Weatherbean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Weatherbean weatherbean) {
                String ganmao = weatherbean.getData().getGanmao();
                tv1.setText(ganmao);
            }
        });*/
        NetRequest instance = NetRequest.getInstance();
        WeatherServer o = (WeatherServer) instance.setService(WeatherServer.class);
        Observable<Weatherbean> bj = o.getWeatherDate("北京");
        instance.toSubscribe(bj, new MySubscriber(){
            @Override
            public void onNext(Object o) {
                Weatherbean o1 = (Weatherbean) o;
                String ganmao = o1.getData().getGanmao();
                tv1.setText(ganmao);
            }
        });
        /*call.enqueue(new Callback<Weatherbean>() {
            @Override
            public void onResponse(Call<Weatherbean> call, Response<Weatherbean> response) {
                String ganmao = response.body().getData().getGanmao();

            }

            @Override
            public void onFailure(Call<Weatherbean> call, Throwable t) {

            }
        });*/
    }

    /*public void sendDate(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("你好,我是第一页");
            }
        });
    }*/

}
