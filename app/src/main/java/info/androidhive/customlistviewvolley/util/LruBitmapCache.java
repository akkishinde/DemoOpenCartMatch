package info.androidhive.customlistviewvolley.util;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class LruBitmapCache extends LruCache<String, Bitmap> implements
		ImageCache {
	public static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;

		return cacheSize;
	}

	    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
	            10);
		
	public LruBitmapCache() {
		this(getDefaultLruCacheSize());
	}

	public LruBitmapCache(int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@Override
	public Bitmap getBitmap(String url1) {
		return get(url1);
	}

	@Override
	public void putBitmap(String url1, Bitmap bitmap) {
		put(url1, bitmap);
	}
	
}