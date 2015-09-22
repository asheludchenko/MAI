package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.presentation.events.ChangeScreenEvent;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;
import oleg.osipenko.mai.presentation.utils.SimpleDividerItemDecoration;
import oleg.osipenko.mai.presentation.utils.SimpleSectionListAdapter;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListContentView extends RecyclerView {

    @Inject
    ListContentScreen.Presenter presenter;

    private Adapter adapter;


    public ListContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        setLayoutManager(layoutManager);
        setItemAnimator(new DefaultItemAnimator());
        addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new Adapter(Collections.EMPTY_LIST, presenter.getParameter());
        setAdapter(adapter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

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
        adapter.setContents(contents);
        adapter.notifyDataSetChanged();
    }

    public void showWithSections(List<ListContent> contents, SimpleSectionListAdapter.Section[] sections) {
        adapter = new Adapter(contents, presenter.getParameter());
        SimpleSectionListAdapter sectionedAdapter = new SimpleSectionListAdapter(
                getContext(),
                R.layout.section,
                R.id.section_text,
                adapter);
        sectionedAdapter.setSections(sections);
        swapAdapter(sectionedAdapter, true);
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ListContent> contents;
        private ClickListener listener;
        private String screenName;

        public Adapter(List<ListContent> contents, String parameter) {
            this.contents = contents;
            listener = new ClickListener() {
                @Override
                public void itemClicked(String value) {
                    App.bus.post(new ChangeScreenEvent(screenName + "#" + value));
                }
            };
            screenName = parameter;
        }

        public void setContents(List<ListContent> contents) {
            this.contents.clear();
            this.contents = contents;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ListContent item = contents.get(position);
            holder.text.setVisibility(
                    item.getText() == null? GONE : VISIBLE
            );
            holder.image.setVisibility(
                    item.getImage() == null || !item.isWithImage() ? GONE : VISIBLE
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
            if (item.getText() != null) holder.text.setText(item.getText());
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
            if (item.getImage() == null && item.isWithImage()) {
                Uri uri = Uri.parse(item.getImage());
                holder.image.setImageURI(uri);
            }
            if (item.getTitle() != null) holder.title.setText(item.getTitle());
            if (item.getSub2() != null) holder.sub2.setText(item.getSub2());
            if (item.getSub3() != null) holder.sub3.setText(item.getSub3());
            if (item.getSub4() != null) holder.sub4.setText(item.getSub4());
            if (item.isClickable()) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.itemClicked(item.getText());
                    }
                });
            } else {
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
        void itemClicked(String value);
    }
}
