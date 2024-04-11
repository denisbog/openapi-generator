/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.5.0-SNAPSHOT).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@Component("validationErrorProcessor")
public class ValidationErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception fault = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        int httpStatusCode = 500;
        if (fault instanceof BeanValidationException) {
            httpStatusCode = 400;
        }
        if (fault instanceof UnrecognizedPropertyException) {
            httpStatusCode = 400;
        }
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, httpStatusCode);
        exchange.getIn().setBody(fault.getMessage());
    }
}