package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Favorites
     * @return
     */
    List<Neighbour> getFavorites();

    /**
     * Delete a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Creates a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Remove from favorite
     * @param neighbour
     */
    void removeFromFavorite(Neighbour neighbour);

    /**
     * Add to favorite
     * @param neighbour
     */
    void addToFavorite(Neighbour neighbour);
}
