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
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Deletes a neighbour
     * @param neighbour
     */

    void createNeighbour(Neighbour neighbour);

    /**
     * Deletes a favorite
     * @param neighbour
     */
    void deleteFromFavorite(Neighbour neighbour);

    /**
     * Create a favorite
     * @param neighbour
     */
    void addToFavorite(Neighbour neighbour);
}
