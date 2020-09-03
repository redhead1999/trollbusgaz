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
import com.redhead.y14.routes.FragmentsGazel.BlankFragmentG1;
import com.redhead.y14.routes.FragmentsGazel.BlankFragmentG2;
import com.redhead.y14.routes.FragmentsGazel.BlankFragmentG3;
import com.redhead.y14.routes.FragmentsGazel.BlankFragmentG4;
import com.redhead.y14.routes.FragmentsGazel.BlankFragmentG5;

public class MainActivityGazel extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            if (position == 0) {
                return new BlankFragmentG1();
            }
            if (position == 1) {
                return new BlankFragmentG2();
            }
            if (position == 2) {
                return new BlankFragmentG3();
            }
            if (position == 3) {
                return new BlankFragmentG4();
            }
            if (position != 4) {
                return null;
            }
            return new BlankFragmentG5();
        }

        public int getCount() {
            return 5;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0546R.layout.activity_gazel);
        setSupportActionBar((Toolbar) findViewById(C0546R.C0548id.toolbar));
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(C0546R.C0548id.container);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(C0546R.C0548id.tabs);
        this.mViewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(this.mViewPager));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
