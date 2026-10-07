package ressourcesrest;

import entities.Etudiant;
import entities.Option;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import java.util.ArrayList;
import java.util.List;

@Path("etudiants")
public class RestEtudiant {

    public static EtudiantBusiness etuB = new EtudiantBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etubIn) {
        if (etuB.addEtudiant(etubIn)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtuds() {
        List<Etudiant> liste = etuB.getAllEtudiants();
        return Response.status(200).entity(liste).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getEtudById(@PathParam("id") String id) {

        Etudiant e = etuB.getEtudiantByIdentifiant(id);

        if (e != null) {
            return Response.status(200).entity(e).build();
        } else {
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEtudiantById(@PathParam("id") String id) {

        if (etuB.deleteEtudiant(id)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{identif}")
    public Response ModifyEtu(
            @PathParam("identif") String identif,
            Etudiant etuIn) {

        if (etuB.updateEtudiant(identif, etuIn)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtuByOpt(@QueryParam("codeOption") int code) {

        OptionBusiness opB = new OptionBusiness();
        Option op = opB.getOptionByCode(code);

        if (op != null) {
            List<Etudiant> liste = etuB.getEtudiantsByOption(op);
            return Response.status(200).entity(liste).build();
        } else {
            return Response.status(404).build();
        }
    }
}