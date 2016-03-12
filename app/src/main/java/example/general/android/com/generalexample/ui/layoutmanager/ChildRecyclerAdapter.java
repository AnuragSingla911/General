package example.general.android.com.generalexample.ui.layoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import example.general.android.com.generalexample.R;
import example.general.android.com.generalexample.Utility;
import example.general.android.com.generalexample.modal.Section;

/**
 * Created by jade on 12/3/16.
 */
public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder> {

    Context mContext;
    Section mSection;

    public ChildRecyclerAdapter(Context context, Section section) {
        mSection = section;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater infllater = LayoutInflater.from(mContext);
        View view = infllater.inflate(R.layout.single_item, parent, false);
        return new ViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso
                .with(mContext)
                .load(mSection.getmItems().get(0).getmImageUrl())
                .centerCrop()
                .placeholder(android.R.drawable.btn_radio)
                .fit()
                .into(holder.mImageView);

        holder.mImageView.setTag(mSection.getmItems().get(position).getWebUrl());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (String) v.getTag();
                Utility.handleClick(s, mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSection.getmItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        Context mContext;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            mContext = context;
            mImageView = (ImageView)itemView.findViewById(R.id.imageView);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)mImageView.getLayoutParams();
            params.width = mContext.getResources().getDisplayMetrics().widthPixels/3;

        }
    }
}
