package ressourcesrest;

import entities.Option;
import jakarta.ws.rs.*;
import metiers.OptionBusiness;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//NO_CONTENT / NOT FOUND

@Path("options") // for the class to be accessible
public class Restoption {

    public static OptionBusiness  optB = new OptionBusiness();//declare static

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOptions(@QueryParam("domaine") String D){
        List<Option> l= new ArrayList<Option>();
        if(D==null)
        {
            l=optB.getListeOptions();
        }        else {
            l=optB.getOptionsByDomaine(D);
        }
        if (l.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(200).entity(l).build();
    }


    @DELETE
    @Path("{code}")
    public Response deleteOption(@PathParam("code") int id){
        if (optB.deleteOption(id)){
            return Response.status(204).build();
        }else {
            return Response.status(404).build();
        }
    }



/*
    @GET
    @Produces(MediaType.APPLICATION_JSON) // by default it's json
    public Response getAllOption() {
        List<Option> l = optB.getListeOptions();
        return Response.status(200).entity(l).build(); // entity for the body content // build to finish the communication
    }*/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option op)
    {
        if (optB.addOption(op)){
            return Response.status(200).build();
        }else {
            return Response.status(404).build();
        }
    }


    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("id") int id ,Option op){
        if (optB.updateOption(id,op)){
            return Response.status(200).build();
        }
        else {
            return Response.status(404).build();
        }
    }

}