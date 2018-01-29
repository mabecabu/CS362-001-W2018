package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the get methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
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
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		

	 }
	//tests bounds checking for isValid and setters
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=24;
		 int startMinute=60;
		 int startDay=00;
		 int startMonth=00;
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

		 assertEquals(null, appt.toString());
		 assertTrue(!appt.getValid());
		 appt.setStartHour(-1);
		 assertTrue(!appt.getValid());
		 appt.setStartHour(20);
		 assertTrue(!appt.getValid());
		 appt.setStartMinute(-1);
		 assertTrue(!appt.getValid());
		 appt.setStartMinute(59);
		 assertTrue(!appt.getValid());
		 appt.setStartDay(32);
		 assertTrue(!appt.getValid());
		 appt.setStartYear(2019);
		 appt.setTitle(null);
		 appt.setDescription(null);
		 assertTrue(!appt.getValid());
		 
	 }
	 @Test
	 public void test03() throws Throwable {
		 int startHour=22;
		 int startMinute=59;
		 int startDay=01;
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

		 assertFalse(appt.isRecurring());

		 int[] recurArr = {2,3,4};
		 appt.setRecurrence(recurArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

		 assertEquals(Appt.RECUR_NUMBER_FOREVER, appt.getRecurNumber());
		 assertEquals(Appt.RECUR_BY_WEEKLY, appt.getRecurBy());
		 assertEquals(recurArr, appt.getRecurDays());
		 assertEquals(2, appt.getRecurIncrement());
		 assertTrue(appt.isRecurring());
		 appt.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
	 }
	 @Test
	 public void test04() throws Throwable {
		 int startHour=22;
		 int startMinute=59;
		 int startDay=01;
		 int startMonth=01;
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
		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 assertEquals("\t1/1/2018 at 10:59pm ,Birthday Party, This is my birthday party.\n", appt1.toString());
}
}
