package com.dunamis.api.model;


import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreateRequest {
    /**
     * Url for which a shortened form is requested
     */
    @NotBlank(message = "url to shorten must be provided")
    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|" +
            "http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]" +
            "[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")
    private String longUrl;

    /**
     * optional string to add to the beginning of a short url.
     */
    private String prefix;

    /**
     * when the link will expire
     */
    private String expirationDay;

    /**
     * link will no longer be valid after  `maxNumHits`.
     * -1 for infinity
     */
    private int maxNumHits;
}
