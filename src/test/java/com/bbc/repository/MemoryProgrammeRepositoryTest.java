package com.bbc.repository;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class MemoryProgrammeRepositoryTest {

    MemoryProgrammeRepository underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new MemoryProgrammeRepository();
        Store.getInstance().resetStore();
    }

    @Test
    public void shouldRetrieveAllProgrammes() throws Exception {
        // given

        // when
        Collection<Programme> programmes = underTest.retrieveProgrammes();

        // then
        assertThat(programmes).hasSize(3);
    }

    @Test
    public void shouldRetrieveProgrammeById() throws Exception {
        // given

        // when
        Programme programme = underTest.retrieveProgrammeById(1L);

        // then
        assertEquals((Object) 1L, programme.getId());
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenNotRetrievedProgrammeById() throws Exception {
        // given

        // when
        underTest.retrieveProgrammeById(-1L);

        // then
    }

    @Test
    public void shouldRetrieveProgrammesByCategory() throws Exception {
        // given

        // when
        Collection<Programme> programmes = underTest.retrieveProgrammesByCategory("news");

        // then
        assertThat(programmes).hasSize(1).onProperty("id").containsOnly(2L);
    }

    @Test
    public void shouldRetrievedProgrammesByAvailability() throws Exception {
        // given

        // when
        Collection<Programme> programmes = underTest.retrieveProgrammeByAvailability(true);

        // then
        assertThat(programmes).hasSize(2).onProperty("id").containsOnly(1L, 2L);
    }

    @Test
    public void shouldAddProgramme() throws Exception {
        // given
        Programme p1 = new Programme(100L, "Programme 100");

        // when
        Long newId = underTest.addProgramme(p1);

        // then
        assertEquals((Object) 100L, newId);
    }

    @Test(expected = ProgrammeAlreadyExists.class)
    public void shouldThrowExceptionWhenAddExistingProgramme() throws Exception {
        // given
        Programme p1 = new Programme(1L, "Program 1");

        // when
        underTest.addProgramme(p1);

        // then
    }

    @Test
    public void shouldUpdateProgramme() throws Exception {
        // given
        Programme p1 = new Programme(1L, "Programme 1");

        // when
        underTest.updateProgramme(p1);

        // then
        Programme updatedProgramme = underTest.retrieveProgrammeById(1L);
        assertEquals("Programme 1", updatedProgramme.getTitle());
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenUpdateUNonExistingProgramme() throws Exception {
        // given
        Programme p1 = new Programme(100L, "Programme 100");

        // when
        underTest.updateProgramme(p1);

        // then
    }

    @Test
    public void shouldDeleteProgramme() throws Exception {
        // given

        // when
        underTest.deleteProgramme(1L);

        // then
        try {
            underTest.retrieveProgrammeById(1L);
        } catch (ProgrammeNotFoundException ignore) {
            // verify deleted properly
        }
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenDeleteNonExistingProgramme() throws Exception {
        // given

        // when
        underTest.deleteProgramme(100L);

        // then
    }
}