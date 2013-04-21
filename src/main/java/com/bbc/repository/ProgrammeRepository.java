package com.bbc.repository;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;

import java.util.Collection;

public interface ProgrammeRepository {

    Collection<Programme> retrieveProgrammes();

    Programme retrieveProgrammeById(Long programmeId) throws ProgrammeNotFoundException;

    Collection<Programme> retrieveProgrammesByCategory(String category);

    Collection<Programme> retrieveProgrammeByAvailability(boolean available);

    Long addProgramme(Programme programmeToAdd) throws ProgrammeAlreadyExists;

    void updateProgramme(Programme programmeToUpdate) throws ProgrammeNotFoundException;

    void deleteProgramme(Long programmeId) throws ProgrammeNotFoundException;
}
