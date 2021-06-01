package com.example.io_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.io_app.MessageDB;
import com.example.io_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_OUT = 0;
    public static final int MSG_TYPE_IN = 1;

    private List<MessageDB> mMessage;
    private Context mContext;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<MessageDB> mMessage) {
        this.mContext = mContext;
        this.mMessage = mMessage;
    }

    @NotNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_OUT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_out_item_view, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_in_item_view, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NotNull MessageAdapter.ViewHolder holder, int position) {
        MessageDB messageDB = mMessage.get(position);

        holder.message.setText(messageDB.getMessage());


    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message;

        public ViewHolder(View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.messageText);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        if (mMessage.get(position).getUserSend().equals(fuser.getUid()))
            return MSG_TYPE_OUT;
        else
            return MSG_TYPE_IN;
    }
}
