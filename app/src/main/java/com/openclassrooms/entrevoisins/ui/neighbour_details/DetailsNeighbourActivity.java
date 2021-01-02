package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends AppCompatActivity {

	// Declaration des variables pour recuperation des information Neighbour
	@BindView(R.id.backArrow)
	AppCompatImageButton mBackArrow;
	@BindView(R.id.personNameHeader)
	TextView mPersonNameHeader;
	@BindView(R.id.detailsAvatar)
	ImageView mDetailsAvatar;
	@BindView(R.id.addNeighbourFavorites)
	FloatingActionButton mAddNeighbourFavorites;
	@BindView(R.id.PersonName)
	TextView mPersonName;
	@BindView(R.id.postalAddress)
	TextView mPostalAddress;
	@BindView(R.id.phone)
	TextView mPhone;
	@BindView(R.id.facebook)
	TextView mFacebook;
	@BindView(R.id.aboutMe)
	TextView mAboutMe;
	@BindView(R.id.aboutMeText)
	TextView mAboutMeText;

	private Neighbour neighbour;
	private NeighbourApiService mApiService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_neighbour);
		ButterKnife.bind(this);
		mApiService = DI.getNeighbourApiService();
		this.neighbour = (Neighbour) getIntent().getExtras().getSerializable("Neighbour");
		updateDataOnView();
		//Back Button
		this.mBackArrow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		NeighbourFavoriteState(neighbour);
		/**
		 * TODO
		 * Ajout/Suppression des favoris
		 */
		mAddNeighbourFavorites.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getIntent().hasExtra("Neighbour");
				if (mApiService.getFavorites().contains(neighbour)) {
					mAddNeighbourFavorites.setActivated(false);
					mApiService.deleteFromFavorite(neighbour);
				} else {
					mAddNeighbourFavorites.setActivated(true);
					mApiService.addToFavorite(neighbour);
				}
			}
		});
	}

	private void NeighbourFavoriteState(Neighbour neighbour) {
		this.neighbour = (Neighbour) getIntent().getExtras().getSerializable("Neighbour");
		assert neighbour != null;
		if (mApiService.getFavorites().contains(neighbour)) {
			mAddNeighbourFavorites.setActivated(true);
		} else {
			mAddNeighbourFavorites.setActivated(false);
		}
	}

	/**
	 * Methode qui rappatrie les infos du neighbour selectionné dans la vue
	 */
	private void updateDataOnView() {
		Glide.with(mDetailsAvatar.getContext())
				.load(neighbour.getAvatarUrl())
				.into(mDetailsAvatar);
		this.mPersonNameHeader.setText(neighbour.getName());
		this.mPersonName.setText(neighbour.getName());
		this.mPostalAddress.setText(neighbour.getAddress());
		this.mPhone.setText(neighbour.getPhoneNumber());
		this.mFacebook.setText(neighbour.getFacebook());
		this.mAboutMeText.setText(neighbour.getAboutMe());
	}
}
