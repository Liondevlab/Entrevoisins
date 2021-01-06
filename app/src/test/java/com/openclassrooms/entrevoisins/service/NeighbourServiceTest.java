package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * 3 tests for favorite get/add/remove actions
     */
    @Test
    public void getFavoriteNeighbourWithSuccess() {
        List<Neighbour> neighbours = service.getFavorites();
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        Neighbour neighbourToAddToFavorite = service.getNeighbours().get(0);
        service.addToFavorite(neighbourToAddToFavorite);
        assertTrue(service.getFavorites().contains(neighbourToAddToFavorite));
    }

    @Test
    public void removeFavoriteNeighbourWithSuccess() {
        Neighbour neighbourToRemoveFromFavorite = service.getNeighbours().get(0);
        service.addToFavorite(neighbourToRemoveFromFavorite);
        assertTrue(service.getFavorites().contains(neighbourToRemoveFromFavorite));
        service.removeFromFavorite(neighbourToRemoveFromFavorite);
        assertFalse(service.getFavorites().contains(neighbourToRemoveFromFavorite));
    }
}
