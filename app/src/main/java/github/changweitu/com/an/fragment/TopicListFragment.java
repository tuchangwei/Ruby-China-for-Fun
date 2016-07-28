package github.changweitu.com.an.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.changweitu.com.an.Constant;
import github.changweitu.com.an.R;
import github.changweitu.com.an.model.Topic;
import github.changweitu.com.an.util.DateUtil;
import github.changweitu.com.an.view.CircleTransform;
import github.changweitu.com.an.view.RoundedTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicListFragment extends Fragment {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rotate_loading)
    RotateLoading loadingView;

    private boolean firstLoading;
    private String type;
    private ArrayList<Topic> mTopics;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int page;
    private int countPerPage = 20;
    private boolean isLoadingMore;

    public TopicListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        type = getArguments().getString("type");
        mTopics = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light
        );
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                requestTopics();
            }
        });
        mAdapter = new TopticListAdapter();
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibileItem  = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemsCount = recyclerView.getAdapter().getItemCount();
                if (lastVisibileItem >= totalItemsCount - 2 && !isLoadingMore && dy > 0) {
                    isLoadingMore = true;
                    loadMore();
                }
            }
        });
        if (!firstLoading) {
            loadingView.start();
            requestTopics();
            firstLoading = true;
        }
    }

    public void requestTopics() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.BaseURL + Constant.TopicEndPoint + "?type=" + type + "&offset=" + page*countPerPage).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                stopLoadingOnMainThread(false);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (page==0) {
                        mTopics.clear();
                    }
                    JSONObject topicsJson = new JSONObject(response.body().string());
                    JSONArray topics = topicsJson.getJSONArray("topics");
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
                    Type topicListType = new TypeToken<Collection<Topic>>(){}.getType();
                    List<Topic> topicList = gson.fromJson(String.valueOf(topics), topicListType);
                    mTopics.addAll(topicList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stopLoadingOnMainThread(true);
            }
        });
    }

    public void loadMore() {
        page++;
        requestTopics();
    }
    private void stopLoadingOnMainThread(final boolean successed) {
        loadingView.post(new Runnable() {
            @Override
            public void run() {
                loadingView.stop();
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
                isLoadingMore = false;
                if (!successed) {
                    Toast.makeText(getContext(), "请求失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class TopticListAdapter extends RecyclerView.Adapter<TopticListAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toptic_list, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Topic topic = mTopics.get(position);
            holder.tv_title.setText(topic.getTitle());
            holder.tv_subTitle.setText(topic.getUser().getLogin() + "       " + DateUtil.before(topic.getCreated_at()));
            int density = (int) getResources().getDisplayMetrics().density;
            Picasso.with(getContext())
                    .load(topic.getUser().getAvatar_url())
                    .error(R.drawable.avatar_default)
                    .placeholder(R.drawable.avatar_default)
                    .centerCrop()
                    .resize(50*density, 50*density)
                    .transform(new RoundedTransformation(8,0))
                    .into(holder.iv_avatar);
        }

        @Override
        public int getItemCount() {
            return mTopics.size();
        }

        public class  ViewHolder extends RecyclerView.ViewHolder {
            public ImageView iv_avatar;
            public TextView tv_title;
            public TextView tv_subTitle;
            public ViewHolder(View itemView) {
                super(itemView);
                iv_avatar = (ImageView)itemView.findViewById(R.id.iv_avatar);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_subTitle = (TextView)itemView.findViewById(R.id.tv_subTitle);
            }
        }
    }
}
