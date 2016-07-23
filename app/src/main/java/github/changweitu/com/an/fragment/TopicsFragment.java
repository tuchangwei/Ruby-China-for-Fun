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

import java.util.ArrayList;

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

    private static String[] mPageNames = {"精华帖", "无人问津", "最后回复", "最新创建"};
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
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setDividerColors(0);
        mSlidingTabLayout.setViewPager(mViewPager);
        super.onViewCreated(view, savedInstanceState);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new TopicListFragment();
        }

        @Override
        public int getCount() {
            return mPageNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPageNames[position];
        }
    }
}
