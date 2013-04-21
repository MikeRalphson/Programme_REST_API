package com.bbc;

import com.bbc.api.Programme;
import com.bbc.repository.MemoryProgrammeRepository;
import com.bbc.service.SimpleProgrammeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/programmes")
public class BBC_Programmes {

    private SimpleProgrammeService programmeService = new SimpleProgrammeService(new MemoryProgrammeRepository());

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Programme> getProgrammes() {
        return programmeService.retrieveProgrammes();
    }

    @GET
    @Path("/category/{category}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Programme> getProgrammeByCategory(@PathParam("category") String category) {
        return programmeService.retrieveProgrammesByCategory(category);
    }

    @GET
    @Path("/available/{availability}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Programme> getProgrammeByAvailability(@PathParam("availability") boolean availability) {
        return programmeService.retrieveProgrammeByAvailability(availability);
    }
}