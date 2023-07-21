package com.areaonline.user.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.areaonline.R;
import com.areaonline.user.activity.Detail_Page_Activity;
import com.areaonline.utils.TouchImageView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    ArrayList image_List;

    public SliderAdapterExample(Context context, ArrayList imageList) {
        this.context = context;
        this.image_List = imageList;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_itemlayout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        Glide.with(context).load(image_List.get(position)).placeholder(context.getResources().getDrawable(R.drawable.loading)).into(viewHolder.imageViewBackground);

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(image_List.get(position).toString());
            }
        });
    }
    private void popup(String image) {
        Log.e( "popup: ", "fff");
        Dialog dialog = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.item_image_popup, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TouchImageView dialog_imageview =  dialog.findViewById(R.id.dialog_imageview);
        Glide.with(context).load(image).into(dialog_imageview);
        ImageButton btnCancel =  dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        dialog.show();
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return image_List.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_slider);

            this.itemView = itemView;
        }
    }

}