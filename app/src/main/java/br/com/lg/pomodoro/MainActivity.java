package br.com.lg.pomodoro;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.lg.pomodoro.fragment.PomodoroCounterFragment;
import br.com.lg.pomodoro.fragment.PomodoroHistoryFragment;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Paper.init(this);

        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        PomodoroCounterFragment pomodoroCounterFragment;
        PomodoroHistoryFragment pomodoroHistoryFragment;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            pomodoroCounterFragment = new PomodoroCounterFragment();
            pomodoroHistoryFragment = new PomodoroHistoryFragment();
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment = pomodoroCounterFragment;
            if(position == 1) fragment = pomodoroHistoryFragment;
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0?"NEW":"HISTORY";
        }
    }
}