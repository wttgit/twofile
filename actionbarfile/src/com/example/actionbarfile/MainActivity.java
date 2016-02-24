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
		
		//�Ӽ���
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
		
		//�õ�actionbar;
		ab=getActionBar();
		//�������Ͻǳ���һ������ļ�ͷ,�����Ե�����൱�ڵ��home�˵���ͨ���˵�����������
		ab.setDisplayHomeAsUpEnabled(true);
//		ab.setDisplayShowTitleEnabled(false);//�����Ƿ���ʾӦ�ó������
//	    ab.setDisplayShowHomeEnabled(false);//�����Ƿ���ʾӦ�ó���ͼ��
		
//		//tab��ʽ
//		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//����Ϊtab��ʽ
//		ab.addTab(ab.newTab().setText("frag1").setIcon(R.drawable.a).setTabListener(tab1));
//		ab.addTab(ab.newTab().setText("frag2").setIcon(R.drawable.b).setTabListener(tab1));
//		ab.addTab(ab.newTab().setText("frag3").setIcon(R.drawable.c).setTabListener(tab1));

	
	  //�б���ʽ
	    ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	    final String[] str={"frag1","frag2","frag3"};
	    //����actionbar��������
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str);
	    //����������actionbarͬʱ����
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
//			//��������
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
				ab.show();//��ʾactionbarҳ�沼��
				break;
			case R.id.yincang:
				ab.hide();//����actionbarҳ�沼��
				break;

			default:
				break;
			}
			
		}
	};
	
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if(item.getItemId() == R.id.m1){
			Toast.makeText(this, "��μӵ��ǰ�·����", 0).show();
		}else if(item.getItemId() == R.id.m2){
			Toast.makeText(this, "��μӵ������ľ���", 0).show();
		}else if(item.getItemId() == R.id.m3){
			Toast.makeText(this, "��μӵ��ǹ��񵳣�", 0).show();
		}
		
		return onOptionsItemSelected( item);
	};
	
	//����ӵ����ͷ�Ӽ���
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			Toast.makeText(this, "�������Ǽ�ͷ", 2000).show();
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
