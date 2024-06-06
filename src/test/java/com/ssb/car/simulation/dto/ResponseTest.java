package com.ssb.car.simulation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ResponseTest {

    @InjectMocks
    Response response;

    @Test
    @DisplayName("Test to set response status code")
    void testSetStatusCode() {
        response.setStatusCode(HttpStatus.OK.value());
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @DisplayName("Test to set response data")
    void testSetData() {
        String result = "A, (6,8) S";
        response.setData(List.of(result));
        Assertions.assertNotNull(response.getData());
        Assertions.assertInstanceOf(List.class, response.getData());
    }

    @Test
    @DisplayName("Test to set response context path")
    void testSetContextPath() {
        response.setContextPath("/simulation/run-simulation");
        Assertions.assertEquals("/simulation/run-simulation", response.getContextPath());
    }

    @Test
    @DisplayName("Test to set response exception message")
    void testSetExceptionMessage() {
        response.setExceptionMessage("Data Not Found");
        Assertions.assertEquals("Data Not Found", response.getExceptionMessage());
    }

    @Test
    @DisplayName("Test to set response timestamp")
    void testSetTimeStamp() {
        Date todayDate = new Date();
        response.setTimeStamp(todayDate);
        Assertions.assertEquals(todayDate, response.getTimeStamp());
    }

    @Test
    @DisplayName("Test to set successful response")
    void testSetSuccessfulResponse() {
        String result = "A, (6,8) S";
        response = new Response(HttpStatus.OK.value(), List.of(result));
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(response.getData());
        Assertions.assertInstanceOf(List.class, response.getData());
    }

    @Test
    @DisplayName("Test to set response in case of exception")
    void testSetExceptionResponse() {
        Date todayDate = new Date();
        response = new Response(HttpStatus.NOT_FOUND.value(), "/simulation/run-simulation",
                "Data Not Found", todayDate);
        Assertions.assertEquals(404, response.getStatusCode());
        Assertions.assertEquals("Data Not Found", response.getExceptionMessage());
        Assertions.assertEquals("/simulation/run-simulation", response.getContextPath());
        Assertions.assertEquals(todayDate, response.getTimeStamp());
    }

}
