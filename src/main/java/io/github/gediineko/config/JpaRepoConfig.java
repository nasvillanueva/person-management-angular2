package io.github.gediineko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ggolong on 10/4/16.
 */
@Configuration
@ComponentScan("io.github.gediineko.repo")
@EnableJpaRepositories("io.github.gediineko.repo")
@EnableTransactionManagement
public class JpaRepoConfig {
}