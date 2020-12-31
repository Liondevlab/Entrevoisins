package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_details.DetailsNeighbourActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Entrevoisins
 * Created by LioNDeVLaB on 28/12/2020
 */
public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

	private final List<Neighbour> mNeighbours;
	private Context context;

	public MyFavoriteRecyclerViewAdapter(Context context, List<Neighbour> items) {
		mNeighbours = items;
		this.context = context;
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_neighbour, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final MyFavoriteRecyclerViewAdapter.ViewHolder holder, int position) {
		Neighbour neighbour = mNeighbours.get(position);
		holder.mNeighbourName.setText(neighbour.getName());
		Glide.with(holder.mNeighbourAvatar.getContext())
				.load(neighbour.getAvatarUrl())
				.apply(RequestOptions.circleCropTransform())
				.into(holder.mNeighbourAvatar);

		holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
			}
		});

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent detailsNeighbourActivityIntent = new Intent(holder.itemView.getContext(), DetailsNeighbourActivity.class);
				detailsNeighbourActivityIntent.putExtra("position", holder.getAdapterPosition() );
				detailsNeighbourActivityIntent.putExtra("fragment", "Neighbour" );
				holder.itemView.getContext().startActivity(detailsNeighbourActivityIntent);
			}
		});
	}

	@Override
    public int getItemCount() {
        return mNeighbours.size();
    }
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.item_list_avatar)
		public ImageView mNeighbourAvatar;
		@BindView(R.id.item_list_name)
		public TextView mNeighbourName;
		@BindView(R.id.item_list_delete_button)
		public ImageButton mDeleteButton;

		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}
