package example.general.android.com.generalexample.ui.layoutmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import example.general.android.com.generalexample.R;
import example.general.android.com.generalexample.Utility;
import example.general.android.com.generalexample.modal.MainModal;

/**
 * Created by jade on 12/3/16.
 */
public class ParentRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MainModal modal;
    Context context;

    public ParentRecycleAdapter(Context context, MainModal modal) {
        this.modal = modal;
        this.context = context;
        Toast.makeText(context, modal.getmSections().size() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case 1:
                View view = inflater.inflate(R.layout.single_item, parent, false);
                return new ImageHolder(view);
            case 2:
                View view2 = inflater.inflate(R.layout.recycler_view, parent, false);
                return new TemplateSecond(view2);
            case 3:
                View view3 = inflater.inflate(R.layout.view_pager, parent, false);
                return new TemplateThree(view3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ImageHolder) {
            ImageHolder holder1 = (ImageHolder)holder;
            Picasso
                    .with(context)
                    .load(modal.getmSections().get(position).getmItems().get(0).getmImageUrl())
                    .centerCrop()
                    .placeholder(android.R.drawable.btn_radio)
                    .fit()
                    .into(holder1.mImageView);
            holder1.mImageView.setTag(modal.getmSections().get(position).getmItems().get(0).getWebUrl());
            holder1.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = (String)v.getTag();
                    Utility.handleClick(s, context);
                }
            });


        } else if (holder instanceof TemplateThree) {
            final TemplateThree templateThree = (TemplateThree) holder;
            templateThree.viewPager.setAdapter(new viewPagerAdapter(context, modal.getmSections().get(position)));
            templateThree.indicater.setIndicatorCount(modal.getmSections().get(position).getmItems().size());
            templateThree.indicater.setCurrentItem(0);
            templateThree.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    templateThree.indicater.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    templateThree.indicater.onPageSelected(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    templateThree.indicater.onPageScrollStateChanged(state);
                }
            });
        } else if (holder instanceof TemplateSecond) {
            TemplateSecond second = (TemplateSecond) holder;
            second.mRecyclerView.setAdapter(new ChildRecyclerAdapter(context, modal.getmSections().get(position)));
            second.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            second.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                    super.onDraw(c, parent, state);
                }

                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);

                    if(parent.getChildAdapterPosition(view) != 0){
                        outRect.left = 10;
                    }

                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        String template = modal.getmSections().get(position).getmTemplate();
        if ("product-template-1".equals(template)) {
            return 1;
        } else if ("product-template-2".equals(template)) {
            return 2;
        } else if ("product-template-3".equals(template)) {
            return 3;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return modal.getmSections().size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        public final ImageView mImageView;

        public ImageHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public static class TemplateSecond extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public TemplateSecond(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.card_recycler_view);
        }
    }

    public static class TemplateThree extends RecyclerView.ViewHolder {

        ViewPager viewPager;
        PageIndicater indicater;


        public TemplateThree(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
            indicater = (PageIndicater) itemView.findViewById(R.id.pager_indicater);
        }
    }
}
