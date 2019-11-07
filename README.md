### API automation test challenge for HelloFresh ###

I've created the following tests:

  `1) _getBookings_: Test that at least 2 existing bookings are returned in the response.`
  `For this test I'm creating two booking for the same roomId beforehand.`

  `2) _getBooking_: Test that the data returned for an existing booking matches.`
  `For this test I'm creating booking beforehand.`

  `3) _createBooking_: Test that bookings can be created.`

I would create tests for _updateBooking_ and _deleteBooking_ endpoints but I couldn't get them to work using swagger web spec.
I'm getting 403 Forbidden for any correct request.

# Tools

I'm using JUnit 4.12, Logback Classic for logs, Apache HttpClient, Jackson json mapper and Joda Time for easier work with dates.

Data model classes:
* Booking
* BookingCreateResponse
* BookingDates
* Bookings

Utility classes:
* FileReader (for fetching Booking object from json template)
* HttpUtils (HttpClient request sender and response processor)

Test classes:
* MainTest

# How to run

`mvn clean test`

