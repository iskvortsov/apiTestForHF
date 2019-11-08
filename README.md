# API automation test challenge for HelloFresh ###

I've created the following tests:

  1) getBookings: Test that at least 2 existing bookings are returned in the response.

  2) getBooking: Test that the data returned for an existing booking matches.

  3) createBooking: Test that bookings can be created.

I would create tests for _updateBooking_ and _deleteBooking_ endpoints but I couldn't get them to work using swagger web spec.
I'm getting 403 Forbidden for any correct request.

## Tools

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

## How to run

`mvn clean test`

