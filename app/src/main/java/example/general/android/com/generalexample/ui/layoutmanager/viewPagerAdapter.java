package example.general.android.com.generalexample.ui.layoutmanager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import example.general.android.com.generalexample.R;
import example.general.android.com.generalexample.Utility;
import example.general.android.com.generalexample.modal.Section;

public class viewPagerAdapter extends PagerAdapter {

    private Section msection;
    private Context mcontext;

    public viewPagerAdapter(Context context, Section section) {
        mcontext = context;
        msection = section;
    }

    @Override
    public int getCount() {
        return msection.getmItems().size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.single_item, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        Picasso
                .with(mcontext)
                .load(msection.getmItems().get(0).getmImageUrl())
                .centerCrop().fit()
                .placeholder(android.R.drawable.btn_radio)
                .into(image);
        image.setTag(msection.getmItems().get(position).getWebUrl());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (String)v.getTag();
                Utility.handleClick(s, mcontext);

            }
        });
        container.addView(view,0);
        return view;

    }
}
