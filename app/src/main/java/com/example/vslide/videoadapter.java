package com.example.vslide;

import android.icu.text.CaseMap;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class videoadapter extends FirebaseRecyclerAdapter<videomodel,videoadapter.myviewholder>
{


    public videoadapter(@NonNull FirebaseRecyclerOptions<videomodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull videomodel model)
    {
        holder.setdata(model);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        VideoView videoView;
        TextView title , desc;
        ProgressBar pbar;


        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            videoView = (VideoView)itemView.findViewById(R.id.videoView);
            title=(TextView)itemView.findViewById(R.id.textVideoTitle);
            desc=(TextView)itemView.findViewById(R.id.textVideoDescription);
            pbar=(ProgressBar) itemView.findViewById(R.id.videoProgressBar);
        }

        void setdata(videomodel obj)
        {
            videoView.setVideoPath(obj.getUrl());
            Log.d("test", obj.getUrl());
            title.setText(obj.getTitle());
            desc.setText(obj.getDesc());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pbar.setVisibility(View.GONE);

                    mp.start();

                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });

        }

    }




    //-------end-------
}
