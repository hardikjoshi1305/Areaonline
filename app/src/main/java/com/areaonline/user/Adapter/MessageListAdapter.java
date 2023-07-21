package com.areaonline.user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;

public class MessageListAdapter{
//        extends RecyclerView.Adapter {
//    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
//    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
//
//    private Context mContext;
//    private List<BaseMessage> mMessageList;
//
//    public MessageListAdapter(Context context, List<BaseMessage> messageList) {
//        mContext = context;
//        mMessageList = messageList;
//    }
//
//    @Override
//    public int getItemCount() {
//        return mMessageList.size();
//    }
//
//    // Determines the appropriate ViewType according to the sender of the message.
//    @Override
//    public int getItemViewType(int position) {
//        UserMessage message = (UserMessage) mMessageList.get(position);
//
//        if (message.getSender().getUserId().equals(SendBird.getCurrentUser().getUserId())) {
//            // If the current user is the sender of the message
//            return VIEW_TYPE_MESSAGE_SENT;
//        } else {
//            // If some other user sent the message
//            return VIEW_TYPE_MESSAGE_RECEIVED;
//        }
//    }
//
//    // Inflates the appropriate layout according to the ViewType.
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view;
//
//        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.listitem_sender, parent, false);
//            return new SentMessageHolder(view);
//        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.listitem_receiver, parent, false);
//            return new ReceivedMessageHolder(view);
//        }
//
//        return null;
//    }
//
//    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        UserMessage message = (UserMessage) mMessageList.get(position);
//
//        switch (holder.getItemViewType()) {
//            case VIEW_TYPE_MESSAGE_SENT:
//                ((SentMessageHolder) holder).bind(message);
//                break;
//            case VIEW_TYPE_MESSAGE_RECEIVED:
//                ((ReceivedMessageHolder) holder).bind(message);
//        }
//    }
//
//    private class SentMessageHolder extends RecyclerView.ViewHolder {
//        TextView text_gchat_message_me, text_gchat_timestamp_me;
//
//        SentMessageHolder(View itemView) {
//            super(itemView);
//
//            text_gchat_message_me = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
//            text_gchat_timestamp_me = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
//        }
//
//        void bind(UserMessage message) {
//            text_gchat_message_me.setText(message.getMessage());
//
//            // Format the stored timestamp into a readable String using method.
//            text_gchat_timestamp_me.setText(Utils.formatDateTime(message.getCreatedAt()));
//        }
//    }
//
//    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
//        TextView messageText, timeText, nameText;
//        ImageView profileImage;
//
//        ReceivedMessageHolder(View itemView) {
//            super(itemView);
//
//            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
//            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
//            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
//            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
//        }
//
//        void bind(UserMessage message) {
//            messageText.setText(message.getMessage());
//
//            // Format the stored timestamp into a readable String using method.
//            timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
//
//            nameText.setText(message.getSender().getNickname());
//
//            // Insert the profile image from the URL into the ImageView.
//            Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
//        }
//    }
}