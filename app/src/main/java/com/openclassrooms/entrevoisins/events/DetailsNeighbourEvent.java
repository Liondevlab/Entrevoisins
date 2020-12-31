package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when user click a neighbour to show his details
 */
public class DetailsNeighbourEvent {

	/**
	 * Neighbour to cast
	 */
	public Neighbour neighbour;

	/**
	 * Constructor.
	 * @param neighbour
	 */
	public DetailsNeighbourEvent(Neighbour neighbour) {
		this.neighbour = neighbour;
	}
}
