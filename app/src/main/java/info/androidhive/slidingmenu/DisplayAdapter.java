package info.androidhive.slidingmenu;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.InputStream;
import java.util.ArrayList;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * adapter to populate listview with data
 * @author ketan(Visit my <a
 *         href="http://androidsolution4u.blogspot.in/">blog</a>)
 */
public class DisplayAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> id;
	private ArrayList<String> firstName;
	private ArrayList<String> lastName;
	private ArrayList<String> image;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	 ImageButton imageButton1;
	public DisplayAdapter(Context c, ArrayList<String> id,ArrayList<String> fname, ArrayList<String> lname) {
		this.mContext = c;

		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		//this.image=image;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return id.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.list_cart, null);
			mHolder = new Holder();
			mHolder.txt_id = (TextView) child.findViewById(R.id.title);
			mHolder.txt_fName = (TextView) child.findViewById(R.id.rating);
			mHolder.txt_lName = (TextView) child.findViewById(R.id.genre);
			//mHolder.image = (ImageView) child.findViewById(R.id.thumbnail);
			
		
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
	
	//	mHolder.txt_id.setText(id.get(pos));
		mHolder.txt_fName.setText(firstName.get(pos));
		mHolder.txt_lName.setText(lastName.get(pos));
		//mHolder.image.setTag(image.get(pos));
		
	
         
         
		return child;
	}

	

	public class Holder {
		public TextView txt_image;
		TextView txt_id;
		TextView txt_fName;
		TextView txt_lName;
		ImageView image;
	}

}
