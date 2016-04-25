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

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.R;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.dataModel.Photo;
import ru.mai.app.presentation.PhotoActivity;
import ru.mai.app.presentation.screens.ListPhotoScreen;
import ru.mai.app.presentation.utils.SimpleDividerItemDecoration;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListPhotosView extends FrameLayout {

    @Inject
    ListPhotoScreen.Presenter presenter;
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.pb)
    ProgressBar progressBar;

    private Adapter adapter;
    private boolean canLoadMore = true;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    public ListPhotosView(Context context, AttributeSet attrs) {
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
        list.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        adapter = new Adapter(new ArrayList<ListContent>());
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

    public void showItems(List<ListContent> contents) {
        adapter.setContents(contents);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(GONE);
        canLoadMore = true;
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ListContent> contents;

        public Adapter(List<ListContent> contents) {
            this.contents = contents;
        }

        public void setContents(List<ListContent> photos) {
            this.contents.addAll(photos);
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
            Uri uri = Uri.parse(photos.get(position).getOriginal());
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setResizeOptions(new ResizeOptions(140, 140))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setOldController(holder.photo.getController())
                    .setImageRequest(request)
                    .build();
            holder.photo.setController(controller);
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showPhoto = new Intent(holder.itemView.getContext(), PhotoActivity.class);
                    showPhoto.putExtra(PhotoActivity.PHOTOS, (ArrayList) photos);
                    showPhoto.putExtra(PhotoActivity.POSITION, position);
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
