import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Simulation {
	public Random generator = new Random(); // random number generator
	public EventHeap h;
	double now;
	public ArrayList<Double> workingTime=new ArrayList<Double>();
	public ArrayList<Double> repairingTime=new ArrayList<Double>();
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	public String trace_fp ;
	
	public Simulation(String trace_file) {
		generator = new Random();
		h = new EventHeap(10000);
		now = 0;
		trace_fp = trace_file;
	}

	public void scheduleEvent(Event e) {
		h.insert(e);
	}
	
	public void setup() {
		//upload trace file
		this.loadTraceFile();
		Event machineEvent = new Event();
		machineEvent.setHandler(m);
		machineEvent.setType(working);
		machineEvent.setTime(0);
		scheduleEvent(machineEvent);
		
		Event userEvent = new Event();
		userEvent.setHandler(u);
		userEvent.setType(userCheck);
		userEvent.setTime(60);
		scheduleEvent(userEvent);
		return;
	}
	
	public void run(double maxTime) {
		while (!h.isEmpty() && h.peek().getTime()<=maxTime && workingTime.size()>0 && repairingTime.size()>0) {
			Event nextEvent = h.remove();
			now = nextEvent.getTime();
			nextEvent.getHandler().respondToEvent(nextEvent, this);
		}
	}
	//read trace file and load time in arraylist working and repairing
	private void loadTraceFile(){
		String trace;
                FileReader f;
		try {
			f = new FileReader(trace_fp);
			BufferedReader br = new BufferedReader(f);
		    while((trace = br.readLine())!=null) {
		    	  String[] details = trace.split(" ");
		    	  if (String.valueOf('w').equals(details[0]))
		    		  workingTime.add(Double.parseDouble(details[1]));		    	  
		    		  
		    	  if (String.valueOf('r').equals(details[0]))
		    		  repairingTime.add(Double.parseDouble(details[1]));

		    }
		    br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Trace file not found!");
			System.exit(1);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to read trace file");
			System.exit(1);
		}
	    
	}
	
	public double getWorkingTime()
	{
		double wtime = 0;
		//get time at the top of the arraylist and remove
		if (workingTime.size() >0){
			wtime = (double) workingTime.get(0);
			workingTime.remove(0);
			
		}
		return wtime;
	}
	
	public double getRepairingTime()
	{
		double rtime = 0;
		//get time at the top of the arraylist and remove
		if (repairingTime.size() >0){
			rtime = (double) repairingTime.get(0);
			repairingTime.remove(0);
			
		}
		return rtime;
	}
	// events
	public final int working = 1;
	public final int failure = 2;
	public final int startRepair = 3;
	public final int finishRepair = 4;
	public final int userCheck = 5;

}
