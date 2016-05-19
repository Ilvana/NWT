package kino.configuration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseMigrate implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMigrate.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Flyway flyway = new Flyway();

        flyway.setDataSource("jdbc:mysql://localhost:3306/kino", "kino", "kino");
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("/db/");

        flyway.migrate();

        LOGGER.info("Database migration finished successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Nothing to do here...
    }
}
