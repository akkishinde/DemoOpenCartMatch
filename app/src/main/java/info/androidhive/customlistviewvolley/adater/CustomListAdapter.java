package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import info.androidhive.slidingmenu.Full_Info;
import info.androidhive.slidingmenu.HomeFragment;
import info.androidhive.slidingmenu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private HomeFragment activity;
	private LayoutInflater inflater;

	public static HashMap<String, String> resultp = new HashMap<String, String>();
	public static ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	public static HashMap<String, String> map = new HashMap<String, String>();
	final List<Movie> cart = HomeFragment.getCart();
	private List<Movie> movieItems;
	ImageButton imageButton1, imageButton2, imageButton3;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private Context context;
	List<Movie> data;
	String year,product_id;
	private ListView listView;
	public static String name;
	public static String bitmap;
	public static String price;

	public CustomListAdapter(Context context, List<Movie> movieItems) {
		this.context = context;
		this.movieItems = movieItems;
		data = movieItems;

	}

	public CustomListAdapter(Full_Info full_Info,
			List<Map<String, Object>> data2, int listcart, String[] strings,
			int[] is) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);
		// imageButton1.setTag(position);
		// resultp = data.get(position);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);

		TextView price = (TextView) convertView.findViewById(R.id.rating);
		final TextView year = (TextView) convertView.findViewById(R.id.genre);
		final ImageButton imageButton1 = (ImageButton) convertView
				.findViewById(R.id.imageButton1);
		title.setText(resultp.get(HomeFragment.title));

		price.setText(resultp.get(HomeFragment.price));
		Movie m = movieItems.get(position);
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		title.setText(m.getTitle());
		price.setText("price" + String.valueOf(m.getYear()));

		/*
		 * imageButton1.setOnClickListener(new OnClickListener() { public void
		 * onClick(View v) {
		 * 
		 * Toast.makeText(context, "" + mylist, Toast.LENGTH_SHORT).show();
		 * 
		 * Movie p = movieItems.get(position); map.put("name", p.getTitle());
		 * map.put("image", p.getThumbnailUrl()); map.put("price",
		 * String.valueOf(p.getRating())); mylist.add(map); Intent i = new
		 * Intent(context, MyCart.class); // Pass all data rank //
		 * intent.putExtra("name",position);
		 * 
		 * i.putExtra("image", p.getThumbnailUrl()); i.putExtra("name",
		 * p.getTitle());
		 * 
		 * i.putExtra("price", String.valueOf(p.getRating())); // Pass all data
		 * country i.putExtra("mylist", map);
		 * i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * 
		 * context.startActivity(i);
		 * 
		 * }
		 * 
		 * private void finish() { // TODO Auto-generated method stub
		 * 
		 * } });
		 */
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
				Movie p = movieItems.get(position);
				
				Intent i = new Intent(context, Full_Info.class);
				// Pass all data rank
				// intent.putExtra("name",position);

				i.putExtra("image", p.getThumbnailUrl());

				i.putExtra("description", p.getdescription());
				i.putExtra("name", p.getTitle());
				i.putExtra("price", p.getYear());
				i.putExtra("product_id", p.getproduct_id());
				//Toast.makeText(context, "" + p.getproduct_id(), Toast.LENGTH_SHORT).show();

				// Pass all data country

				context.startActivity(i);

			}

		});
		// getting movie data for the row

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// title
		title.setText(m.getTitle());

		// rating
		price.setText("Price: " + String.valueOf(m.getYear()));

		// genre

		// release year

		return convertView;
	}

	protected void startActivityForResult(Intent myIntent, int i) {
		// TODO Auto-generated method stub

	}

	public static HashMap<String, String> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

}