package kuit.springbasic.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Slf4j
@Configuration
public class WebConfig {

    @Bean
    public MappingJackson2JsonView jsonView() {
        log.info("MappingJackson2JsonView");
        return new MappingJackson2JsonView();
    }

}
