package com.zua.kelefun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zua.kelefun.R;
import com.zua.kelefun.data.model.Status;
import com.zua.kelefun.util.DateAgo;
import com.zua.kelefun.util.LogHelper;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Status> data;

    public RecyclerViewAdapter(Context mContext, List data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
//                    }
//                });
//                animator.start();
                LogHelper.d("点击了card");
            }
        });
        if(data.size()!=0){
            Status status= data.get(position);
            holder.screenNameView.setText(status.getUser().getScreenName());
            holder.timeSourceView.setText(DateAgo.toAgo(status.getCreatedAt())+Html.fromHtml(status.getSource()).toString());
            holder.statusView.setText(status.getText());
            Picasso.with(mContext)
                    .load(status.getUser().getProfileImageUrl())
//                    .placeholder(R.drawable.tab_item_bg)
                    .into(holder.avatarView);
            if(status.getPhoto() != null){
                Picasso.with(mContext)
                        .load(status.getPhoto().getImageurl())
//                    .placeholder(R.drawable.tab_item_bg)
                        .into(holder.photoView);
            }else {
                holder.photoView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public TextView screenNameView;
        public TextView timeSourceView;
        public TextView statusView;
        public ImageView avatarView;
        public ImageView photoView;
        public Layout status_right_side;
//        public TextView replyUserView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            screenNameView = (TextView) itemView.findViewById(R.id.screenNameView);
            timeSourceView = (TextView) itemView.findViewById(R.id.timeSourceView);
            statusView = (TextView) itemView.findViewById(R.id.statusView);
            avatarView = (ImageView) itemView.findViewById(R.id.avatarView);
            photoView = (ImageView) itemView.findViewById(R.id.photoView);
        }
    }
}
