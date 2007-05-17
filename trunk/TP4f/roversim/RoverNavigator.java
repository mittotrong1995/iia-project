package roversim;

public class RoverNavigator {

	private static final String PATH="D:\\Pedro\\TP4\\RoverSim\\";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	       String[] rnet1 = { PATH + "teste1.snet"};
	       String[] rnet2 = { PATH + "rov84out.snet",PATH + "rov85out.snet",PATH + "rov86out.snet"};

//	       NeuralController rnet = new NeuralController(rnet1, rnet2);
	       
	       RoverSim rs = new RoverSim(PATH, "th.png", null);
//	       RoverSim rs = new RoverSim(PATH, "th.png", rnet);
	       
	       rs.start();

	}

}
