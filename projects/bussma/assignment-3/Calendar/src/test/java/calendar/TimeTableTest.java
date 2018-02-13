package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	//delete appt
	 @Test
	  public void test01()  throws Throwable  {
		 GregorianCalendar last = new GregorianCalendar(2018, 1, 31);
		 GregorianCalendar first = new GregorianCalendar(2018, 1, 29);
		 CalDay cal = new CalDay(first);
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
		 assertNotNull(tt.getApptRange(cal.getAppts(), first, last).getFirst());
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
		 String title = "title";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			  "1");
		 Appt appt1 = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			  "2");
		 Appt appt2 = new Appt(startHour+2,
			  startMinute ,
			  startDay+2 ,
			  startMonth ,
			  startYear ,
			  title,
			  "3");
		 Appt appt3 = new Appt(startHour+2,
			  startMinute ,
			  startDay+3 ,
			  startMonth ,
			  startYear ,
			  title,
			  "4");
		 Appt apptI = new Appt(24,
			  startMinute ,
			  startDay+1 ,
			  startMonth ,
			  startYear ,
			  title,
			  "I1");
		 Appt apptI1 = new Appt(24,
			  startMinute ,
			  startDay+2 ,
			  startMonth ,
			  startYear ,
			  title,
			  "I2");
		 Appt apptI2 = new Appt(24,
			  61 ,
			  startDay+2 ,
			  startMonth ,
			  startYear ,
			  title,
			  "I3");
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		LinkedList<CalDay> calList = tt.getApptRange(apptList, first, last);
		Iterator<CalDay> calItr = calList.iterator();
		while(calItr.hasNext()){
			assertEquals(0, calItr.next().getSizeAppts());
		}
		apptList.add(appt);
		apptList.add(appt1);
		apptList.add(appt2);
		apptList.add(appt3);
		apptList.add(apptI);
		apptList.add(apptI1);
		apptList.add(apptI2);
		System.out.println(apptList);
		try{ 
			tt.getApptRange(apptList, last, first);
			fail("Last date was before first date");
		}
		catch (DateOutOfRangeException e) {
			System.out.println("caught exception");
		}
		calList = tt.getApptRange(apptList, first, last);
		assertNotEquals(2, calList.get(1).getSizeAppts());
		System.out.println(calList.get(1).getSizeAppts());
		assertNotEquals(null, calList);
		calItr = calList.iterator();
		while(calItr.hasNext()){
			CalDay item = calItr.next();
			//System.out.println(item.getSizeAppts());
			//assertEquals(0, item.getSizeAppts());
		}

		//System.out.println("Length for pv" + apptList.size());
		LinkedList<Appt> apptList1 = new LinkedList<Appt>();
		int[] permb = {4, 1, 2, 0, 3, 5};
		try {
			apptList1 = tt.permute(apptList, permb);
			fail();
		}
		catch (IllegalArgumentException e) {
		}
		int[] perm = {2, 1, 0, 6, 5, 4, 3};
		apptList1 = tt.permute(apptList, perm);
		//System.out.println(apptList1);
		assertEquals(7, apptList1.size());
		//System.out.println(apptList.getFirst());
		//System.out.println(apptList1.getFirst());
		assertEquals(apptList1.getFirst(), apptList.getFirst());
	 }
	 //getNextApptOccurence test
	@Test
	public void test03() throws Throwable {
		 GregorianCalendar first = new GregorianCalendar(2018, 1, 29);
		 GregorianCalendar last = new GregorianCalendar(2018, 2, 10);
		 GregorianCalendar first1 = new GregorianCalendar(2018, 1, 25);
		 GregorianCalendar last1 = new GregorianCalendar(2018, 2, 14);
		 TimeTable tt = new TimeTable();
		 int startHour=1;
		 int startMinute=30;
		 int startDay=23;
		 int startMonth=1;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt1 = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		int[] recurArr = {1};
		appt1.setRecurrence(recurArr, Appt.RECUR_BY_WEEKLY, 1, 15);
		apptList.add(appt1);
		//System.out.println(tt.getApptRange(apptList, first1, last1));
		//System.out.println("Val: " + apptList.getFirst().isRecurring());
		assertNotEquals(null, tt.getApptRange(apptList, first, last));
		assertEquals(17, tt.getApptRange(apptList, first1, last1).size());
		//System.out.println(tt.getApptRange(apptList, first1, last1).getFirst().getSizeAppts());
		assertEquals(1, tt.getApptRange(apptList, first1, last1).getFirst().getSizeAppts());
		LinkedList<CalDay> calList = tt.getApptRange(apptList, first1, last1);
		//System.out.println("s " + calList.size());
		Iterator<CalDay> itr = calList.iterator();
		int count = 0;
		while (itr.hasNext()) {
			//System.out.println(itr.next().getClass().getName());
			CalDay cal = itr.next();
			count += cal.getSizeAppts();
		}	
		assertEquals(3, count);
		System.out.println(count);

	}
//add more unit tests as you needed
}
