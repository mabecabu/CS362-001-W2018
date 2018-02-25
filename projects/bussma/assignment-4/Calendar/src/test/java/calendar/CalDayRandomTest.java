package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	private static final long TestTimeout = 60*125; //60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	 @Test
	  public void randomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=ValuesGenerator.RandInt(random);;
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear=ValuesGenerator.RandInt(random);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 //Construct a new Appointment object with the initial data	 
				 Appt appt = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				 Appt appt1 = new Appt(startHour + ValuesGenerator.RandInt(random),
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				GregorianCalendar greg_cal = new GregorianCalendar();
				CalDay cal = new CalDay(greg_cal);
				for (int i = 0; i < NUM_TESTS; i++) {
					cal = new CalDay(greg_cal);
					cal.addAppt(appt);
					cal.addAppt(appt1);
					if(appt.getValid() && appt1.getValid()) {
						assertEquals(0, appt.compareTo(cal.getAppts().get(0)));
						assertEquals(0, appt1.compareTo(cal.getAppts().get(1)));
						assertEquals(2, cal.getSizeAppts());
					}
					else if(appt.getValid() && !appt1.getValid()) {
						assertEquals(0, appt.compareTo(cal.getAppts().get(0)));
						assertEquals(1, cal.getSizeAppts());
					}	
					else if(!appt.getValid() && appt1.getValid()) {
						assertEquals(0, appt1.compareTo(cal.getAppts().get(0)));
						assertEquals(1, cal.getSizeAppts());
					}	
					else {
						assertEquals(0, cal.getSizeAppts());
					}
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	
}
