package com.redhead.y14.routes;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener;
import com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment10T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment1T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment2T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment3T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment4T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment5T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment6T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment7T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment8T;
import com.redhead.y14.routes.FragmentsTroll.BlankFragment9T;

public class MainActivityTroll extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BlankFragment1T();
                case 1:
                    return new BlankFragment2T();
                case 2:
                    return new BlankFragment3T();
                case 3:
                    return new BlankFragment4T();
                case 4:
                    return new BlankFragment5T();
                case 5:
                    return new BlankFragment6T();
                case 6:
                    return new BlankFragment7T();
                case 7:
                    return new BlankFragment8T();
                case 8:
                    return new BlankFragment9T();
                case 9:
                    return new BlankFragment10T();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 10;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0546R.layout.activity_troll);
        setSupportActionBar((Toolbar) findViewById(C0546R.C0548id.toolbar));
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(C0546R.C0548id.container);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        TabLayout tabLayout2 = (TabLayout) findViewById(C0546R.C0548id.tabs);
        this.mViewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout2));
        tabLayout2.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(this.mViewPager));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
