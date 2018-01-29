package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	//delete appt
	 @Test
	  public void test01()  throws Throwable  {
		 GregorianCalendar today = new GregorianCalendar(2018, 1, 31);
		 CalDay cal = new CalDay(today);
		 TimeTable tt = new TimeTable();
		 int startHour=1;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
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
		 Appt appt1 = new Appt(startHour+1,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 Appt appt2 = new Appt(startHour+2,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 Appt apptI = new Appt(24,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		cal.addAppt(appt);
		cal.addAppt(appt1);
		cal.addAppt(appt2);
		cal.addAppt(apptI);
		assertEquals(null, tt.deleteAppt(null, null)); 
		assertEquals(null, tt.deleteAppt(null, apptI)); 
		assertEquals(null, tt.deleteAppt(cal.getAppts(), appt)); 
		assertNotEquals(null, tt.deleteAppt(cal.getAppts(), appt1)); 
	 }
	 //getApptRange test
	 @Test
	  public void test02()  throws Throwable  {
		 GregorianCalendar last = new GregorianCalendar(2018, 1, 31);
		 GregorianCalendar first = new GregorianCalendar(2018, 1, 29);
		 CalDay cal = new CalDay(first);
		 TimeTable tt = new TimeTable();
		 int startHour=1;
		 int startMinute=30;
		 int startDay=28;
		 int startMonth=01;
		 int startYear=2018;
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
		 Appt appt1 = new Appt(startHour+1,
			  startMinute ,
			  startDay+1 ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 Appt appt2 = new Appt(startHour+2,
			  startMinute ,
			  startDay+2 ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 Appt appt3 = new Appt(startHour+2,
			  startMinute ,
			  startDay+3 ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 Appt apptI = new Appt(24,
			  startMinute ,
			  startDay+2 ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		apptList.add(appt);
		apptList.add(appt1);
		apptList.add(appt2);
		apptList.add(appt3);
		apptList.add(apptI);
		try{ 
			tt.getApptRange(apptList, last, first);
			fail("Last date was before first date");
		}
		catch (DateOutOfRangeException e) {
			System.out.println("caught exception");
		}
		assertNotEquals(null, tt.getApptRange(apptList, first, last));
	 }
	 //getNextApptOccurence test
	@Test
	public void test03() throws Throwable {
		 GregorianCalendar first = new GregorianCalendar(2018, 1, 29);
		 GregorianCalendar last = new GregorianCalendar(2018, 2, 10);
		 GregorianCalendar first1 = new GregorianCalendar(2018, 1, 25);
		 GregorianCalendar last1 = new GregorianCalendar(2018, 1, 27);
		 TimeTable tt = new TimeTable();
		 int startHour=1;
		 int startMinute=30;
		 int startDay=29;
		 int startMonth=01;
		 int startYear=2018;
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
		 Appt appt1 = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		apptList.add(appt);
		apptList.add(appt1);
		 int[] recurArr = {2, 3, 4};
		 appt.setRecurrence(recurArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		 recurArr = new int[1];
		 appt.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		 assertNotEquals(null, tt.getApptRange(apptList, first, last));
		 assertNotEquals(null, tt.getApptRange(apptList, first1, last1));

	}
//add more unit tests as you needed
}
