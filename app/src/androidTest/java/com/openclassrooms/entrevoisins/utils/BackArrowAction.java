package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import com.openclassrooms.entrevoisins.R;
import org.hamcrest.Matcher;


/**
 * Entrevoisins
 * Created by LioNDeVLaB on 03/01/2021
 */
public class BackArrowAction implements ViewAction{

	@Override
	public Matcher<View> getConstraints() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Click on BackArrow button";
	}

	@Override
	public void perform(UiController uiController, View view) {
		View button = view.findViewById(R.id.backArrow);
		// Maybe check for null
		button.performClick();
	}
}
