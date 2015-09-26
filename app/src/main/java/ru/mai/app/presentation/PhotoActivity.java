package ru.mai.app.presentation;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import oleg.osipenko.app.R;

/**
 * Created by olegosipenko on 26.09.15.
 */
public class PhotoActivity extends Activity {

    public static final String URI = "uri to open";

    @Bind(R.id.photo)
    SimpleDraweeView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        ButterKnife.bind(this);
        String uri = getIntent().getStringExtra(URI);
        photo.setImageURI(Uri.parse(uri));
    }
}
