package uk.ac.aber.dcs.cs12320.cards.timer;
//	http://stackoverflow.com/questions/8255738/is-there-a-stopwatch-in-java 
//	was used. Many web sites implemented the same methods, this is slightly edited
//	to only start, stop and get the total time.
public class Timer {

	  private long startTime = 0;
	  private long stopTime = 0;


	  public void start() {
	    this.startTime = System.currentTimeMillis();
	  }


	  public void stop() {
	    this.stopTime = System.currentTimeMillis();
	  }


	  public long getElapsedTimeSecs() {
	    long elapsed;
	 
	      elapsed = ((stopTime - startTime) / 1000);
	    
	    return elapsed;
	  }
	}