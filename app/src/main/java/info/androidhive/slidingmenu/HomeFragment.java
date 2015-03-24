package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

public class HomeFragment extends Fragment {
	private static final String TAG = HomeFragment.class.getSimpleName();
	public static int count;
	// Movies json url
	private static final String url = "http://192.168.0.107:80/mayuri/index.php";
	private static final int MODE_PRIVATE = 0;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;

	public static CustomListAdapter adapter1;
	private ProgressDialog pDialog;
	Button b1, b2, b3;
	public static String title = "", price = "";
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ImageButton next, prev, imageButton1, imageButton2;
	ViewPager viewPager;
	String a = "Image1", b = "Image2", c = "Image3";
	Timer timer;
	ViewPager mHandler;
	int page = 1;
	protected Handler handler;
	 public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	  
	 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_home,
				container, false);
		listView = (ListView) rootView.findViewById(R.id.list);

		 adapter1 = new CustomListAdapter(getActivity(), movieList);
		listView.setAdapter(adapter1);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0,
					android.view.View arg1, int arg2, long arg3) {
				Toast.makeText(getActivity(), "List item clicked",
						Toast.LENGTH_SHORT).show();
			}
		});

		viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);

		ImageAdapterForGallery adapter = new ImageAdapterForGallery(
				getActivity());
		viewPager.setAdapter(adapter);

		final Movie m = null;
		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						//Log.d(TAG, response.toString());

						count = response.length();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setTitle(obj.getString("name"));
								movie.setdescription(obj.getString("description"));
								movie.setYear(obj.getInt("price"));
								movie.setThumbnailUrl(obj.getString("image"));
								movie.setproduct_id(obj.getInt("product_id"));
							
							//	movie.setRating(((Number) obj.get("product_id"))
								// .doubleValue());
								movie.setYear(obj.getInt("price"));
								movie.put("name", obj.getString("price"));
								movie.put("description", obj.getString("description"));
								movie.put("price", obj.getString("price"));
								movie.put("image", obj.getString("image"));
								
								//movie.put("image2", obj.getString("image2"));
								ArrayList<String> genre = new ArrayList<String>();

								// adding movie to movies array
								movieList.add(movie);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter1.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Object s = parent.getItemAtPosition(position + 1);

				Toast.makeText(getActivity(), "You have Clicked item no" + s,
						Toast.LENGTH_LONG).show();

				Intent icomplain = new Intent(getActivity(), MyCart.class);
				startActivity(icomplain);

			}
		});
		viewPager.setCurrentItem(0);
		adapter.setTimer(viewPager, 3);

		return rootView;
	}

	protected Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {

		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}

	}

	public static List<Movie> getCatalog(Object resources) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Movie> getCart() {
		// TODO Auto-generated method stub
		return null;
	}
}
