package com.deazzle.deazzleassignment.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deazzle.deazzleassignment.R;
import com.deazzle.deazzleassignment.database.RandomUser;
import com.deazzle.deazzleassignment.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomUserListAdapter extends RecyclerView.Adapter<RandomUserListAdapter.ViewHolder> {

    private List<Result> users;
    Context context;

    public RandomUserListAdapter(Context context,List<Result> users){
        this.context=context;
        this.users=users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result user=users.get(position);
        holder.user_name.setText(user.getName().getFirst()+" "+ user.getName().getLast());
        holder.user_phone.setText(user.getPhone());
        holder.user_email.setText(user.getEmail());
        Picasso.get().load(user.getPicture().getMedium()).placeholder(context.getResources().getDrawable(R.drawable.loading)).fit().centerCrop().into(holder.userImage);
        holder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.like_text.setVisibility(View.VISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.like_text.setVisibility(View.GONE);
                        users.remove(position);
                        notifyItemRemoved(position);
                    }
                },1000);
            }
        });
        holder.rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.reject_text.setVisibility(View.VISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.reject_text.setVisibility(View.GONE);
                        users.remove(position);
                        notifyItemRemoved(position);
                    }
                },1000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage,liked,rejected;
        TextView user_name, user_phone, user_email,like_text,reject_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.name);
            user_phone=itemView.findViewById(R.id.phone);
            user_email=itemView.findViewById(R.id.email);
            userImage=itemView.findViewById(R.id.user_image);
            liked=itemView.findViewById(R.id.accept);
            rejected=itemView.findViewById(R.id.reject);
            like_text=itemView.findViewById(R.id.like_text);
            reject_text=itemView.findViewById(R.id.reject_text);
        }
    }
}
