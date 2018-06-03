package com.learncamel.routes.jdbc.routebuilders;

import com.learncamel.routes.jdbc.processors.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class DBPostgresRoute extends RouteBuilder {
    public void configure() throws Exception {
        ;
        from("direct:dbInput")
                .onException(PSQLException.class).handled(true).log("Exception While inserting messages.").end()
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:PGDataSource")
                .to("sql:select * from country_capital?dataSource=PGDataSource")
                .to("log:?level=INFO&showBody=true");
    }
}
