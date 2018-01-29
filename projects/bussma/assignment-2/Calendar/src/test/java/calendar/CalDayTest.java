package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	  public void test01()  throws Throwable  {
		 CalDay cal = new CalDay();
		 GregorianCalendar today = new GregorianCalendar(2018, 1, 31);
		 CalDay cal1 = new CalDay(today);
		 CalDay cal2 = new CalDay(today);
		 int startHour=21;
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
		 startHour = 22;
		 Appt appt1 = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 startHour = 18;
		 Appt appt2 = new Appt(startHour,
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

		 cal1.addAppt(appt);
		 cal1.addAppt(appt1);
		 cal1.addAppt(appt1);
		 
		 assertTrue(cal.isValid());
		 assertTrue(cal1.isValid());
		 assertEquals(3, cal1.getDay());
		 assertEquals(2, cal1.getMonth());
		 assertEquals(2018, cal1.getYear());
		 assertEquals(3, cal1.getSizeAppts());

		 cal2.addAppt(apptI);
		 assertEquals(0, cal2.getSizeAppts());
	  }
	 //stringbuilder test
	 @Test
	  public void test02()  throws Throwable  {
		 GregorianCalendar today = new GregorianCalendar(2018, 1, 31);
		 CalDay cal = new CalDay(today);
		 int startHour=21;
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
		 
		 cal.addAppt(appt);

		 assertEquals("\t --- 2/3/2018 --- \n --- -------- Appointments ------------ --- \n\t1/15/2018 at 9:30pm ,Birthday Party, This is my birthday party.\n \n", cal.toString());

		 cal.valid = false;
		 assertEquals("", cal.toString());
	 }
	 //iterator test
	 @Test
	 public void test03() throws Throwable {
		 GregorianCalendar today = new GregorianCalendar(2018, 1, 31);
		 CalDay cal = new CalDay(today);
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
		 Appt apptI = new Appt(24,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
		 cal.addAppt(appt);
		 cal.addAppt(appt1);
		 cal.addAppt(apptI);
		 cal.valid = false;
		 assertEquals(null, cal.iterator());
		 cal.valid = true;
		 Iterator itr = cal.iterator();
		 int loop = 0;
		 while(itr.hasNext()) {
			 Appt i = (Appt)itr.next();
			 if (loop < 2) {
				 assertTrue(i.getValid());
			 }
			 else {
				 assertFalse(i.getValid());
			 }
			 loop++;
		 }
			 
	 }
//add more unit tests as you needed	
}
