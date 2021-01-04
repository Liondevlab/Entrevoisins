package com.openclassrooms.entrevoisins.details_and_favorites;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;



/**
* Test class for details of neighbours and Add/Remove to favorite list
* Entrevoisins
* Created by Julien Guerard on 02/01/2021
*/

@RunWith(AndroidJUnit4.class)
public class NeighbourDetailsAndFavoriteTest {

	// This is fixed
	private static final int ITEMS_COUNT = 12;

	private ListNeighbourActivity mActivity;
	// Including Neighbour object and ApiService for testing
	private NeighbourApiService mApiService;
	private List<Neighbour> mNeighbours;

	@Rule
	public ActivityTestRule<ListNeighbourActivity> mActivityRule =
			new ActivityTestRule(ListNeighbourActivity.class);

	@Before
	public void setUp() {
		mActivity = mActivityRule.getActivity();
		assertThat(mActivity, notNullValue());
		mApiService = DI.getNewInstanceApiService();
		mNeighbours = mApiService.getNeighbours();
	}

	/**
	 * When we select a neighbour in the list it open the details page
	 */
	@Test
	public void myNeighboursList_clickOnNeighbourAction_shouldShowDetailsPage() {
		// Given : Perform a click on a neighbour open his details
		onView(ViewMatchers.withId(R.id.list_neighbours))
				.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
		// When : The detailsNeighbourActivity is shown
		onView(withId(R.id.detailsNeighbour)).check(matches(isDisplayed()));
		// Then : Check if the detailsNeighbourActivity is shown with the good neighbour name
		onView(ViewMatchers.withId(R.id.personNameHeader)).check(matches(withText(mNeighbours.get(0).getName())));
	}

	/**
	 * When we click on the favorite button, the neighbour is added and removed in the favorite list
	 * The favorite list is checked each times
	 */
	@Test
	public void myDetailsNeighbourActivity_addRemoveFavoriteAction_shouldAddAndRemoveNeighbourFromFavoriteList() {
		// àWe go to the details activity to add neighbour to favorite
		onView(ViewMatchers.withId(R.id.list_neighbours))
				.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
		onView(ViewMatchers.withId(R.id.favoriteButton)).perform(click());
		// We go back to check the favorite list content
		onView(ViewMatchers.withId(R.id.backArrow)).perform(click());
		onView(withContentDescription("Favorites")).perform(click());
		onView(ViewMatchers.withId(R.id.list_favorites))
				.check(matches(hasMinimumChildCount(1)))
		// We go to the details again from the favorite list to remove neighbour from favorites
				.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
		onView(ViewMatchers.withId(R.id.favoriteButton)).perform(click());
		// We go back and check that there is no more neighbour in the favorite list
		onView(ViewMatchers.withId(R.id.backArrow)).perform(click());
		onView(withContentDescription("Favorites")).perform(click());
		onView(ViewMatchers.withId(R.id.list_favorites))
				.check(matches(hasMinimumChildCount(0)));
	}
}