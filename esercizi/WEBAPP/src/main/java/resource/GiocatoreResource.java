package resource;

import org.example.entity.Joueur;
import org.example.service.JoueurService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/Giocatore")
public class GiocatoreResource {

      JoueurService joueurService=new JoueurService();


    public static void main(String[] args) throws Exception {
       JoueurService joueurService=new JoueurService();
        joueurService.getGiocatore(2);
        List<Joueur> giocatori = joueurService.lista();
        for(Joueur j :giocatori){
            System.out.println(j.toString());
        }



  }
     @Context
      UriInfo uriInfo;//Un'interfaccia iniettabile che fornisce l'accesso all'applicazione
                      // e richiede informazioni sull'URI.
                      // Gli URI relativi sono relativi all'URI di base dell'applicazione

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGiocatori() {

        try {
            JoueurService  joueurService=new JoueurService();
            List<Joueur> giocatori = joueurService.lista();

            if (!giocatori.isEmpty()) {
                return Response.ok(giocatori).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No players found").build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception properly
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }


    @GET
    @Path("{id}") // Get a player selected by ID if it exists
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("id") long id) {
        // Assuming you have a method in your controller to find a player by ID

        try{
            JoueurService joueurService=new JoueurService();

            Joueur giocatore = joueurService.getGiocatore(id);

            if (giocatore != null) {
                return Response.ok(giocatore).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Giocatore not found").build();
            }
         }
        catch (Exception e) {
            e.printStackTrace(); // Log the exception for further investigation
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error").build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGiocatore(Joueur nuovoGiocatore) {
        try {
            JoueurService joueurService=new JoueurService();
            if (nuovoGiocatore == null || nuovoGiocatore.getNom() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("dati sbagliati").build();
            }
            Joueur createdGiocatore = joueurService.create(nuovoGiocatore);


            return Response.status(Response.Status.CREATED)
                    .entity(createdGiocatore)
                    .build();
        } catch (Exception e) {


            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error").build();
        }
    }


    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGiocatore(@PathParam("id") long id) {
        try {
            JoueurService joueurService = new JoueurService();

            boolean isDeleted = joueurService.delete(id);

            if (isDeleted) {
                return Response.status(Response.Status.NO_CONTENT).entity("Giocatore deleted").build(); // 204 No Content
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Giocatore not found").build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for further investigation
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error").build();
        }
    }



    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGiocatore(@PathParam("id") long id, Joueur updatedGiocatore) {
        try {
            JoueurService joueurService = new JoueurService();

            // Check if the updatedGiocatore object is valid
            if (updatedGiocatore == null || updatedGiocatore.getNom() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Dati sbagliati").build();
            }


            Joueur ifEsiste = joueurService.update(id,updatedGiocatore);

            if (ifEsiste != null) {
                return Response.ok(ifEsiste).build(); // 200 OK with updated player
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Giocatore non trovato").build(); // 404 Not Found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error").build();
        }
    }




}
