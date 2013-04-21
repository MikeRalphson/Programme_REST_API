package com.bbc.service;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;
import com.bbc.repository.ProgrammeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleProgrammeServiceTest {

    @Mock
    ProgrammeRepository repository;
    @InjectMocks
    SimpleProgrammeService underTest;

    @Test
    public void shouldRetrieveAllProgrammes() throws Exception {
        // given
        Programme p1 = new Programme();
        Programme p2 = new Programme();
        when(repository.retrieveProgrammes()).thenReturn(Arrays.asList(p1, p2));

        // when
        Collection<Programme> programmes = underTest.retrieveProgrammes();

        // then
        assertThat(programmes).hasSize(2).containsOnly(p1, p2);
    }

    @Test
    public void shouldRetrieveProgrammeById() throws Exception {
        // given
        Programme p1 = new Programme(1L, "Programme 1");
        when(repository.retrieveProgrammeById(1L)).thenReturn(p1);

        // when
        Programme programme = underTest.retrieveProgrammeById(1L);

        // then
        assertEquals(p1, programme);
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenNotRetrievedProgrammeById() throws Exception {
        // given
        when(repository.retrieveProgrammeById(1L)).thenThrow(ProgrammeNotFoundException.class);

        // when
        underTest.retrieveProgrammeById(1L);

        // then
    }

    @Test
    public void shouldAddProgramme() throws Exception {
        // given
        Programme p1 = new Programme(1L, "Programme 1");
        when(repository.addProgramme(p1)).thenReturn(1L);

        // when
        Long newId = underTest.addProgramme(p1);

        // then
        assertEquals((Object) 1L, newId);
        verify(repository).addProgramme(eq(p1));
    }

    @Test(expected = ProgrammeAlreadyExists.class)
    public void shouldThrowExceptionWhenAddExistingProgramme() throws Exception {
        // given
        Programme p1 = new Programme();
        when(repository.addProgramme(any(Programme.class))).thenThrow(ProgrammeAlreadyExists.class);

        // when
        underTest.addProgramme(p1);

        // then
    }

    @Test
    public void shouldUpdateProgramme() throws Exception {
        // given
        Programme p1 = new Programme(2L, "Programme 2");

        // when
        underTest.updateProgramme(p1);

        // then
        verify(repository).updateProgramme(eq(p1));
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenUpdateUNonExistingProgramme() throws Exception {
        // given
        Programme p1 = new Programme();
        doThrow(new ProgrammeNotFoundException()).when(repository).updateProgramme(eq(p1));

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
        verify(repository).deleteProgramme(eq(1L));
    }

    @Test(expected = ProgrammeNotFoundException.class)
    public void shouldThrowExceptionWhenDeleteNonExistingProgramme() throws Exception {
        // given
        doThrow(new ProgrammeNotFoundException()).when(repository).deleteProgramme(anyLong());

        // when
        underTest.deleteProgramme(1L);

        // then
    }
}