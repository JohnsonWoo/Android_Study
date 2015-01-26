package com.lve.criminalintent;

import java.util.ArrayList;

import com.lve.criminalintent.R.id;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	public static final int REQUEST_CRIME = 1;
	
	private ArrayList<Crime> mCrimes;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();
		
		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Crime crime = (Crime)(getListAdapter()).getItem(position);
		Log.d("Crime", crime.getTitle() + " was checked");
		Intent intent = new Intent(getActivity(), CrimePageActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivityForResult(intent, REQUEST_CRIME);
	}

	@Override
	public void onResume(){
		super.onResume();
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == REQUEST_CRIME){
			
		}
	}
	
	private class CrimeAdapter extends ArrayAdapter<Crime>{
		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
			}
			Crime crime = getItem(position);
			TextView titTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
			titTextView.setText(crime.getTitle());
			TextView datetTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
			datetTextView.setText(crime.getDate().toString());
			CheckBox solvedcCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
			solvedcCheckBox.setChecked(crime.isSolved());
			return convertView;
		}
	}
}
