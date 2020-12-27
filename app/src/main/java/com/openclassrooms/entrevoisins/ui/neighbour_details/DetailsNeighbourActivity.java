package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.AddNeighbourActivity;

public class DetailsNeighbourActivity extends AppCompatActivity {

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
	}
}
