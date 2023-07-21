package com.areaonline.user;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.areaonline.R;
import com.bumptech.glide.Glide;

import static com.areaonline.user.activity.Welcome_Activity.arrayList;

public class C0825p extends PagerAdapter {

    /* renamed from: a */
    public final LayoutInflater f2053a;

    /* renamed from: b */
    public Context f2054b = null;

    public C0825p(Context context) {
        this.f2054b = context;
        this.f2053a = LayoutInflater.from(context);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
     return   arrayList.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.f2053a.inflate(R.layout.layout_help_screen, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        if (imageView != null) {
            try {
                Context context = this.f2054b;
                if (context != null) {
                    Glide.with(context).load(arrayList.get(i)).into(imageView);
                }
            } catch (Exception e2) {
                Log.e(C0825p.class.getSimpleName(), "picasso image loading issue: ${e.message}", e2);
            }
        }
        viewGroup.addView(inflate, 0);
        return inflate;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }
}

