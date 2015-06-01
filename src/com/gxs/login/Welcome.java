package com.gxs.login;

import com.example.login.R;
import com.gxs.listview.*;
import android.os.Bundle;
import android.preference.Preference;
import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends Activity implements OnClickListener{
	private EditText uname = null;
	private EditText upswd = null;
	private CheckBox checkbox = null;
	private Button login = null;
	SharedPreferences sp = null; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        init();
    
    }

   
    public void init()
	{
		uname = (EditText) findViewById(R.id.user_input);
		upswd = (EditText) findViewById(R.id.pass_input);
		checkbox = (CheckBox) findViewById(R.id.checkBoxLogin);
	    login = (Button) findViewById(R.id.new_user);
	    if (sp.getBoolean("auto", false))
	  		{
	  	    	uname.setText(sp.getString("uname", null));
	  	    	upswd.setText(sp.getString("upswd", null)); 
	  	    	checkbox.setChecked(true);
	  	     
	  		}
	    login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == login){
			String name = uname.getText().toString();
		 	String pswd = upswd.getText().toString();
		 	if(name.trim().equals("")){
    			Toast.makeText(this, "请您输入用户名！", Toast.LENGTH_SHORT).show();
				return;
    		}
    		if(pswd.trim().equals("")){
    			Toast.makeText(this, "请您输入密码！", Toast.LENGTH_SHORT).show();
				return;
    		}
			boolean autoLogin = checkbox.isChecked();
			if (autoLogin)
			{
					Editor editor = sp.edit();
					editor.putString("uname", name);
					editor.putString("upswd", pswd);
					editor.putBoolean("auto", true);
					editor.commit();
			}
			else
			{  
				Editor editor = sp.edit();
				editor.putString("uname", null);
				editor.putString("upswd", null);
				editor.putBoolean("auto", false);
				editor.commit();
				}
			//Intent跳转
			Intent intent = new Intent(Welcome.this,Content.class);
			startActivity(intent);
			finish();
		}

		
	}
   
}
