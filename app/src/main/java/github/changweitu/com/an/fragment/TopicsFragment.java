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
import github.changweitu.com.an.model.Topic;
import github.changweitu.com.an.view.SlidingTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicsFragment extends Fragment {
    @BindView(R.id.view_pager)
    public ViewPager mViewPager;
    @BindView(R.id.sliding_tab)
    public SlidingTabLayout mSlidingTabLayout;

    private static String[] mPageNames = {Topic.POST_TOP, Topic.POST_NO_REPLY, Topic.POST_LAST_REPLY, Topic.POST_NEW};
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
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new TopicListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", Topic.types.get(mPageNames[position]));
            fragment.setArguments(bundle);
            return fragment;
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
