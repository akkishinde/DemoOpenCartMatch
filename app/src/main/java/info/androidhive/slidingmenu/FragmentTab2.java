package info.androidhive.slidingmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

public class FragmentTab2 extends Activity implements OnClickListener{
  
    Button b1,b2;
    EditText ev1,ev2,ev3;
    protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmenttab2);
    
        b1 = (Button)findViewById(R.id.button2);
        ev1=(EditText)findViewById(R.id.ev1);
        ev2=(EditText)findViewById(R.id.ev2);
        ev3=(EditText)findViewById(R.id.editText2);

	b1.setOnClickListener(this);
		b2 = (Button)findViewById(R.id.button3);

		b2.setOnClickListener(this);
      
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button2:
		        ev3.setText("");
			break;
		case R.id.button3:
		
			break;
		}

	}
 
}