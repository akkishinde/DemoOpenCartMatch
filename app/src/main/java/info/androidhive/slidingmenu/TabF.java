package info.androidhive.slidingmenu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class TabF extends Activity {
	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2, Tab3;
	Fragment fragmentTab1 = new FragmentTab1();

	
TabHost tabhost;
ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setTitle("                Matchwell       " );
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(true);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(true);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Login");
		Tab2 = actionBar.newTab().setText("Delivery");
		Tab3 = actionBar.newTab().setText("Payment");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
	
	

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73880a")));
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		
		case R.id.action_back:
			onClick(imageView);
			return true;
		default:

			return super.onOptionsItemSelected(item);
		}
	}
	private void onClick(View imageView) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(), MyCart.class);
		finish();
		startActivity(i);
	}
}
