package com.study.apache.camel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



class CopyFileCamelSimple {

    private static final Logger LOG = LoggerFactory.getLogger(CopyFileCamelSimple.class);

    public static void main(String[] args) {


        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("file:data/input?noop=true")
                            .log(LoggingLevel.INFO, "Body: ${body}  Header: ${headers}")
                            .to("file:data/output");
                }
            });

            camelContext.start();
            Thread.sleep(5000);

            camelContext.stop();
        } catch (Exception e) {
            LOG.error("Error during camel transport {}", e);
        }
    }

}
