package ru.mai.app.presentation.views;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.Router;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.presentation.events.ChangeScreenEvent;
import ru.mai.app.presentation.screens.ListContentScreen;
import ru.mai.app.presentation.utils.SimpleDividerItemDecoration;
import ru.mai.app.presentation.utils.SimpleSectionListAdapter;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListContentView extends RelativeLayout {

    @Inject
    ListContentScreen.Presenter presenter;
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.hint_view)
    TextView hint;
    @Bind(R.id.pb)
    ProgressBar progressBar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.del)
    View del;

    private Adapter adapter;
    private boolean canLoadMore = true;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    public ListContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        if (presenter.getParameter().equals(Router.CANTEENS)) hint.setVisibility(VISIBLE);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        adapter = new Adapter(new ArrayList<ListContent>(), presenter.getParameter());
        list.setAdapter(adapter);
        if (presenter.getParameter().equals(Router.NEWS) ||
                presenter.getParameter().equals(Router.PHOTO)) {
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

    public void showItems(List<ListContent> contents) {
        progressBar.setVisibility(GONE);
        list.setVisibility(VISIBLE);
        if (!contents.isEmpty() && !TextUtils.isEmpty(contents.get(0).getFacTitle())) {
            title.setVisibility(VISIBLE);
            del.setVisibility(VISIBLE);
            title.setText(contents.get(0).getFacTitle());
            adapter.setContents(new ArrayList<ListContent>(contents.subList(1, contents.size())));
        } else {
            adapter.setContents(contents);
        }
        adapter.notifyDataSetChanged();
        canLoadMore = true;
    }

    public void showWithSections(List<ListContent> contents, SimpleSectionListAdapter.Section[] sections) {
        progressBar.setVisibility(GONE);
        list.setVisibility(VISIBLE);
        adapter = new Adapter(contents, presenter.getParameter());
        SimpleSectionListAdapter sectionedAdapter = new SimpleSectionListAdapter(
                getContext(),
                R.layout.section,
                R.id.section_text,
                adapter);
        sectionedAdapter.setSections(sections);
        list.swapAdapter(sectionedAdapter, true);
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ListContent> contents;
        private ClickListener listener;
        private String screenName;
        private boolean isRound;

        public Adapter(List<ListContent> contents, String parameter) {
            this.contents = contents;
            listener = new ClickListener() {
                @Override
                public void itemClicked(String value, int pos) {
                    if (screenName.split("#").length > 2) {
                        App.bus.post(new ChangeScreenEvent(screenName + "#" + value + pos));
                    } else {
                        App.bus.post(new ChangeScreenEvent(screenName + "#" + value));
                    }
                }
            };
            screenName = parameter;
            isRound = !parameter.startsWith(Router.FACULTIES);
        }

        public void setContents(List<ListContent> items) {
            contents.addAll(items);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final ListContent item = contents.get(position);
            holder.text.setVisibility(
                    item.getText() == null ? GONE : VISIBLE
            );
            holder.image.setVisibility(
                    item.getImage() == null ? GONE : VISIBLE
            );
            holder.title.setVisibility(
                    item.getTitle() == null ? GONE : VISIBLE
            );
            holder.sub2.setVisibility(
                    item.getSub2() == null ? GONE : VISIBLE
            );
            holder.sub3.setVisibility(
                    item.getSub3() == null ? GONE : VISIBLE
            );
            holder.sub4.setVisibility(
                    item.getSub4() == null ? GONE : VISIBLE
            );
            if (item.getText() != null) holder.text.setText(Html.fromHtml(item.getText()));
            if (!isRound) {
                RoundingParams roundingParams = holder.image.getHierarchy().getRoundingParams();
                roundingParams.setRoundAsCircle(false);
                holder.image.getHierarchy().setRoundingParams(roundingParams);
                holder.image.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
            } else {
                RoundingParams roundingParams = holder.image.getHierarchy().getRoundingParams();
                roundingParams.setRoundAsCircle(true);
                holder.image.getHierarchy().setRoundingParams(roundingParams);
                holder.image.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
            }
            if (item.getImage() != null && !item.isWithImage()) {
                Uri uri = Uri.parse(item.getImage());
                holder.image.setImageURI(uri);
            }
            if (item.getImage() != null && item.isWithImage()) {
                Uri uri = new Uri.Builder()
                        .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                        .path(item.getImage())
                        .build();
                holder.image.setImageURI(uri);
            }
            /*if (item.getImage() == null && item.isWithImage()) {
                Uri uri = Uri.parse(item.getImage());
                holder.image.setImageURI(uri);
            }*/
            if (item.getTitle() != null) holder.title.setText(item.getTitle());
            if (item.getSub2() != null) holder.sub2.setText(item.getSub2());
            if (item.getSub3() != null) holder.sub3.setText(item.getSub3());
            if (item.getSub4() != null) holder.sub4.setText(item.getSub4());
            if (item.isClickable()) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.itemClicked(item.getText(), position);
                    }
                });
            }
            if (item.isDialogable()) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final CharSequence[] dialogMenu = Router.getDialogItems(item.getText());
                        AlertDialog.Builder dialog = new AlertDialog.Builder(holder.itemView.getContext())
                                .setItems(dialogMenu, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        listener.itemClicked(item.getText() + "#" + dialogMenu[i], position);
                                    }
                                });
                        dialog.show();
                    }
                });
            }
            if (item.getLink() != null) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.itemClicked(item.getLink(), position);
                    }
                });
            }
            if (!item.isDialogable() && !item.isClickable() && item.getLink() == null) {
                holder.itemView.setOnClickListener(null);
            }
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView text;
        @Bind(R.id.image)
        SimpleDraweeView image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.sub2)
        TextView sub2;
        @Bind(R.id.sub3)
        TextView sub3;
        @Bind(R.id.sub4)
        TextView sub4;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ClickListener {
        void itemClicked(String value, int pos);
    }
}
