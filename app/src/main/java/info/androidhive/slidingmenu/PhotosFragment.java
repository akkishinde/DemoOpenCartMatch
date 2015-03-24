package info.androidhive.slidingmenu;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class PhotosFragment extends Fragment {

	public PhotosFragment() {
	}

	private static final String TAG = HomeFragment.class.getSimpleName();
	public static int count;
	// Movies json url
	private static final String url = "http://10.0.2.2:80/mayuri/index.php";
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter1;
	private ProgressDialog pDialog;
	Button b1, b2, b3;
	ImageButton next, prev, imageButton1, imageButton2;
	ViewPager viewPager;
	String a = "Image1", b = "Image2", c = "Image3";
	Timer timer;
	ViewPager mHandler;
	int page = 1;
	protected Handler handler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_photos, container,
				false);
		listView = (ListView) rootView.findViewById(R.id.list);

		adapter1 = new CustomListAdapter(getActivity(), movieList);
		listView.setAdapter(adapter1);
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
						Log.d(TAG, response.toString());

						count = response.length();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								
								if (20==obj.getInt("category_id")) {
									Movie movie = new Movie();
								movie.setTitle(obj.getString("name"));
								movie.setThumbnailUrl(obj.getString("image"));
								movie.setdescription(obj.getString("description"));
								movie.setYear(obj.getInt("price"));
								movie.setproduct_id(obj.getInt("category_id"));
								movie.setproduct_id(obj.getInt("product_id"));
								movieList.add(movie);
								}
								
								//ArrayList<String> genre = new ArrayList<String>();

								// adding movie to movies array
								

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

		/*
		 * next.setOnClickListener(this); prev.setOnClickListener(this);
		 */
		viewPager.setCurrentItem(0);
		adapter.setTimer(viewPager, 3);

		return rootView;
	}

}
