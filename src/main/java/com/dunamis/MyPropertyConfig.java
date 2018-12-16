package com.dunamis;


import com.dunamis.dao.IUrlDao;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "dunamis")
public class MyPropertyConfig {

    private IUrlDao urlDao;
}
