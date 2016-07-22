package github.changweitu.com.an.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.changweitu.com.an.R;
import github.changweitu.com.an.view.SlidingTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicsFragment extends Fragment {
    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    @BindView(R.id.sliding_tab)
    public SlidingTabLayout mSlidingTabLayout;
    public TopicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
        super.onViewCreated(view, savedInstanceState);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
