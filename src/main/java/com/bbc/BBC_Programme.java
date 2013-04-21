package com.bbc;

import com.bbc.api.Programme;
import com.bbc.exceptions.ProgrammeAlreadyExists;
import com.bbc.exceptions.ProgrammeNotFoundException;
import com.bbc.repository.MemoryProgrammeRepository;
import com.bbc.rs.NotFoundException;
import com.bbc.service.SimpleProgrammeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/programme")
public class BBC_Programme {

    private SimpleProgrammeService programmeService = new SimpleProgrammeService(new MemoryProgrammeRepository());

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public com.bbc.api.Programme getProgramme(@PathParam("id") Long id) {
        try {
            return programmeService.retrieveProgrammeById(id);
        } catch (ProgrammeNotFoundException ignore) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void addProgramme(Programme programme) {
        try {
            programmeService.addProgramme(programme);
        } catch (ProgrammeAlreadyExists ignore) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void updateProgramme(Programme programme) {
        try {
            programmeService.updateProgramme(programme);
        } catch (ProgrammeNotFoundException ignore) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteProgramme(@PathParam("id") Long id) {
        try {
            programmeService.deleteProgramme(id);
        } catch (ProgrammeNotFoundException ignore) {
            throw new NotFoundException();
        }
    }
}