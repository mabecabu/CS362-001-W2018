package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.LinkedList;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	private static final long TestTimeout = 60*125; //60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"deleteAppt", "getApptRange"};// The list of the of methods to be tested in the TimeTable class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
			    
		return methodArray[n] ; // return the method name 
	}
		 
	@Test
	public void randomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 0 , 23);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 0, 59);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 27);
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
				 startHour=ValuesGenerator.getRandomIntBetween(random, 0 , 22);
				 Appt appt1 = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				 Appt appt2 = new Appt(startHour + 1,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				 Appt apptI = new Appt(ValuesGenerator.getRandomIntBetween(random, 24, 100),
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				 Appt apptN = new Appt(ValuesGenerator.getRandomIntBetween(random, 0, 23),
				          ValuesGenerator.getRandomIntBetween(random, 0, 59),
				          ValuesGenerator.getRandomIntBetween(random, 0, 27),
				          startMonth ,
				          startYear ,
				          title,
				         description);
				TimeTable tt = new TimeTable();
				GregorianCalendar first = new GregorianCalendar(startYear,startMonth,startDay-1);
				GregorianCalendar mid = new GregorianCalendar(startYear,startMonth,startDay);
				GregorianCalendar last = new GregorianCalendar(startYear,startMonth,startDay + 1);
				boolean coin = ValuesGenerator.getBoolean((float)0.1, random);
				CalDay cal = new CalDay(mid);
				cal.addAppt(appt);
				cal.addAppt(appt1);
				cal.addAppt(appt2);
				cal.addAppt(apptI);
				for (int i = 0; i < NUM_TESTS; i++) {	
					LinkedList<Appt> apptList = new LinkedList<Appt>();
					apptList.add(appt);
					apptList.add(appt1);
					apptList.add(appt2);
					String methodName = TimeTableRandomTest.RandomSelectMethod(random);
					if (methodName.equals("deleteAppt")){
						int choice = ValuesGenerator.getRandomIntBetween(random, 0, 2);
						if(choice == 0) {
							choice = ValuesGenerator.getRandomIntBetween(random, 0, 2);
							if(choice == 0)
								appt = null;
							else if(choice == 1)
								apptList = null;
							else {
								appt = null;
								apptList = null;
							}
							assertEquals(null, tt.deleteAppt(apptList, appt));	
							assertEquals(null, tt.deleteAppt(apptList, apptI));
						} 
						else if(choice == 1) {
							if(apptList.getFirst() == null)
								continue;
							assertEquals(null, tt.deleteAppt(apptList, apptN));
						}
						else {	
							apptList = tt.deleteAppt(apptList, appt);
							if(apptList == null)
								continue;
							assertEquals(appt1, apptList.get(0));
							assertEquals(appt2, apptList.get(1));
							assertEquals(2, apptList.size());
						}
					}
					else if (methodName.equals("getApptRange")) {
						if(apptList.getFirst() == null)
							continue;
						try {
							tt.getApptRange(apptList, last, first);
							fail();
						}catch(DateOutOfRangeException e){}
						CalDay cal1 = tt.getApptRange(apptList, first, last).get(1);
						assertEquals(cal.toString(), cal1.toString());
						apptList.add(apptI);
						cal1 = tt.getApptRange(apptList, first, last).get(1);
						assertEquals(cal.toString(), cal1.toString());
					}
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			System.out.println("Err: null pointer exception");	
		}
	 
		 System.out.println("Done testing...");
	 }


	
}
