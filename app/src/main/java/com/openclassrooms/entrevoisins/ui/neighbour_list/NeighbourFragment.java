package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailsNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_details.DetailsNeighbourActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NeighbourFragment extends Fragment{

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    @BindView(R.id.list_neighbours)
    public RecyclerView mRecyclerView;
    private boolean mIsFavorite;


    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorite) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle args = new Bundle();
        args.putBoolean("mIsFavorite", isFavorite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        mIsFavorite = args.getBoolean("mIsFavorite", false);
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        Bundle args = getArguments();
        boolean mIsFavorite = args.getBoolean("mIsFavorite", false);
        if (!mIsFavorite) {
            mNeighbours = mApiService.getNeighbours();
        } else {
            mNeighbours = mApiService.getFavorites();
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, mIsFavorite));
    }

    @Override
    public void onStart() {
        super.onStart();
        initList();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if (event.getIfNeighbourIsFavorite()) {
            mApiService.removeFromFavorite(event.neighbour);
        } else {
            mApiService.deleteNeighbour(event.neighbour);
        }
        initList();
    }

    /**
     * Fired if the user clicks on a Neighbour
     *
     * @param event
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDetailsNeighbour(DetailsNeighbourEvent event) {
        DetailsNeighbourEvent stickyEvent = EventBus.getDefault().removeStickyEvent(DetailsNeighbourEvent.class);
        if (stickyEvent != null) {
            Intent detailsNeighbourActivityIntent = new Intent(getContext(), DetailsNeighbourActivity.class);
            detailsNeighbourActivityIntent.putExtra("Neighbour", event.neighbour);
            startActivity(detailsNeighbourActivityIntent);
        }
    }
}
