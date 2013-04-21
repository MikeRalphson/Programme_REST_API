package com.bbc.service;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;
import com.bbc.repository.ProgrammeRepository;

import java.util.Collection;

public class SimpleProgrammeService implements ProgrammeService {

    private ProgrammeRepository repository;

    public SimpleProgrammeService(ProgrammeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Programme> retrieveProgrammes() {
        return repository.retrieveProgrammes();
    }

    @Override
    public Programme retrieveProgrammeById(Long programmeId) throws ProgrammeNotFoundException {
        return repository.retrieveProgrammeById(programmeId);
    }

    @Override
    public Collection<Programme> retrieveProgrammesByCategory(String category) {
        return repository.retrieveProgrammesByCategory(category);
    }

    @Override
    public Collection<Programme> retrieveProgrammeByAvailability(boolean available) {
        return repository.retrieveProgrammeByAvailability(available);
    }

    @Override
    public Long addProgramme(Programme programmeToAdd) throws ProgrammeAlreadyExists {
        return repository.addProgramme(programmeToAdd);
    }

    @Override
    public void updateProgramme(Programme programme) throws ProgrammeNotFoundException {
        repository.updateProgramme(programme);
    }

    @Override
    public void deleteProgramme(Long programmeToRemove) throws ProgrammeNotFoundException {
        repository.deleteProgramme(programmeToRemove);
    }


}
