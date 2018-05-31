package com.learncamel.routes.jdbc.test;

import com.learncamel.routes.jdbc.routebuilders.DBPostgresRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DBPostgresRouteTest extends CamelTestSupport {
    @Override
    protected CamelContext createCamelContext() throws Exception {
        String jdbcURL = "jdbc:postgresql://localhost:5432/testDB";
        DataSource dataSource = setupDataSource(jdbcURL);

        SimpleRegistry reg = new SimpleRegistry();
        reg.put("PGDataSource", dataSource);

        return new DefaultCamelContext(reg);

    }

    private DataSource setupDataSource(String jdbcURL) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.setUrl(jdbcURL);
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new DBPostgresRoute();

    }

    @Test
    public void insertData(){
        //String inputJSON = "{\"name\":\"United States of America\",\"topLevelDomain\":[\".us\"],\"alpha2Code\":\"US\",\"alpha3Code\":\"USA\",\"callingCodes\":[\"1\"],\"capital\":\"Washington, D.C.\",\"altSpellings\":[\"US\",\"USA\",\"United States of America\"],\"region\":\"Americas\",\"subregion\":\"Northern America\",\"population\":323947000,\"latlng\":[38.0,-97.0],\"demonym\":\"American\",\"area\":9629091.0,\"gini\":48.0,\"timezones\":[\"UTC-12:00\",\"UTC-11:00\",\"UTC-10:00\",\"UTC-09:00\",\"UTC-08:00\",\"UTC-07:00\",\"UTC-06:00\",\"UTC-05:00\",\"UTC-04:00\",\"UTC+10:00\",\"UTC+12:00\"],\"borders\":[\"CAN\",\"MEX\"],\"nativeName\":\"United States\",\"numericCode\":\"840\",\"currencies\":[{\"code\":\"USD\",\"name\":\"United States dollar\",\"symbol\":\"$\"}],\"languages\":[{\"iso639_1\":\"en\",\"iso639_2\":\"eng\",\"name\":\"English\",\"nativeName\":\"English\"}],\"translations\":{\"de\":\"Vereinigte Staaten von Amerika\",\"es\":\"Estados Unidos\",\"fr\":\"États-Unis\",\"ja\":\"アメリカ合衆国\",\"it\":\"Stati Uniti D'America\",\"br\":\"Estados Unidos\",\"pt\":\"Estados Unidos\"},\"flag\":\"https://restcountries.eu/data/usa.svg\",\"regionalBlocs\":[{\"acronym\":\"NAFTA\",\"name\":\"North American Free Trade Agreement\",\"otherAcronyms\":[],\"otherNames\":[\"Tratado de Libre Comercio de América del Norte\",\"Accord de Libre-échange Nord-Américain\"]}]}";
        String inputJSON = "{\"name\":\"Romania\",\"topLevelDomain\":[\".ro\"],\"alpha2Code\":\"RO\",\"alpha3Code\":\"ROU\",\"callingCodes\":[\"40\"],\"capital\":\"Bucharest\",\"altSpellings\":[\"ROMANIA\"],\"region\":\"Europe\",\"subregion\":\"Eastern Europe\",\"population\": 2012164,\"latlng\":[45.0,26.0],\"demonym\":\"Romanian\",\"area\":238397,\"gini\":32.0,\"timezones\":[\"UTC+02:00\",\"UTC+03:00\"],\"borders\":[\"UKR\",\"SER\",\"BG\", \"HUN\", \"MD\"],\"nativeName\":\"Romania\",\"numericCode\":\"840\",\"currencies\":[{\"code\":\"RON\",\"name\":\"Romanian Leu\",\"symbol\":\"LEU\"}],\"languages\":[{\"\":\"ro\",\"iso_8859-16\":\"rou\",\"name\":\"Romanian\",\"nativeName\":\"Romanian\"}],\"translations\":{\"de\":\"Rumänien\",\"es\":\"Rumania\",\"fr\":\"Roumanie\",\"ja\":\"ルーマニア\",\"it\":\"Romania\",\"br\":\"Romênia\",\"pt\":\"Romênia\"},\"flag\":\"http://restcountries.eu/data/rou.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[\"Unión Europea\",\"Union Européene\"]}]}";
        List responseList = template.requestBody("direct:dbInput", inputJSON, ArrayList.class);
        System.out.println("responseList : " + responseList.size());
        assertNotEquals(0, responseList.size());
    }
}
