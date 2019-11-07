package com.hellofresh.challenge.utils;

import com.hellofresh.challenge.model.Booking;
import com.hellofresh.challenge.model.BookingCreateResponse;
import com.hellofresh.challenge.model.Bookings;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpUtils {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static String hostName = "automationintesting.online";

    public static Booking createBooking(Booking requestBooking) throws IOException {
        HttpHost target = new HttpHost(hostName, 443, "https");

        HttpPost request = new HttpPost("/booking/");
        request.addHeader("accept", "*/*");
        request.addHeader("Content-Type", "application/json");

        String requestBody = mapper.writeValueAsString(requestBooking);
        request.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

        logger.info("Executing request " + request.getRequestLine() + " to " + target);

        CloseableHttpResponse response = httpClient.execute(target, request);
        String body = EntityUtils.toString(response.getEntity());
        logger.info(response.getStatusLine() + body);
        BookingCreateResponse responseBooking = mapper.readValue(body, BookingCreateResponse.class);
        Booking createdBooking = responseBooking.booking;
        response.close();
        logger.info("Created booking with id " + createdBooking.bookingid);
        return createdBooking;
    }

    public static Booking getBookingById(int bookingId) throws IOException{
        HttpHost target = new HttpHost(hostName, 443, "https");
        HttpGet request = new HttpGet("/booking/" + bookingId);
        request.addHeader("accept", "*/*");

        logger.info("Executing request " + request.getRequestLine() + " to " + target);

        CloseableHttpResponse response = httpClient.execute(target, request);
        String body = EntityUtils.toString(response.getEntity());
        Booking booking = mapper.readValue(body, Booking.class);
        response.close();
        logger.info("Found booking with id " + bookingId);
        return booking;
    }

    public static Bookings getBookings(int roomId) throws IOException{
        HttpHost target = new HttpHost(hostName, 443, "https");
        HttpGet request = new HttpGet("/booking/?roomid=" + roomId);
        request.addHeader("accept", "*/*");

        logger.info("Executing request " + request.getRequestLine() + " to " + target);

        CloseableHttpResponse response = httpClient.execute(target, request);
        String body = EntityUtils.toString(response.getEntity());
        Bookings bookings = mapper.readValue(body, Bookings.class);
        response.close();
        logger.info("Found bookings count " + bookings.bookings.size());
        return bookings;
    }
}
