package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when user click a neighbour to show his details
 */
public class DetailsNeighbourEvent {

	/**
	 * Neighbour position in list (Neighbour or favorites)
	 */
	public boolean mIsFavorite;

	/**
	 * Neighbour to cast
	 */
	public Neighbour neighbour;

	/**
	 * Constructor.
	 * @param neighbour
	 */
	public DetailsNeighbourEvent(Neighbour neighbour, boolean isFavorite) {
		this.neighbour = neighbour;
		this.mIsFavorite = isFavorite;
	}

	public boolean getIfNeighbourIsFavorite() {
		return mIsFavorite;
	}
}
