package com.ammach.mybubbles;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by ammach on 8/23/2016.
 */
public class MessageAdapter extends BaseAdapter {


    List<Message> messages;
    Context context;
    MainActivity mainActivity;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
        mainActivity= (MainActivity) context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(messages.get(position).opt=="1"){
            if(messages.get(position).getText()!=null){
                convertView=layoutInflater.inflate(R.layout.item_left,null);
                EmojiTextView textView= (EmojiTextView) convertView.findViewById(R.id.txt);
                textView.setText(messages.get(position).text);

            }

            else if (String.valueOf(messages.get(position).getVideoUrl())!=null){
                convertView=layoutInflater.inflate(R.layout.item_video,null);
                JCVideoPlayerStandard jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
                jcVideoPlayer.titleTextView.setVisibility(View.INVISIBLE);
                boolean setUp = jcVideoPlayer.setUp(messages.get(position).getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,"test");
                if (setUp) {
//                    Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(messages.get(position).getVideoUrl(), MediaStore.Images.Thumbnails.MINI_KIND);
//                    jcVideoPlayer.thumbImageView.setImageDrawable(new BitmapDrawable(bitmap));
                    Picasso.with(context).load("http://www.therealmadridfan.com/wp-content/uploads/madridista.jpg").into(jcVideoPlayer.thumbImageView);
                }
                else {
                    Toast.makeText(context,"not setup",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            if(messages.get(position).getText()!=null){
                convertView=layoutInflater.inflate(R.layout.item_right,null);
                TextView textView= (TextView) convertView.findViewById(R.id.txt);
                textView.setText(messages.get(position).text);

            }
           else if (String.valueOf(messages.get(position).getImgMessage())!=null) {
                convertView=layoutInflater.inflate(R.layout.item_image,null);
                final ImageView imageView= (ImageView) convertView.findViewById(R.id.imgMsg);
                imageView.setImageURI(Uri.parse(messages.get(position).getImgMessage()));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.animImage(imageView);
                    }
                });
            }

        }



        return convertView;
    }



}
