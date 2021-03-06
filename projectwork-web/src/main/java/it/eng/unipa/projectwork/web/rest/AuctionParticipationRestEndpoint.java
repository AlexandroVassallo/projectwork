package it.eng.unipa.projectwork.web.rest;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.modelmapper.ModelMapper;

import javax.ws.rs.core.SecurityContext;

import it.eng.unipa.projectwork.channel.MultiChannelContainer;
import it.eng.unipa.projectwork.model.Auction;
import it.eng.unipa.projectwork.model.Bid;
import it.eng.unipa.projectwork.model.Image;
import it.eng.unipa.projectwork.service.AuctionService;
import it.eng.unipa.projectwork.web.converter.ConverterUtils;
import it.eng.unipa.projectwork.web.dto.AuctionDTO;
import it.eng.unipa.projectwork.web.dto.AuctionFullDTO;
import it.eng.unipa.projectwork.web.dto.BidDTO;
import it.eng.unipa.projectwork.web.dto.ChannelDTO;
import it.eng.unipa.projectwork.web.dto.ResponseObject;

/*import it.eng.book.auction.event.ClosedAuctionEvent;
import it.eng.book.auction.event.OfferAuctionEvent;
import it.eng.book.auction.event.TimerAuctionEvent;
import it.eng.book.service.AuctionEventManager;*/

@Path("/auction")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AuctionParticipationRestEndpoint {
	
	@EJB
	AuctionService auctionSevice;
	
	@EJB
	MultiChannelContainer multiChannelContainer;
	
	@GET
    @Path("/list")
	@RolesAllowed(value="USER")
    public List<AuctionDTO> list(){
		return auctionSevice.loadActiveAuctions((a)->ConverterUtils.convert(a,AuctionDTO.class));
	}
	
	
	@GET
    @Path("/get/{oid}")
	@RolesAllowed(value="USER")
    public AuctionFullDTO get(@PathParam("oid") long oid){
		return auctionSevice.loadAuction(oid,(a)->ConverterUtils.convert(a, AuctionFullDTO.class,new ConverterUtils.ImageConverter()));
	}
	
	@GET
    @Path("/getImage/{oidAuction}/{oidImage}")
	@RolesAllowed(value="USER")
    public Response getImage(@PathParam("oidAuction") long oidAuction, @PathParam("oidImage") long oidImage){
		Image image = auctionSevice.loadImage(oidAuction,oidImage);
		if (image==null) {
			throw new WebApplicationException(404);
		}
		ResponseBuilder rb = Response.ok(new ByteArrayInputStream(image.getBody()), image.getContentType());
		return rb.build();
	}

	
	@POST
	@Path("/addBid")
	@RolesAllowed(value="USER")
	public AuctionFullDTO add(@Context SecurityContext sc, BidDTO bid){
		String username  = sc.getUserPrincipal().getName();
		auctionSevice.addBid(bid.getAuctionOid(), bid.getAuctionVersion(), username, bid.getPrice());
		return get(bid.getAuctionOid());
	}
	
	
	/***
	 * IN GET COME BACKDOOR PER FAR VEDERE L'ATTACCO CSRF 
	 * 
	 */
	@POST
	@Path("/registerChannel")
	@RolesAllowed(value="USER")
	public ResponseObject<Boolean> registerChannel(@Context SecurityContext sc, ChannelDTO channelDTO){
		String username = sc.getUserPrincipal().getName();
		Long auctionOid = channelDTO.getAuctionOid();
		String type = channelDTO.getType();
		multiChannelContainer.add(type, username, auctionOid);
		return new ResponseObject<Boolean>(true);
	}
	
	@POST
	@Path("/deregisterChannel")
	@RolesAllowed(value="USER")
	public ResponseObject<Boolean> deregisterChannel(@Context SecurityContext sc, ChannelDTO channelDTO){
		String username = sc.getUserPrincipal().getName();
		Long auctionOid = channelDTO.getAuctionOid();
		String type = channelDTO.getType();
		multiChannelContainer.remove(type, username, auctionOid);
		return new ResponseObject<Boolean>(true);
	}
	
	
	@GET
	@Path("/activeChannels/{oidAuction}")
	@RolesAllowed(value="USER")
	public List<ChannelDTO> activeChannels(@Context SecurityContext sc, @PathParam("oidAuction") long oidAuction){
		String username = sc.getUserPrincipal().getName();
		return multiChannelContainer.getTypes(username,oidAuction).stream().map((s)->{return new ChannelDTO(s);}).collect(Collectors.toList());
	}
	
	
	//@Inject
	//AuctionEventManager messageManager;
	/*
   	@GET
    @Path("/offer")
    @Produces({ "application/json" })
    public String offer(@QueryParam("auctionId")String auctionId,@QueryParam("importo") BigDecimal importo) {
   		messageManager.sendAuctionEvent(new OfferAuctionEvent(auctionId, importo));
        return "{\"result\":\"OK\"}";
    }
   	
   	
   	@GET
    @Path("/ttl")
    @Produces({ "application/json" })
    public String ttl(@QueryParam("auctionId")String auctionId,@QueryParam("ttl") long ttl) {
   		messageManager.sendAuctionEvent(new TimerAuctionEvent(auctionId, ttl));
        return "{\"result\":\"OK\"}";
    }
   	
   	@GET
    @Path("/close")
    @Produces({ "application/json" })
    public String close(@QueryParam("auctionId")String auctionId) {
   		messageManager.sendAuctionEvent(new ClosedAuctionEvent(auctionId));
        return "{\"result\":\"OK\"}";
    }
    */
	

	private static class IdentityFunction<T> implements Function<T,T>{
		
		@Override
		public T apply(T t) {
			return t;
		}
		
	}
	
	
}
