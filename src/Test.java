import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Test {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		World world = new World();
		int numNotReturn = 0;
		int numNotStart = 0;
		int numUser = 0;
		int numStation = 0;
		XSSFWorkbook userData = new XSSFWorkbook(new FileInputStream(new File("/Users/Kiana/Desktop/SAMPLE/newtest/user.xlsx")));
		XSSFSheet sheetUserData = userData.getSheetAt(0);

		Iterator<Row> rowUserDataIterator = sheetUserData.iterator();
		while (rowUserDataIterator.hasNext() && (numUser<=568)) {
			
			Row rowUser = rowUserDataIterator.next();
			
			User a = new User((String)rowUser.getCell(0).getStringCellValue());
			a.setEnterStation(new Station((String)rowUser.getCell(1).getStringCellValue()));
			a.setEnterTime((double)rowUser.getCell(2).getNumericCellValue());
			a.setPickOrDrop((boolean)rowUser.getCell(3).getBooleanCellValue());
			a.setWorld(world);
			world.addUsers(a);
			world.addVisitor(a);
			numUser++; 
		}
		XSSFWorkbook stationData = new XSSFWorkbook(new FileInputStream(new File("/Users/Kiana/Desktop/SAMPLE/newtest/station.xlsx")));
		XSSFSheet sheetStationData = stationData.getSheetAt(0);

		Iterator<Row> rowStationDataIterator = sheetStationData.iterator();
		while (rowStationDataIterator.hasNext() && (numStation<=20)) {
			
			Row rowStation = rowStationDataIterator.next();
			Station a = new Station((String)rowStation.getCell(0).getStringCellValue());
			a.setNumBike((int)rowStation.getCell(1).getNumericCellValue());
			a.setNumDock((int)rowStation.getCell(2).getNumericCellValue());
			a.setCapacity((int)rowStation.getCell(3).getNumericCellValue());
			world.addStations(a);
			numStation++; 
		}
		Truck a = new Truck("a",15);
		XSSFWorkbook truckData = new XSSFWorkbook(new FileInputStream(new File("/Users/Kiana/Desktop/SAMPLE/newtest/trucknewnew.xlsx")));
		XSSFSheet sheetTruckData = truckData.getSheetAt(0);

		Iterator<Row> rowTruckDataIterator = sheetTruckData.iterator();
		while (rowTruckDataIterator.hasNext() ) {
			Row rowTruck = rowTruckDataIterator.next();
			a.addStation(new Station(rowTruck.getCell(0).getStringCellValue()));
			a.addPickOrDrop((boolean)(rowTruck.getCell(1).getBooleanCellValue()));
			a.addNumPickOrDrop((int)(rowTruck.getCell(2).getNumericCellValue()));
			a.addEnterTime((double)(rowTruck.getCell(3).getNumericCellValue()));
		}
		a.setWorld(world);
		world.addVisitor(a);
		
		for (Visitor visitor:world.getVisitors()) {
			System.out.println(visitor.getName());
			System.out.println(visitor.getEnterTime());
		}

		
		userData.close();
		stationData.close();
		truckData.close();
		
		world.run();

		for (Visitor visitor:world.getVisitors()) {
			if (visitor instanceof User) {
				User user = (User)visitor;

				/*if(user.getPickOrDrop() == true) {
					if(user.getSuccess()==false) {
						numNotStart++;
						System.out.println(user.getName() +" start" + user.getSuccess() + (user.getEnterTime()-360) + user.getEnterStation().getName());
					} 
				} */
				if(user.getPickOrDrop() == false) {
				if(user.getSuccess() == false) {
					numNotReturn++;
					System.out.println(user.getName() +"return" + user.getSuccess() + (user.getEnterTime() -360) +  user.getEnterStation().getName());
				}
				}
			
			} else {
				Truck truck = (Truck)visitor;
				System.out.println("nello");
				for (int j:truck.getNumPickOrDrop()) {
					System.out.println(j + "nello");
				}
				for (int i:truck.getSuccess()) {
					System.out.println(i+"mooo");
				}

			}
		}
		System.out.println("here");
		System.out.println(numNotReturn);
		System.out.println(numNotStart);
		/*System.out.println(world.getStations().get(0).getNumBike());
		System.out.println(world.getStations().get(0).getNumDock());
		System.out.println(world.getStations().get(1).getNumBike());
		System.out.println(world.getStations().get(1).getNumDock());*/
	}
}


