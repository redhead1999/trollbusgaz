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
import com.redhead.y14.routes.FragmentsAuto.BlankFragment10A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment11A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment12A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment13A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment14A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment15A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment16A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment1A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment2A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment3A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment4A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment5A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment6A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment7A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment8A;
import com.redhead.y14.routes.FragmentsAuto.BlankFragment9A;

public class MainActivityAuto extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BlankFragment1A();
                case 1:
                    return new BlankFragment2A();
                case 2:
                    return new BlankFragment3A();
                case 3:
                    return new BlankFragment4A();
                case 4:
                    return new BlankFragment5A();
                case 5:
                    return new BlankFragment6A();
                case 6:
                    return new BlankFragment7A();
                case 7:
                    return new BlankFragment8A();
                case 8:
                    return new BlankFragment9A();
                case 9:
                    return new BlankFragment10A();
                case 10:
                    return new BlankFragment11A();
                case 11:
                    return new BlankFragment12A();
                case 12:
                    return new BlankFragment13A();
                case 13:
                    return new BlankFragment14A();
                case 14:
                    return new BlankFragment15A();
                case 15:
                    return new BlankFragment16A();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 16;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0546R.layout.activity_auto);
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
