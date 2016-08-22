package com.example.listviewfenye;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ActionBarActivity {
	ListView listView;
	Button myButton;
	List<Integer> list = new ArrayList<>();
	int maxSize = 45;
	int countSize = 10;
	class ViewHolder{
		TextView tv1;
		TextView tv2;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		listView = (ListView) findViewById(R.id.myListView);
		for(int i = 0; i < countSize; ++i){
			list.add(i);
		}
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View footView = inflater.inflate(R.layout.foot_view, null);
		myButton = (Button) footView.findViewById(R.id.myButton);
		listView.addFooterView(footView);
		final MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);
		
		myButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myButton.setText("正在加载......");
				if(countSize+10 < maxSize){
					for(int i = countSize; i < countSize + 10; ++i){
						list.add(i);
					}
					countSize += 10;
				}else{
					for(int i = countSize; i < maxSize; ++i){
						list.add(i);
					}
					countSize = maxSize;
					listView.removeFooterView(footView);
				}
				
				//adapter.notifyDataSetChanged();
				adapter.notifyDataSetInvalidated();
				myButton.setText("加载更多");
			
			}
		});
		
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			if(convertView != null){
				view = convertView;
			}else{
				view = getLayoutInflater().inflate(R.layout.list_item, null);
				ViewHolder vh = new ViewHolder();
				TextView tv1 = (TextView) view.findViewById(R.id.myTextView1);
				TextView tv2 = (TextView) view.findViewById(R.id.myTextView2);
				vh.tv1 = tv1;
				vh.tv2 = tv2;
				view.setTag(vh);
			}
			ViewHolder vh = (ViewHolder)view.getTag();
			vh.tv2.setText(""+position);
			return view;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
