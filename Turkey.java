//**************************************************************************************
// Turkey.java      Author: Khaled Alshatti
//
// Counts how many turkeys there are
//**************************************************************************************

import java.util.Random;

//import Lab08.Turkey;

public class Turkey {
	private final int MAX_AGE = 100; // days
	private final int PREGNANCY_DURATION = 7; // a turkey is born 7 days
												// after being pregnant
	private final int ADULT_AGE = 20; // Age at which a turkey is considered an
										// adult
	private int gender; // 1 or 2
	private int id; // unique ID
	private int age; // in days, 0 initially
	private int pairedWithID; // 0 if not paired.
	private int daysPregnant; // 0 initially
	private boolean isPregnant; // initially false
	private boolean isAdult; // initially false
	static Random gen = new Random(System.currentTimeMillis());

	// static variables add them here
	private static int nextID = 1; // This holds the value of the next free ID
   private static int totalSlaughtered = 0;
   private static int totalTurkeys = 0;
   private static int totalAdultTurkeys = 0;
   private static int totalYoungTurkeys = 0;
   private static int totalPairedTurkeys = 0;
   private static int totalUnpairedTurkeys = 0;
   
	// Constructor that creates a turkey of a random gender.
	// add code here
	public Turkey() {
		// This is the constructor for a Turkey. When A turkey is
		// born/constructed It will get a random gender, 1 or 2.
		// Use the random number generator above.
		// The other variable we will set
		// to 0 Initially.
      
      this.id = nextID;
      this.gender = gen.nextInt(2)+1;
      this.age = 0;
      this.pairedWithID = 0;
      this.daysPregnant = 0;
      this.isPregnant = false;
      this.isAdult = false;
      
      nextID++;
      totalTurkeys++;
      totalYoungTurkeys++;
      totalUnpairedTurkeys++;
	}

	public static Turkey birth(Turkey t1) {
		// Turkey 1 should no longer be pregnant add code to change to not
		// pregnant
      t1.isPregnant = false;
		t1.daysPregnant = 0;
      return new Turkey();
      
	}

	public boolean isAdult() {
		// returns true if age is over ADULT_AGE
      if(this.age>ADULT_AGE)
      {
         if(!this.isAdult)
         {
            totalAdultTurkeys++;
            totalYoungTurkeys--;         
         }
         this.isAdult = true;

      }
		return this.isAdult;
	}

	public boolean isPregnant() {
		return this.isPregnant;
	}

	public boolean istimeToDie() {
		// returns true if age is > max_Age
      if(this.age > MAX_AGE)
         return true;
		return false;
	}

	public int getID() {
		// returns the value of the ID of the current Turkey
		return this.id;
	}

	public int getPairID() {
		// this method returns the pairID, the ID of the turkey this one is pair
		// with.
		return this.pairedWithID;
	}

	public void unPair() {
		// This method removes the value for pairID and sets it to zero
      this.pairedWithID = 0;
       totalUnpairedTurkeys++;
       totalPairedTurkeys--;
	}

	public void passTime() {
		// add one to each of the day values. This includes the age and
		// daysPregnant.
      this.age++;
      this.daysPregnant++;
	}

	public static void slaughter() {
		// increments a counter that keeps track of how many turkeys have been
		// killed.
      totalSlaughtered++;
	}

	public boolean isPaired() {
		// return true if the turkey pairID is != 0;
      if(this.pairedWithID == 0)
      return false;
      else
      return true;
      
	}

	public static void mate(Turkey t1, Turkey t2) {
		// Always set female to pregnant
		// Determine which of the two Turkey's is female and make pregnant and
		// set days pregnant to zero
      if(t1.gender == 1 && t2.gender == 2 && !t1.isPregnant){
         t1.isPregnant = true;
         t1.daysPregnant = 0;
      }
      else if(t2.gender == 1 && t1.gender == 2 && !t2.isPregnant){
         t2.isPregnant = true;
         t2.daysPregnant = 0;
      }   
	}

	private void setDaysPregnant(int days) {
      this.daysPregnant = days;
	}

	public static void pairTurkeys(Turkey t1, Turkey t2) {
		// This method will set pair ID of each other
      t1.setPairIDTo(t2.id);
      t2.setPairIDTo(t1.id);
	}

	private void setPairIDTo(int id) {
      this.pairedWithID = id;
      totalPairedTurkeys++;
      totalUnpairedTurkeys--;
	}

	public int getGender() {
		return gender;
	}

	public boolean isPregnancyDue() {
		// This method will return true if the daysPregnant is greater than
		// PREGNANCYDURATION
		return this.daysPregnant>PREGNANCY_DURATION;
	}

	public void setIsPregnant(boolean p) {
      this.isPregnant = p;
	}

	// toString method that returns important information about a Turkey
   public String toString()
   {
      String info = " \nID: " + this.getID();
      info+=" Paid ID: " + this.getPairID();
      info+=" Gender: " + this.getGender();
      info+=" Is Pregnant: " + (this.isPregnant()?"Yes":"No");
      info+=" Is Adult: " + (this.isAdult()?"Yes":"No");
      return info;
   }
   
   public static void PrintTotalTurkeys()
   {
      System.out.println("Total Turkeys: "+totalTurkeys);
   }
   
   public static void PrintTotalAdultTurkeys()
   {
      System.out.println("Total Adult Turkeys: "+totalAdultTurkeys);
   }
   public static void PrintTotalYoungTurkeys()
   {
      System.out.println("Total Non-Adult Turkeys: "+totalYoungTurkeys);
   }
   
   public static void PrintTotalPairedTurkeys()
   {
      System.out.println("Total Paired Turkeys: "+totalPairedTurkeys);
   } 
   
   public static void PrintTotalUnpairedTurkeys()
   {
      System.out.println("Total Unpaired Turkeys: "+totalUnpairedTurkeys);
   }        
}
