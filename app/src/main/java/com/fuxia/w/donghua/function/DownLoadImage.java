package com.fuxia.w.donghua.function;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

/**
 * @description 根据下载地址下载网络图片并保存在SD卡指定路径中
 * @author Administrator Zsaog
 * 
 */
public class DownLoadImage {
	private final String TAG = "DownLoadImage";
	private String url;// 图片下载网络地址
	private String path;// 图片存储的SD卡的路径
	private CompressFormat format;// 图片格式
	private int quality;// 图片质量，100表不压缩，0表示压缩100%
	private String fileName;

	private Bitmap mBitmap;

	public DownLoadImage(String url) {
		this.url = url;
		path = Environment.getExternalStorageDirectory() + "/DownLoadImage/";
		format = CompressFormat.JPEG;// 图片保存为JPG格式
		quality = 80;// 压缩20%，即原图的80%
		fileName = "downimage.jpg";
	}

	public DownLoadImage(String url, String fileName) {
		this.url = url;
		path = Environment.getExternalStorageDirectory() + "/DownLoadImage/";
		format = CompressFormat.JPEG;// 图片保存为JPG格式
		quality = 80;// 压缩20%，即原图的80%
		this.fileName = fileName + ".jpg";
	}

	public DownLoadImage(String url, String path, CompressFormat format,
			int quality, String fileName) {
		this.url = url;
		this.path = path;
		this.format = format;
		this.quality = quality;
		this.fileName = fileName;
	}

	/**
	 * @description 保存文件
	 * 
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public void saveFile(Bitmap bm, String fileName) throws IOException {
		File dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File myCaptureFile = new File(path + fileName);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(format, quality, bos);
		bos.flush();
		bos.close();
	}

	private Runnable saveFileRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				saveFile(mBitmap, fileName);
				Log.i(TAG, "图片保存成功！");
			} catch (IOException e) {
				Log.e(TAG, "图片保存失败！");
				e.printStackTrace();
			}
		}
	};

	/**
	 * @description 异步执行保存图片操作
	 */
	public void saveImage() {
		new Thread(connectNet).start();
		new Thread(saveFileRunnable).start();
	}

	/**********************************************************************************************/
	/**
	 * @description 连接网络 由于在4.0中不允许在主线程中访问网络，所以需要在子线程中访问
	 */
	public Runnable connectNet = new Runnable() {
		@Override
		public void run() {
			try {
				// 以下是取得图片的两种方法
				// ////////////// 方法1：取得的是byte数组, 从byte数组生成bitmap
				byte[] data = getImage(url);
				if (data != null) {
					mBitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);// bitmap
				} else {
					Log.e(TAG, "Image error!");
				}
				// //////////////////////////////////////////////////////

				// ******** 方法2：取得的是InputStream，直接从InputStream生成bitmap
				// ***********/
				// mBitmap = BitmapFactory.decodeStream(getImageStream(url));
				// ********************************************************************/

				// 发送消息，通知handler在主线程中更新UI
				// Log.i(TAG, "set image ...");
			} catch (Exception e) {
				Log.e(TAG, "无法链接网络！");
				e.printStackTrace();
			}
		}
	};

	/**
	 * Get image from newwork
	 * 
	 * @param path
	 *            The path of image
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return readStream(inStream);
		}
		return null;
	}

	/**
	 * Get image from newwork
	 * 
	 * @param path
	 *            The path of image
	 * @return InputStream
	 * @throws Exception
	 */
	public InputStream getImageStream(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * Get data from stream
	 * 
	 * @param inStream
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}
}
