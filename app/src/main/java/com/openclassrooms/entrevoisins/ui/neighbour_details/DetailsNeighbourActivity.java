package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import org.greenrobot.eventbus.EventBus;

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
	String fragment;
	int position;

	/**
	 * Used to navigate to this activity
	 * @param activity
	 */
	public static void navigate(View.OnClickListener activity) {
		Intent intent = new Intent((Context) activity, DetailsNeighbourActivity.class);
		ActivityCompat.startActivity((Context) activity, intent, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_neighbour);
		ButterKnife.bind(this);
		this.position = getIntent().getExtras().getInt("position");
		this.fragment = getIntent().getExtras().getString("fragment");
		this.neighbour = fragmentPosition(fragment, position);
		updateDataOnView();
		//Back Button
		this.mBackArrow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		/**
		 * Ajout/Suppression des favoris
		 */
		this.mAddNeighbourFavorites.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
	/**
	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}
	*/
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

	private Neighbour fragmentPosition(String fragment, int position) {
		if (fragment.equals("Neighbour")) {
			return DI.getNeighbourApiService().getNeighbours().get(position);
		} else {
			return null;
		}
	}

}
