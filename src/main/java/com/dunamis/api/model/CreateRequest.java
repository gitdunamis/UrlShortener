package com.dunamis.api.model;


import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreateRequest {
    @NotBlank(message = "url to shorten must be provided")
    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|" +
            "http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}" +
            "[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")
    private String longUrl;

    private String prefix;
}
