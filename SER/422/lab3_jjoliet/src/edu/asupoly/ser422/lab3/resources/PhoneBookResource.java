package edu.asupoly.ser422.lab3.resources;

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

import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.model.PhoneEntry;
import edu.asupoly.ser422.lab3.services.PhoneBookService;
import edu.asupoly.ser422.lab3.services.PhoneBookServiceFactory;

@Path("rest/pb/{phonebook}")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PhoneBookResource {
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
     * @api {get} rest/pb/:phonebook Get list of entrys in a given phonebook
     * @apiName getPhoneBook
     * @apiGroup PhoneBook
     * @apiParam {String} phonebook Id of the phonebook being retrieved. Use "ul" for unlisted phone entries.
     * 
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone": "5551234" "id": "-1"},
     *   {"firstName":"John","lastName":"Worsley", "phone": "5525222" "id": "5"}
     *  ]
     * 
     * */
	
	@GET	
	public List<PhoneEntry> getBook(@PathParam("phonebook") String id){	
		return __pService.getBook(id);	
	}
	
	/**
     * @api {get} rest/pb/:phonebook/get?first=:first&last=:last Get list of entrys in a given phonebook that contain the given query string for first and last.
     * @apiName getPartial
     * @apiGroup PhoneBook
     * @apiParam {String} phonebook Id of the phonebook being retrieved. Use "ul" for unlisted phone entries.
     * @apiParam {String} first The first name being queried (can be empty-> ex: ?first=&last=ley
     * @apiParam {String} last The last name being queried (can be empty - > ex: ?first=joh
     * 
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Johnson","lastName":"Denhamley", "phone": "5551234" "id": "-1"},
     *   {"firstName":"John","lastName":"Worsley", "phone": "5525222" "id": "5"}
     *  ]
     * 
     * */
	@GET
	@Path("/get")
    public List<PhoneEntry> getPartial(@PathParam("phonebook") String id, @QueryParam("first") String first, @QueryParam("last") String last) {
		return __pService.getPartial(id, first, last);
    }
}
