package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.Router;
import oleg.osipenko.mai.data.dataModel.NewsHeadersContent;
import oleg.osipenko.mai.presentation.events.ChangeScreenEvent;
import oleg.osipenko.mai.presentation.screens.NewsHeadersScreen;

/**
 * Created by olegosipenko on 20.04.16.
 */
public class NewsHeadersView extends FrameLayout {

    @Inject
    NewsHeadersScreen.Presenter presenter;

    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.pb)
    ProgressBar progressBar;

    private Adapter adapter;
    private boolean canLoadMore = true;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    public NewsHeadersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(new ArrayList<NewsHeadersContent>());
        list.setAdapter(adapter);
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                if (canLoadMore) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        canLoadMore = false;
                        presenter.loadMore();
                    }
                }
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        presenter.visibilityChanged(visibility == VISIBLE);
    }

    public void showHeaders(List<NewsHeadersContent> newsHeadersContents) {
        progressBar.setVisibility(GONE);
        list.setVisibility(VISIBLE);
        adapter.setHeaders(newsHeadersContents);
        adapter.notifyDataSetChanged();
        canLoadMore = true;
    }

    public void showError(Throwable e) {
        progressBar.setVisibility(GONE);

        if (e != null && e.getMessage() != null) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static class Adapter extends RecyclerView.Adapter<HeaderVH> {
        private List<NewsHeadersContent> headersContents;
        private ClickListener clickListener;
        private String screenName;

        public Adapter(List<NewsHeadersContent> headersContents) {
            this.headersContents = headersContents;
            clickListener = new ClickListener() {
                @Override
                public void itemClicked(String value, int pos) {
                    if (screenName.split("#").length > 2) {
                        App.bus.post(new ChangeScreenEvent(screenName + "#" + value + pos));
                    } else {
                        App.bus.post(new ChangeScreenEvent(screenName + "#" + value));
                    }
                }
            };
            screenName = Router.NEWS;
        }

        public void setHeaders(List<NewsHeadersContent> headers) {
            headersContents.addAll(headers);
        }

        @Override
        public HeaderVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_header, parent, false);
            return new HeaderVH(view);
        }

        @Override
        public void onBindViewHolder(HeaderVH holder, final int position) {

            final NewsHeadersContent header = headersContents.get(position);
            Picasso.with(holder.itemView.getContext()).load(header.getImage()).into(holder.image);
            holder.header.setText(Html.fromHtml(header.getHeader()));
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.itemClicked(header.getLink(), position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return headersContents.size();
        }
    }

    public static class HeaderVH extends RecyclerView.ViewHolder {

        @Bind(R.id.header_image)
        ImageView image;
        @Bind(R.id.header)
        TextView header;

        public HeaderVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ClickListener {
        void itemClicked(String value, int pos);
    }
}
