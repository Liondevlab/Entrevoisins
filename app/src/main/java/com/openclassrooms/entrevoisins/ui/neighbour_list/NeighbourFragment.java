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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NeighbourFragment extends Fragment{

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    @BindView(R.id.list_neighbours)
    public RecyclerView mRecyclerView;
    private boolean mIsFavorite;
    private final static String IS_FAVORITE_KEY = "IS_FAVORITE_KEY";

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorite) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_FAVORITE_KEY, isFavorite);
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
        assert args != null;
        mIsFavorite = args.getBoolean(IS_FAVORITE_KEY, false);
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
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
        if (event !=null && event.getIfNeighbourIsFavorite() == mIsFavorite) {
            if (mIsFavorite) mApiService.removeFromFavorite(event.neighbour);
            else mApiService.deleteNeighbour(event.neighbour);
        }
        initList();
    }

    /**
     * Fired if the user clicks on a Neighbour
     *
     * @param event
     */
    @Subscribe
    public void onDetailsNeighbour(DetailsNeighbourEvent event) {
        if (event != null && event.getIfNeighbourIsFavorite() == mIsFavorite) {
            Intent detailsNeighbourActivityIntent = new Intent(getContext(), DetailsNeighbourActivity.class);
            detailsNeighbourActivityIntent.putExtra("Neighbour", event.neighbour);
            startActivity(detailsNeighbourActivityIntent);
        }
    }
}
