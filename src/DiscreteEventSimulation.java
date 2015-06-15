public class DiscreteEventSimulation {

	public static void main(String[] args) {
		//check arguments to verify trace filepath
		if (args.length > 0) { 
			
			Simulation s = new Simulation(args[0]);
			
			s.setup(); // setup simulation;
			
			s.run(10000); // run of simulation			
		}else{
			System.out.println("Please provide the trace filepath");
			System.out.println("Usage: DiscreteEventSimulation trace_filepath");
		}

		
		
	}

}
