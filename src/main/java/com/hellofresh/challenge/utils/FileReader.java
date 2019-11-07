package com.hellofresh.challenge.utils;

import com.hellofresh.challenge.model.Booking;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FileReader {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    public static Booking getBookingFromTemplate(String resourcePath)
            throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Reading resource " + resourcePath);

        // Getting resource by given path

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(resourcePath);

        return mapper.readValue(inputStream, Booking.class);

    }
}
