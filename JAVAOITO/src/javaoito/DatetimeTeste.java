/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoito;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 * @author User
 */
public class DatetimeTeste {
    
    public static void main(String args[]) {
      testLocalDateTime();
   }
	
   public static void testLocalDateTime() {
      // Get the current date and time
      LocalDateTime currentTime = LocalDateTime.now();
      System.out.println("Current DateTime: " + currentTime);
      //      Current DateTime: 2014-12-09T11:00:45.457
      
      LocalDate date1 = currentTime.toLocalDate();
      System.out.println("date1: " + date1);
      //      date1: 2014-12-09
		
      Month month = currentTime.getMonth();
      int day = currentTime.getDayOfMonth();
      int seconds = currentTime.getSecond();
		
      System.out.println("Month: " + month +" day: " + day +" seconds: " + seconds);
      //      Month: DECEMBERday: 9seconds: 45
		
      LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
      System.out.println("date2: " + date2);
      //      date2: 2012-12-10T11:00:45.457
		
      //12 december 2014
      LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
      System.out.println("date3: " + date3);
      //      date3: 2014-12-12
		
      //22 hour 15 minutes
      LocalTime date4 = LocalTime.of(22, 15);
      System.out.println("date4: " + date4);
      //      date4: 22:15
		
      //parse a string
      LocalTime date5 = LocalTime.parse("20:15:30");
      System.out.println("date5: " + date5);
      //      date5: 20:15:30

      ZonedDateTime date6 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
      System.out.println("date1: " + date6);
      //date1: 2007-12-03T10:15:30+05:00[Asia/Karachi]
      
      ZoneId id = ZoneId.of("Europe/Paris");
      System.out.println("ZoneId: " + id);
      //ZoneId: Europe/Paris
      
      ZoneId currentZone = ZoneId.systemDefault();
      System.out.println("CurrentZone: " + currentZone);
      //CurrentZone: Etc/UTC
      
      LocalDate today = LocalDate.now();
      System.out.println("Current date: " + today);
      //Current date: 2014-12-10	
      //add 1 week to the current date
      LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
      System.out.println("Next week: " + nextWeek);
      //Next week: 2014-12-17 
		
      //add 1 month to the current date
      LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
      System.out.println("Next month: " + nextMonth);
      //Next month: 2015-01-10
		
      //add 1 year to the current date
      LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
      System.out.println("Next year: " + nextYear);
      //Next year: 2015-12-10
		
      //add 10 years to the current date
      LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
      System.out.println("Date after ten year: " + nextDecade);
      //Date after ten year: 2024-12-10
      
      LocalDate date7 = LocalDate.now();
      System.out.println("Current date: " + date7);
      //Current date: 2014-12-10
		
      //add 1 month to the current date
      LocalDate date8 = date7.plus(1, ChronoUnit.MONTHS);
      System.out.println("Next month: " + date8);
      //Next month: 2015-01-10
      
      Period period = Period.between(date8, date7);
      System.out.println("Period: " + period);
      //Period: P-1M
      
      LocalTime time1 = LocalTime.now();
      Duration twoHours = Duration.ofHours(2);
      
		
      LocalTime time2 = time1.plus(twoHours);
      Duration duration = Duration.between(time1, time2);
		
      System.out.println("Duration: " + duration);
      //Duration: PT2H
      
      LocalDate date9 = LocalDate.now();
      System.out.println("Current date: " + date9);
      //Current date: 2014-12-10
      
      //get the next tuesday
      LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
      System.out.println("Next Tuesday on : " + nextTuesday);
      //Next Tuesday on : 2014-12-16
      
      //get the second saturday of next month
      LocalDate firstInYear = LocalDate.of(date1.getYear(),date1.getMonth(), 1);
      LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(
         DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
      System.out.println("Second Saturday on : " + secondSaturday);
      //Second Saturday on : 2014-12-13
      
      //Get the current date
      Date currentDate = new Date();
      System.out.println("Current date: " + currentDate);
      //Current date: Wed Dec 10 05:44:06 UTC 2014	
      
      //Get the instant of current date in terms of milliseconds
      Instant now = currentDate.toInstant();
      ZoneId currentZone2 = ZoneId.systemDefault();
		
      LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone2);
      System.out.println("Local date: " + localDateTime);
      //Local date: 2014-12-10T05:44:06.635
		
      ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone2);
      System.out.println("Zoned date: " + zonedDateTime);
      //Zoned date: 2014-12-10T05:44:06.635Z[Etc/UTC]
   }
}
