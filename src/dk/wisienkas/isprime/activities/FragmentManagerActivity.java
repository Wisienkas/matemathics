package dk.wisienkas.isprime.activities;

import dk.wisienkas.isprime.activities.fragments.FibonacciFragment;
import dk.wisienkas.isprime.activities.fragments.PrimeFragment;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class FragmentManagerActivity extends FragmentActivity {
	
	ViewPager viewPager;
	Application application;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.application = getApplication();
		setContentView(dk.wisienkas.isprime.R.layout.fragment_manager_activity);
		this.viewPager = (ViewPager) findViewById(dk.wisienkas.isprime.R.id.fragmentPager);
		FragmentManager fragmentManager = getSupportFragmentManager();
		this.viewPager.setAdapter(new MyFragmentAdapter(fragmentManager));
	}
	
	private class MyFragmentAdapter extends FragmentStatePagerAdapter{

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			if(arg0 == 0){
				PrimeFragment fragment = new PrimeFragment();
				return fragment;
			}else if(arg0 == 1){
				FibonacciFragment fragment = new FibonacciFragment();
				return fragment;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			if (position == 0) {
				return (CharSequence) "IsPrime";
			}else if(position == 1){
				return (CharSequence) "Fibonacci Numbers";
			}
			return (CharSequence) "none";
		}
		
	}
	
}
