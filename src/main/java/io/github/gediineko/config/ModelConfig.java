package io.github.gediineko.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by ggolong on 10/4/16.
 */
@Configuration
@EntityScan("io.github.gediineko.model")
@EnableJpaAuditing
public class ModelConfig {
}
