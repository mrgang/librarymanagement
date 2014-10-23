package com.example.librarymanagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.example.librarymanagement.fragments.BookSelect;
import com.example.librarymanagement.fragments.LendHistory;
import com.example.librarymanagement.fragments.LoginState;
import com.example.librarymanagement.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by ligan_000 on 2014/10/22.
 */
public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    private TabPageIndicator indicator;
    private String[] titles = new String[]{"查找书籍","我的借阅","登录状态"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_main);

        viewPager = (ViewPager)this.findViewById(R.id.viewpager);
        list = new ArrayList<Fragment>();
        list.add(new BookSelect());
        list.add(new LendHistory());
        list.add(new LoginState());

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),list));
        viewPager.setCurrentItem(0);
        indicator = (TabPageIndicator)this.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);


    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter
    {

        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
