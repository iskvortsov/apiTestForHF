package com.hellofresh.challenge;

import com.hellofresh.challenge.model.Booking;
import com.hellofresh.challenge.model.Bookings;
import com.hellofresh.challenge.utils.FileReader;
import com.hellofresh.challenge.utils.HttpUtils;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static com.hellofresh.challenge.utils.HttpUtils.createBooking;
import static com.hellofresh.challenge.utils.HttpUtils.getBookings;


public class MainTest {

    private Random rnd = new Random();
    private String templatePath = "test-data/create_booking_template.json";

    @Test
    public void getBookingsTest() throws IOException{

        int roomId = rnd.nextInt(10000);

//        Getting Booking from template file
        Booking bookingRequestOne = FileReader.getBookingFromTemplate(templatePath);

//        Changing dates and roomId
        bookingRequestOne.roomid = roomId;
        bookingRequestOne.bookingdates.checkin = LocalDate.now().toString();
        bookingRequestOne.bookingdates.checkout = LocalDate.now().plusDays(1).toString();
        createBooking(bookingRequestOne);

//        Creating second request and changing it's dates
        Booking bookingRequestTwo = bookingRequestOne;

        bookingRequestTwo.bookingdates.checkin = LocalDate.now().plusDays(2).toString();
        bookingRequestTwo.bookingdates.checkout = LocalDate.now().plusDays(3).toString();
        createBooking(bookingRequestTwo);

        Bookings bookings = getBookings(roomId);

        Assert.assertEquals("Number of bookings is wrong",2,bookings.bookings.size());
    }

    @Test
    public void checkGetBookingById() throws IOException {

//        Getting Booking from template file
        Booking bookingRequest = FileReader.getBookingFromTemplate(templatePath);

//        Change roomId value to random
        bookingRequest.roomid = rnd.nextInt(10000);

        int createdBookingId = createBooking(bookingRequest).bookingid;

        Booking bookingById = HttpUtils.getBookingById(createdBookingId);

//        Verify that all the fields in original bookingRequest are equal to bookingById fields
        Assert.assertEquals("Room Ids don't match", bookingRequest.roomid, bookingById.roomid);
        Assert.assertEquals("First names don't match", bookingRequest.firstname, bookingById.firstname);
        Assert.assertEquals("Last names don't match", bookingRequest.lastname, bookingById.lastname);
        Assert.assertEquals("DepositPaid flags don't match", bookingRequest.depositpaid, bookingById.depositpaid);
        Assert.assertEquals("CheckIn dates don't match", bookingRequest.bookingdates.checkin, bookingById.bookingdates.checkin);
        Assert.assertEquals("CheckOut dates don't match", bookingRequest.bookingdates.checkout, bookingById.bookingdates.checkout);
    }

    @Test
    public void checkCreateBooking() throws IOException {

//        Getting Booking from template file
        Booking bookingRequest = FileReader.getBookingFromTemplate(templatePath);

//        Changing some values
        bookingRequest.firstname = "Jason";
        bookingRequest.lastname = "Smith";
        bookingRequest.roomid = rnd.nextInt(10000);

        Booking createdBooking = createBooking(bookingRequest);

//        Verifying that all the fields in bookingRequest equal to createdBooking fields.
        Assert.assertEquals("Room Ids don't match", bookingRequest.roomid, createdBooking.roomid);
        Assert.assertEquals("First names don't match", bookingRequest.firstname, createdBooking.firstname);
        Assert.assertEquals("Last names don't match", bookingRequest.lastname, createdBooking.lastname);
        Assert.assertEquals("DepositPaid flags don't match", bookingRequest.depositpaid, createdBooking.depositpaid);
        Assert.assertEquals("CheckIn dates don't match", bookingRequest.bookingdates.checkin, createdBooking.bookingdates.checkin);
        Assert.assertEquals("CheckOut dates don't match", bookingRequest.bookingdates.checkout, createdBooking.bookingdates.checkout);
    }

}
