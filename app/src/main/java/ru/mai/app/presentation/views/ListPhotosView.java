package ru.mai.app.presentation.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.app.R;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.dataModel.Photo;
import ru.mai.app.presentation.PhotoActivity;
import ru.mai.app.presentation.screens.PhotoScreen;
import ru.mai.app.presentation.utils.SimpleDividerItemDecoration;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListPhotosView extends FrameLayout {

    @Inject
    PhotoScreen.Presenter presenter;
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.pb)
    ProgressBar progressBar;

    private Adapter adapter;


    public ListPhotosView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        adapter = new Adapter(Collections.EMPTY_LIST);
        list.setAdapter(adapter);
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
        progressBar.setVisibility(GONE);
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ListContent> contents;

        public Adapter(List<ListContent> contents) {
            this.contents = contents;
        }

        public void setContents(List<ListContent> contents) {
            this.contents.clear();
            this.contents = contents;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final ListContent item = contents.get(position);
            holder.title.setText(item.getAlbumTitle());
            PhotosAdapter adapter = new PhotosAdapter(item.getPhotos());
            holder.photos.setAdapter(adapter);
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.photos)
        RecyclerView photos;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            photos.setLayoutManager(layoutManager);
            photos.setItemAnimator(new DefaultItemAnimator());
            photos.addItemDecoration(new SimpleDividerItemDecoration(itemView.getContext()));
        }
    }

    public static class PhotosAdapter extends RecyclerView.Adapter<PhotosVH> {
        private List<Photo> photos;

        public PhotosAdapter(List<Photo> photos) {
            this.photos = photos;
        }


        @Override
        public PhotosVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo, parent, false);
            return new PhotosVH(view);
        }

        @Override
        public void onBindViewHolder(final PhotosVH holder, final int position) {
            holder.photo.setImageURI(Uri.parse(photos.get(position).getOriginal()));
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showPhoto = new Intent(holder.itemView.getContext(), PhotoActivity.class);
                    showPhoto.putExtra(PhotoActivity.URI, photos.get(position).getOriginal());
                    holder.itemView.getContext().startActivity(showPhoto);
                }
            });
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }
    }

    public static class PhotosVH extends RecyclerView.ViewHolder {
        @Bind(R.id.photo)
        SimpleDraweeView photo;

        public PhotosVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
