package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;

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
        adapter = new Adapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        setLayoutManager(layoutManager);
        setItemAnimator(new DefaultItemAnimator());
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

    public void showText(List<ListContent> contents) {
        adapter.setContents(contents);
        adapter.notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ListContent> contents;

        public Adapter() {
            this.contents = Collections.emptyList();
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
            holder.textView.setText(contents.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
