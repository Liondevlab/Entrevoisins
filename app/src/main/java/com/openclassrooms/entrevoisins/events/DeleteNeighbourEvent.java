package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Neighbour position (Neighbour or favorites)
     */
    public boolean mIsFavorite;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour, boolean isFavorite) {
        this.neighbour = neighbour;
        this.mIsFavorite = isFavorite;
    }

    public boolean getIfNeighbourIsFavorite() {
        return mIsFavorite;
    }
}
