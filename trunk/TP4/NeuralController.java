package roversim;

import java.util.Arrays;

public class NeuralController implements RoverController {

	private RoverNet[] accNet;
	private RoverNet[] rotNet;
	private double[] outAcc;
	private double[] outRot;
	
		
	public NeuralController(String[] accNames, String[] rotNames) {
		accNet = new RoverNet[accNames.length];
		rotNet = new RoverNet[rotNames.length];
		
		int idx=0;
		for(String filename:accNames) {
			accNet[idx++] = new RoverNet(filename);
		}

		idx=0;
		for(String filename:rotNames) {
			rotNet[idx++] = new RoverNet(filename);
		}
	}
	
	public void setSensorData(SensorData s) {
		// Caso pretenda alterar o número de inputs só terá de alterar esta classe
		// s.getSensorData() obtém uma array com 78 doubles com todos os valores
		// dos inputs.
		
		// Colocam-se os inputs na primeira sequência de redes (para a aceleração)
		for(RoverNet r:accNet)
			r.setInput(s.getSensorData());
		
		// Colocam-se os inputs na segunda sequência de redes (para a rotação)
		for(RoverNet r:rotNet)
			r.setInput(s.getSensorData());

		// Obtém os valores de output da primeira sequência de redes
		outAcc = new double[5];
		int idx=0;
		for(RoverNet r:accNet) {
			for(double o:r.getOutput())
				outAcc[idx++] = o;
		}
		
		// Obtém os valores de output da segunda sequência de redes
		outRot = new double[3];
		idx=0;
		for(RoverNet r:rotNet) {
			for(double o:r.getOutput())
				outRot[idx++] = o;
		}

	}

	public int getAcceleration(boolean reset) {
		double maxout = 0.0;
		int maxidx=0;
		if( outAcc == null)
			return 0;
		for(int i=0; i < 5 ; i++)
			if( maxout < outAcc[i]) {
				maxidx=i;
				maxout = outAcc[i];
			}
		if( maxout < 0.5 )
			return 0;
		switch(maxidx) {
			case 0: return 2;
			case 1: return 1;
			case 2: return 0;
			case 3: return -1;
			case 4: return -2;
		}
		return 0;
	}
	
	public int getRotation(boolean reset) {
		double maxout = 0.0;
		int maxidx=0;
		if( outRot == null)
			return 0;
		for(int i=0; i < 3 ; i++)
			if( maxout < outRot[i]) {
				maxidx=i;
				maxout = outRot[i];
			}
		if( maxout < 0.5 )
			return 0;
		switch(maxidx) {
			case 0: return 1;
			case 1: return 0;
			case 2: return -1;
		}
		return 0;
	}
	
	public String toString() {
		return "OUTPUT " + Arrays.toString(outAcc) + " " + Arrays.toString(outRot); 
	}
}

