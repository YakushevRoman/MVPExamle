package e.user301.mvpexamle.Common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import e.user301.mvpexamle.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    public static final String TAG = "UserAdapter";
    List <User> rUsers;

    public UserAdapter() {
        rUsers = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        userHolder.bind(rUsers.get(i));
    }

    public void setData(List<User> rUsers){
        this.rUsers.clear();
        this.rUsers.addAll(rUsers);
        notifyDataSetChanged();
        Log.d(TAG, "setData: " + rUsers.size());

    }

    @Override
    public int getItemCount() {
        return rUsers.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_item_recycler_view);
        }

        void bind (User user){
            textView.setText(String.format("id: %s, name: %s, email: %s ", user.getId(), user.getName(), user.getEmail()));
        }
    }
}
