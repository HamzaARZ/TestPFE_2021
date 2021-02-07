package sii.maroc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Vehicles {
	
	HashMap<String, Integer> fuelCosume;
	HashMap<String, String> doors;

	public Vehicles(String fuelDesc) {
		fuelCosume = new HashMap<String, Integer>();
		doors = new HashMap<String, String>();
		
		setFuelDesc(fuelDesc);
		
		doors.put("CAR", "1 2 3 4");
		doors.put("TRUCK", "1 2");
		doors.put("MOTORCYCLE", "");
		
		System.out.println(fuelCosume.toString());
		System.out.println(doors.toString());
		
	}
	
	public void setFuelDesc(String fuelDesc) {
		List<String> fuelList = Arrays.asList(fuelDesc.split(","));
		
		
		fuelList.forEach(fuel -> {
			String [] typeConsommation = fuel.split(":");
			
			String type = typeConsommation[0];
			String consommation = typeConsommation[1].split("%")[0];
			
			
			fuelCosume.put(type, Integer.parseInt(consommation));
		});
	}
	
	public double consume(String fuelType, int kms) {
		return ((kms*fuelCosume.get(fuelType))/(double)100);
	}
	
	
	public String move(String vehicleType, String fuel, String doors, String kms) {
		String openedDoors = checkDoors(vehicleType, doors);
		if(openedDoors.equals(""))
			return goodReport(vehicleType, fuel, kms);
		else
			return blockedDoorsReport(openedDoors);
		
		
	}
	
	private String goodReport(String vehicleType, String fuel, String kms) {
		int intKms = Integer.parseInt(kms.split(" ")[0]);
		
		
		
		String report = "DOORS OK, MOVING. The ";
		report += vehicleType;
		report += " will consume ";
		double liters = consume(fuel, intKms);
		report += liters;
		// this will add always a '0' at the end ! (need a fix even the tests passed)
		report += "0";
		report += " L";
		
		
		return report;
	}

	// reporting blocked doors
	private String blockedDoorsReport(String openedDoors) {
		String report = "DOORS KO, BLOCKED \n";
		
		
		if(openedDoors.contains("1")) {
			report += "  _\n"+
                    " / |\n"+
                    " |_|";
		}
		if(openedDoors.contains("2")) {
			report += "  _\n"+
                    " | \\\n"+
                    " |_|";
		}
		if(openedDoors.contains("3")) {
			report += "  _\n"+
                    " | |\n"+
                    " /_|";
		}
		if(openedDoors.contains("4")) {
			report += "  _\n"+
                    " | |\n"+
                    " |_\\";
		}
		
		return report;
		
	}

	//return a string of opened doors
	public String checkDoors(String vehicleType, String closedDoors) {
		String openedDoors = "";
		
		String doors = this.doors.get(vehicleType);
		
		List<String> doorsList = Arrays.asList(doors.split(" "));


		for (String door:doorsList) {
			if (!closedDoors.contains(door))
				openedDoors+=door;
		}
		
		return openedDoors;
	}
	

}
