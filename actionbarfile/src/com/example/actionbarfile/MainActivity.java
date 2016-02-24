package com.example.actionbarfile;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private Fragment1 f1;
	private Fragment2 f2;
	private Fragment3 f3;
	private ActionBar ab;
	private Button xianshi;
	private Button yincang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initone();
		init();
	}

	private void initone() {
		// TODO Auto-generated method stub
		manager=getSupportFragmentManager();
		xianshi=(Button) findViewById(R.id.xianshi);
		yincang=(Button) findViewById(R.id.yincang);
		
		//加监听
		xianshi.setOnClickListener(l);
		yincang.setOnClickListener(l);
		
		f1=new Fragment1();
		f2=new Fragment2();
		f3=new Fragment3();
		transaction=manager.beginTransaction();
		transaction.add(R.id.fragment_page, f1);
		transaction.add(R.id.fragment_page, f2);
		transaction.add(R.id.fragment_page, f3);
		transaction.hide(f2);
		transaction.hide(f3);
		transaction.show(f1);
		transaction.commit();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		//得到actionbar;
		ab=getActionBar();
		//设置左上角出现一个向左的箭头,并可以点击，相当于点击home菜单，通过菜单监听器监听
		ab.setDisplayHomeAsUpEnabled(true);
//		ab.setDisplayShowTitleEnabled(false);//设置是否显示应用程序标题
//	    ab.setDisplayShowHomeEnabled(false);//设置是否显示应用程序图标
		
//		//tab样式
//		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//设置为tab样式
//		ab.addTab(ab.newTab().setText("frag1").setIcon(R.drawable.a).setTabListener(tab1));
//		ab.addTab(ab.newTab().setText("frag2").setIcon(R.drawable.b).setTabListener(tab1));
//		ab.addTab(ab.newTab().setText("frag3").setIcon(R.drawable.c).setTabListener(tab1));

	
	  //列表样式
	    ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	    final String[] str={"frag1","frag2","frag3"};
	    //创建actionbar的适配器
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str);
	    //把适配器给actionbar同时监听
	    ab.setListNavigationCallbacks(adapter,new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				// TODO Auto-generated method stub
				if(str[itemPosition].equals("frag1")){
					transaction=manager.beginTransaction();
					transaction.hide(f2);
					transaction.hide(f3);
					transaction.show(f1);
					transaction.commit();
				}else if(str[itemPosition].equals("frag2")){
					transaction=manager.beginTransaction();
					transaction.hide(f1);
					transaction.hide(f3);
					transaction.show(f2);
					transaction.commit();
				}else if(str[itemPosition].equals("frag3")){
					transaction=manager.beginTransaction();
					transaction.hide(f2);
					transaction.hide(f1);
					transaction.show(f3);
					transaction.commit();
				}
				
				
				
				return false;
			}
		});
	   
	    
	}
	
//	TabListener tab1=new TabListener() {
//		
//		@Override
//		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
//			// TODO Auto-generated method stub
//			//开启事务
//			transaction=manager.beginTransaction();
//			if(tab.getText().equals("frag1")){
//				transaction.hide(f2);
//				transaction.hide(f3);
//				transaction.show(f1);
//				
//			}else if(tab.getText().equals("frag2")){
//				transaction.hide(f1);
//				transaction.hide(f3);
//				transaction.show(f2);
//			}else if(tab.getText().equals("frag3")){
//				transaction.hide(f1);
//				transaction.hide(f2);
//				transaction.show(f3);
//			}
//			
//			transaction.commit();
//			
//		}
//		
//		@Override
//		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
		
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.xianshi:
				ab.show();//显示actionbar页面布局
				break;
			case R.id.yincang:
				ab.hide();//隐藏actionbar页面布局
				break;

			default:
				break;
			}
			
		}
	};
	
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if(item.getItemId() == R.id.m1){
			Toast.makeText(this, "你参加的是八路军！", 0).show();
		}else if(item.getItemId() == R.id.m2){
			Toast.makeText(this, "你参加的是新四军！", 0).show();
		}else if(item.getItemId() == R.id.m3){
			Toast.makeText(this, "你参加的是国民党！", 0).show();
		}
		
		return onOptionsItemSelected( item);
	};
	
	//给添加的左箭头加监听
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			Toast.makeText(this, "你点击的是箭头", 2000).show();
			this.finish();
		}
		
		
		return onMenuItemSelected(featureId, item);
		
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
