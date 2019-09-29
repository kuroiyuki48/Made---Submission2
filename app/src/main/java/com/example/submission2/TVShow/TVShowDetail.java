package com.example.submission2.TVShow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission2.R;

import butterknife.ButterKnife;

public class TVShowDetail extends AppCompatActivity {

    ImageView imgPhoto,imgImage;
    TextView tvName, tvRelease, tvGenre, tvOverview, tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        ButterKnife.bind(this);

        TVShow parceget = getIntent().getParcelableExtra("tvshow");
        imgImage = findViewById (R.id.img_item_image);
        tvName = findViewById (R.id.tv_item_name);
        tvRelease = findViewById (R.id.tv_item_release);
        tvScore = findViewById (R.id.tv_item_score);
        tvGenre = findViewById (R.id.tv_item_genre);
        tvOverview = findViewById (R.id.tv_item_overview);
        imgPhoto = findViewById (R.id.img_item_photo);


        Glide.with(this)
                .load(parceget.getImage())
                .apply(new RequestOptions().override(1450,600).placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher).centerCrop()
                )
                .into(imgImage);
        tvName.setText(parceget.getName());
        tvRelease.setText(parceget.getRelease());
        tvScore.setText(parceget.getScore());
        tvGenre.setText(parceget.getGenre());
        tvOverview.setText(parceget.getOverview());
        Glide.with(this)
                .load(parceget.getPhoto())
                .apply(new RequestOptions().override(400,600).placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher).centerCrop()
                )
                .into(imgPhoto);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
