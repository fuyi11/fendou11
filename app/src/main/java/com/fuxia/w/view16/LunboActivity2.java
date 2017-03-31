package com.fuxia.w.view16;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fuxia.w.R;
import com.loopj.android.image.SmartImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyi on 2017/3/16.
 */
public class LunboActivity2 extends AppCompatActivity {
    private ImageCycleView mImageCycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbos);


        mImageCycleView = (ImageCycleView) findViewById(R.id.icv_topView);
//		mImageCycleView.setAutoCycle(false); //关闭自动播放
//		mImageCycleView.setCycleDelayed(2000);//设置自动轮播循环时间
//
//		mImageCycleView.setIndicationStyle(ImageCycleView.IndicationStyle.COLOR,
//				Color.BLUE, Color.RED, 1f);

//		mImageCycleView.setIndicationStyle(ImageCycleView.IndicationStyle.IMAGE,
//				R.drawable.dian_unfocus, R.drawable.dian_focus, 1f);

//		Log.e("eee", Environment.getExternalStorageDirectory().getPath()+ File.separator+"a1.jpg");

        final List<ImageCycleView.ImageInfo> list=new ArrayList<ImageCycleView.ImageInfo>();

        //res图片资源
        list.add(new ImageCycleView.ImageInfo(R.drawable.img_avatar_01,"111111111111",""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.img_avatar_02,"222222222222222",""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.img_avatar_03,"3333333333333",""));

        //SD卡图片资源
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a1.jpg"),"11111",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a2.jpg"),"22222",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a3.jpg"),"33333",""));


        //使用网络加载图片
//		list.add(new ImageCycleView.ImageInfo("http://www.huanjingtrip.com/uploads/Default/201611/e4387c4280ffa67bf9626f43bbc55ee5.jpg","11","eeee"));
//		list.add(new ImageCycleView.ImageInfo("http://www.huanjingtrip.com/uploads/Default/201611/b9ad7dd786b64599be37f6d4e2d27a21.jpg","222","rrrr"));
//		list.add(new ImageCycleView.ImageInfo("http://www.huanjingtrip.com/uploads/Default/201611/19c51040aff697c2fac064eff80c3cb7.jpg", "333", "tttt"));

		mImageCycleView.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
			@Override
			public void onClick(View imageView, ImageCycleView.ImageInfo imageInfo) {
                for (int i = 0 ; i<= list.size()-1;i++) {
                    Toast.makeText(LunboActivity2.this, "你点击了" + list.get(i), Toast.LENGTH_SHORT).show();
                }
            }
		});

        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

                //本地图片
                ImageView imageView=new ImageView(LunboActivity2.this);
                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));

                return imageView;


//				//使用SD卡图片
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageURI(Uri.fromFile((File)imageInfo.image));
//				return smartImageView;

//				//使用SmartImageView，既可以使用网络图片也可以使用本地资源
//				SmartImageView smartImageView = new SmartImageView(LunboActivity2.this);
//				smartImageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//				return smartImageView;

                //使用BitmapUtils,只能使用网络图片
//				BitmapUtils bitmapUtils = new BitmapUtils(LunboActivity2.this);
//				ImageView imageView = new ImageView(LunboActivity2.this);
//				bitmapUtils.display(imageView, imageInfo.image.toString());
//				return imageView;


            }
        });


    }


}
