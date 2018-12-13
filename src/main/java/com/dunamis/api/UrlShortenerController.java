package com.dunamis.api;


import com.dunamis.api.model.CreateRequest;
import com.dunamis.api.model.CreateResponse;
import com.dunamis.service.IShortUrlService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {"application/json", "application/xml"})
public class UrlShortenerController {

    @Autowired
    IShortUrlService urlService;


    @PostMapping(consumes = "application/json")
    public CreateResponse createShortUrl(@RequestBody @Validated CreateRequest req) {
        CreateResponse createResponse;
        //TODO
        var shortUrl = urlService.genShortUrl(req.getLongUrl(), req.getPrefix());

        createResponse = new CreateResponse();
        createResponse.setStatusCode("201");
        createResponse.setMessage("ShortUrl Successfully Created");
        createResponse.setShortUrl(shortUrl);

        return createResponse;
    }


    @GetMapping("/{shortUrlkey}")
    public ResponseEntity getLongUrl(@PathVariable String shortUrlkey) {
        //TODO return a redirect 302 response, set the redirect url
        //TODO return longUrl ...
        String longUrl = urlService.getLongUrlFromShortUrl(shortUrlkey);
        var responseEntity = ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, longUrl).build();

        return responseEntity;

    }





    @GetMapping("/short/{id}")
    public Response getShortUrl(@PathVariable int id) {
        //TODO
        return new Response();
    }

    @DeleteMapping("/{id}")
    public Response deleteShortUrl(@PathVariable int id) {
        //TODO
        return new Response();
    }


}
