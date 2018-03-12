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
     * Also verifies constructor
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
		 int[] recurArr = new int[0];
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
		 assertEquals(Appt.RECUR_NUMBER_NEVER, appt.getRecurNumber());
		 assertEquals(Appt.RECUR_BY_MONTHLY, appt.getRecurBy());
		 //assertEquals(recurArr, appt.getRecurDays());
		 assertEquals(0, appt.getRecurIncrement());
		 assertFalse(appt.isRecurring());

	 }
	//tests bounds checking for isValid and setters
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=24;
		 int startMinute=60;
		 int startDay=00;
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

		 assertEquals(null, appt.toString());
		 assertFalse(appt.getValid());
		 appt.setStartHour(-1);
		 assertFalse(appt.getValid());
		 appt.setStartHour(20);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(-1);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(59);
		 assertFalse(appt.getValid());
		 appt.setStartDay(32);
		 assertFalse(appt.getValid());
		 appt.setStartYear(2019);
		 appt.setTitle(null);
		 appt.setDescription(null);
		 assertFalse(appt.getValid());
		 
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
		 assertEquals("\t1/1/2018 at 10:59pm ,Birthday Party, This is my birthday party.\n", appt1.toString());
	}
	//boundary condition mutations
	@Test
	public void test05() throws Throwable {
		 int startHour=22;
		 int startMinute=45;
		 int startDay=05;
		 int startMonth=05;
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

		 appt.setStartHour(0);
		 assertTrue(appt.getValid());
		 appt.setStartHour(23);
		 assertTrue(appt.getValid());
		 appt.setStartHour(20);
		 appt.setStartMinute(0);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(59);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(30);
		 appt.setStartDay(1);
		 assertTrue(appt.getValid());
		 appt.setStartDay(CalendarUtil.NumDaysInMonth(startYear, startMonth));
		 assertTrue(appt.getValid());
		 appt.setStartDay(15);
		 appt.setStartMonth(1);
		 assertTrue(appt.getValid());
		 try {
			 appt.setStartMonth(12);
			 assertTrue(appt.getValid());
		 }
		 catch (Exception e) {
			 System.out.println("Month array exception");
			 assertFalse(appt.getValid());
		 }
		 appt.setStartMonth(11);
		 appt.setStartHour(23);
		 appt.setStartYear(2018);
		 assertTrue(appt.getValid());
	}
	//invalid set value mutations
	@Test
	public void test06() throws Throwable {
		 int startHour=22;
		 int startMinute=45;
		 int startDay=05;
		 int startMonth=05;
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

		 appt.setStartHour(-1);
		 assertFalse(appt.getValid());
		 appt.setStartHour(22);
		 appt.setStartMinute(-1);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(45);
		 appt.setStartDay(-1);
		 assertFalse(appt.getValid());
		 appt.setStartDay(15);
		 try {
			 appt.setStartMonth(0);
			 assertFalse(appt.getValid());
		 }
		 catch (Exception e) {
			 System.out.println("Month array exception");
		 }
	}
	@Test
	public void test07() throws Throwable {
		 int startHour=0;
		 int startMinute=45;
		 int startDay=05;
		 int startMonth=05;
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
		 
		 assertTrue(appt.toString().contains("12"));
		 appt.setStartHour(11);
		 assertFalse(appt.toString().contains("-1"));
		 assertFalse(appt.toString().contains("pm"));
		 appt.setStartHour(10);
		 assertTrue(appt.toString().contains("am"));
		 appt.setStartHour(12);
		 assertTrue(appt.toString().contains("pm"));

	}
	@Test
	public void test08() throws Throwable {
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(2,
		          2 ,
		          2 ,
		          2 ,
		          2 ,
		          title,
		         description);
		 Appt appt1 = new Appt(1,
		          1 ,
		          1,
		          1 ,
		          1 ,
		          title,
		         description);
		 
		 assertEquals(5, appt.compareTo(appt1));
		 //System.out.println("/" + Integer.toString(appt.compareTo(appt1)) + "/");
	}
}
