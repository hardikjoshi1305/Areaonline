package com.areaonline.shopowner.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.modal.Tracking_Response;
import com.areaonline.utils.CommandMethod;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Tracking_Response.ShipmentTrackActivity> timeLineModelList;
        private Context context;

        public TimeLineAdapter(Context context, List<Tracking_Response.ShipmentTrackActivity> timeLineModelList) {
            this.timeLineModelList = timeLineModelList;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracklist, parent, false);
            return new ViewHolder(view, viewType);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder) holder).tv_activity.setText("Activity : "+timeLineModelList.get(position).getStatus());
            ((ViewHolder) holder).tv_location.setText(timeLineModelList.get(position).getLocation());

                ((ViewHolder)holder).tv_time.setText(CommandMethod.TimeFormate(timeLineModelList.get(position).getDate().toString()));
                ((ViewHolder)holder).tv_day.setText(CommandMethod.Orderdateformate(timeLineModelList.get(position).getDate().toString()));



//            if (timeLineModelList.get(position).getStatus().equals("inactive"))
//                ((ViewHolder) holder).timeline.setMarker(context.getDrawable(ic_remove_circle_outline_black_24dp));
//            else
//                ((ViewHolder) holder).timeline.setMarker(context.getDrawable(ic_check_circle_black_24dp));
        }

        @Override
        public int getItemViewType(int position) {
            return TimelineView.getTimeLineViewType(position, getItemCount());
        }

        @Override
        public int getItemCount() {
            return timeLineModelList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            TimelineView timeline;
            TextView tv_time, tv_day,tv_activity, tv_location;

            ViewHolder(View itemView, int viewType) {
                super(itemView);
                timeline = itemView.findViewById(R.id.timeline);
                tv_time = itemView.findViewById(R.id.tv_time);
                tv_activity = itemView.findViewById(R.id.tv_activity);
                tv_location = itemView.findViewById(R.id.tv_location);
                tv_day = itemView.findViewById(R.id.tv_day);

                timeline.initLine(viewType);
            }
        }}