package edu.asupoly.ser422.lab3.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asupoly.ser422.lab3.model.PhoneEntry;
import edu.asupoly.ser422.lab3.services.PhoneBookService;
import edu.asupoly.ser422.lab3.services.PhoneBookServiceFactory;


@Path("/entry")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class PhoneEntryResource {
	private static PhoneBookService __pService = PhoneBookServiceFactory.getInstance();
	
	@Context
	private UriInfo _uriInfo;
	
	/**
     * @apiDefine BadRequestError
     * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
     * */
    /** @apiDefine ActivityNotFoundError
     * @apiError (Error 4xx) {404} NotFound Activity cannot be found
     * */
    /**
     * @apiDefine InternalServerError
     * @apiError (Error 5xx) {500} InternalServerError Something went wrong at server, Please contact the administrator!
     * */
    /**
     * @apiDefine NotImplementedError
     * @apiError (Error 5xx) {501} NotImplemented The resource has not been implemented. Please keep patience, our developers are working hard on it!!
     * */
	
    /**
     * @api {get} rest/entry Get list of PhoneEntrys
     * @apiName getEntries
     * @apiGroup PhoneEntry
     *
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"},
     *   {"firstName":"John","lastName":"Worsley", "phone":"5554321"}
     *  ]
     * 
     * */
	@GET
	public List<PhoneEntry> getEntries() {
		return __pService.getEntries();
	}
	
	 /**
     * @api {get} rest/entry/:phoneNumber Get a specific Phone Entry
     * @apiName getEntry
     * @apiGroup PhoneEntry
     * @apiParam {String} phoneNumber Phone number attached to an entry.
     * 
     * @apiUse ActivityNotFoundError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"}
     *  ]
     * 
     * */
	@GET
	@Path("/{phoneNumber}")
	public Response getEntry(@PathParam("phoneNumber") String number){
		
		PhoneEntry entry =  __pService.getEntry(number);
		if(entry != null){
		try {
			String aString = new ObjectMapper().writeValueAsString(entry);
			return Response.status(Response.Status.OK).entity(aString).build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		}else{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	//Requires format of "fname lastname phonenumber Optional: Id"
	@POST
	@Consumes("text/plain")
	 public Response createEntry(String name) {
		int rval = -1;
		String[] names = name.split(" ");
		if(names.length == 3){
			System.out.println("Not in the id spec");
			rval = __pService.createEntry(names[0], names[1], names[2]);
		}else if(names.length == 4){
			System.out.println("hit In the id spec");
			rval = __pService.createEntry(names[0], names[1], names[2], names[3]);
		} else {
			return Response.status(400)
					.header("Location", String.format("%s/%s",_uriInfo.getAbsolutePath().toString()))
					.entity("{ \"Entry\" : \"" + "Not Properly formatted Input" + "\"}").build();
		}
		if(rval >= 0){
			return Response.status(201)
					.header("Location", String.format("%s/%s",_uriInfo.getAbsolutePath().toString(), names[2]))
					.entity("{ \"Entry\" : \"" + names[2] + "\"}").build();
		}else{
			return Response.status(Response.Status.CONFLICT).build();
		}
    }
	
	/**
     * @api {Post} rest/entry Create A new Entry
     * @apiName createEntry
     * @apiGroup PhoneEntry
     * 
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * PAYLOAD: application/json ex: {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"}
     * 	HTTP/1.1 201 Created
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"}
     *  ]
     * 
     * */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response createEntry(PhoneEntry e) {
		int rval = -1;
		rval = __pService.createEntry(e);
			
		if(rval >= 0){
			try {
				String aString = new ObjectMapper().writeValueAsString(e);
				return Response.status(201).entity(aString).build();
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}else if (rval == -1){
			return Response.status(Response.Status.CONFLICT).build();
		} else{
			return Response.status(400)
					.header("Location", String.format("%s/%s",_uriInfo.getAbsolutePath().toString()))
					.entity("{ \"Entry\" : \"" + "Not Properly formatted Input" + "\"}").build();
		}
    }
	
	/**
     * @api {PUT} rest/entry Update an Entry
     * @apiName updateEntry
     * @apiGroup PhoneEntry
     * 
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiSuccessExample Success-Response:
     * PAYLOAD: application/json ex: {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"}
     * 	HTTP/1.1 201 Created
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone": "5551234"}
     *  ]
     * 
     * */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntry(PhoneEntry e) {
		System.out.println(e.getFirstname());
		if (__pService.updateEntry(e)) {
			try{
			String aString = new ObjectMapper().writeValueAsString(e);
			return Response.status(201).entity(aString).build();
			}catch(Exception err){
				err.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response.status(404, "{ \"message \" : \"No such Author " + e.getId() + "\"}").build();
		}
    }
	
	//Requires format of "fname lastname phonenumber Optional: Id"
	@PUT
	@Consumes("text/plain")
    public Response updateEntry(String in) {
		try {
			String[] input = in.split(" ");
			if(input.length == 3){
				if (__pService.updateEntry(new PhoneEntry(input[0], input[1], input[2]))) {
					// In the response payload it would still use Jackson's default serializer,
					// so we directly invoke our serializer so the PUT payload reflects what it should.
				} else {
					return Response.status(404, "{ \"message \" : \"No such PhoneEntry " + "\"}").build();
				}
			} else if(input.length == 4){
				if (__pService.updateEntry(new PhoneEntry(input[0], input[1], input[2], input[3]))) {
					// In the response payload it would still use Jackson's default serializer,
					// so we directly invoke our serializer so the PUT payload reflects what it should.
				} else {
					return Response.status(404, "{ \"message \" : \"No such PhoneEntry " + "\"}").build();
				}
			} else {
				return Response.status(400)
				.header("Location", String.format("%s/%s",_uriInfo.getAbsolutePath().toString()))
				.entity("{ \"Entry\" : \"" + "Not Properly formatted Input" + "\"}").build();
			}
			return Response.status(201)
					.header("Location", String.format("%s/%s",_uriInfo.getAbsolutePath().toString(), input[2]))
					.entity("{ \"Entry\" : \"" + input[0]+ " " + input[1]+ " " + input[2] + "\"}").build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return Response.status(500, "{ \"message \" : \"Internal server error deserializing Phone Entry\"}").build();
		}
		
    }
	
	/**
     * @api {Delete} rest/entry?num=:num Delete an Entry
     * @apiName DeleteEntry
     * @apiGroup PhoneEntry
     * @apiParam {String} num Phone number attached to an entry.
     * 
     * @apiUse ActivityNotFoundError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * PAYLOAD: Text/plain
     * String format-> Ariel Denheam 5551234 (id optional here)
     * 	HTTP/1.1 204 No Content
     * 	[]
     * 
     * */
	@DELETE
    public Response deleteAuthor(@QueryParam("num") String num) {
		if (__pService.deleteEntry(num)) {
			return Response.status(204).build();
		} else {
			return Response.status(404, "{ \"message \" : \"No such Phone Entry " + num + "\"}").build();
		}
    }
	
	
}
