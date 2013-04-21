package com.bbc.service;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;

import java.util.Collection;

public interface ProgrammeService {
    /**
     * @return all Programmes
     */
    Collection<Programme> retrieveProgrammes();

    /**
     * @param programmeId programmeId
     * @return programmed by id
     * @throws ProgrammeNotFoundException when programme with id does not exist
     */
    Programme retrieveProgrammeById(Long programmeId) throws ProgrammeNotFoundException;

    /**
     * @param category non case sensitive param
     * @return programmes with specified category
     */
    Collection<Programme> retrieveProgrammesByCategory(String category);

    /**
     * @param available available
     * @return programmes with specified availability
     */
    Collection<Programme> retrieveProgrammeByAvailability(boolean available);

    /**
     * @param programmeToAdd programmeToAdd
     * @return id added programme
     * @throws com.bbc.exceptions.ProgrammeAlreadyExists when programmes already exist
     */
    Long addProgramme(Programme programmeToAdd) throws ProgrammeAlreadyExists;

    /**
     * @param programme programme to update
     * @throws ProgrammeNotFoundException when programmes not exist
     */
    void updateProgramme(Programme programme) throws ProgrammeNotFoundException;

    /**
     * @param programmeToRemove programme to remove
     * @throws ProgrammeNotFoundException when programmes not exist
     */
    void deleteProgramme(Long programmeToRemove) throws ProgrammeNotFoundException;
}
