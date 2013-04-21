package com.bbc.repository;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryProgrammeRepository implements ProgrammeRepository {

    private Store store = Store.getInstance();

    @Override
    public Collection<Programme> retrieveProgrammes() {
        return store.getRepository().values();
    }

    @Override
    public Programme retrieveProgrammeById(Long programmeId) throws ProgrammeNotFoundException {
        Programme programme = store.getRepository().get(programmeId);
        if (programme == null) {
            throw new ProgrammeNotFoundException();
        }
        return programme;
    }

    @Override
    public Collection<Programme> retrieveProgrammesByCategory(String category) {
        Collection<Programme> result = new ArrayList<Programme>();

        for (Programme programme : store.getRepository().values()) {
            if (programme.getCategory().equalsIgnoreCase(category)) {
                result.add(programme);
            }
        }

        return result;
    }

    @Override
    public Collection<Programme> retrieveProgrammeByAvailability(boolean available) {
        Collection<Programme> result = new ArrayList<Programme>();

        for (Programme programme : store.getRepository().values()) {
            if (available == programme.isAvailable()) {
                result.add(programme);
            }
        }

        return result;
    }

    @Override
    public Long addProgramme(Programme programmeToAdd) throws ProgrammeAlreadyExists {
        Long id = programmeToAdd.getId();
        if (store.getRepository().containsKey(id)) {
            throw new ProgrammeAlreadyExists();
        }

        store.getRepository().put(id, programmeToAdd);
        return id;
    }

    @Override
    public void updateProgramme(Programme programmeToUpdate) throws ProgrammeNotFoundException {
        if (store.getRepository().containsKey(programmeToUpdate.getId())) {
            store.getRepository().put(programmeToUpdate.getId(), programmeToUpdate);
        } else {
            throw new ProgrammeNotFoundException();
        }
    }

    @Override
    public void deleteProgramme(Long programmeId) throws ProgrammeNotFoundException {
        if (store.getRepository().containsKey(programmeId)) {
            store.getRepository().remove(programmeId);
        } else {
            throw new ProgrammeNotFoundException();
        }
    }
}
