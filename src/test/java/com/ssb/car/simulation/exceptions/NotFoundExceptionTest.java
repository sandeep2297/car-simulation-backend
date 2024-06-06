package com.ssb.car.simulation.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NotFoundExceptionTest {

    @InjectMocks
    NotFoundException notFoundException;

    @Test
    @DisplayName("Test to verify Not Found Exception")
    void testNotFoundException() {
        notFoundException = new NotFoundException("Location coordinates of A");
        Assertions.assertNotNull(notFoundException.getMessage());
        Assertions.assertEquals("Location coordinates of A Not Found", notFoundException.getMessage());
    }

}
