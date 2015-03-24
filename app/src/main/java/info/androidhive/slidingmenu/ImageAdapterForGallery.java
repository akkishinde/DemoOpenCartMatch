package info.androidhive.slidingmenu;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapterForGallery extends PagerAdapter {
	Context context;
	private int[] GalImages = new int[] { R.drawable.slider_img3, R.drawable.slider_img2, R.drawable.slider_img1, R.drawable.slider_img2,R.drawable.slider_img3, R.drawable.slider_img2, R.drawable.slider_img1, R.drawable.slider_img2};

	ImageAdapterForGallery(Context context) {
		this.context = context;
	}

	final Handler handler = new Handler();
	public Timer swipeTimer ;


	@Override
	public int getCount() {
		return GalImages.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		ImageView imageView = new ImageView(context);
		int padding = context.getResources().getDimensionPixelSize(
				R.dimen.padding_small);
		
		imageView.setPadding(padding, padding, padding, padding);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setImageResource(GalImages[position]);
		
		((ViewPager) container).addView(imageView, 0);
		
		
		
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ImageView) object);
	}

	public void setTimer(final ViewPager viewPager, int i) {
		// TODO Auto-generated method stub
		final Runnable Update = new Runnable() {
            int NUM_PAGES =6;
            int currentPage =0  ;
            public void run() {
            
                if (currentPage == NUM_PAGES ) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
       
		swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, i*1000);
	}

	
}