package it.polito.ski;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SkiArea {
	
	private String resortName;
	private TreeMap<String,LiftType> liftTypesColl = new TreeMap<>();
	private Set<Lift> liftsColl = new HashSet<>();
	private Set<Slope> slopeColl = new HashSet<>();
	private Set<CarParking> parkingsColl = new HashSet<>();
	// collection created to associate each parking to the lift served by it
	// map: key parking name value lift name
	private TreeMap<String,List<String>> parkingServices = new TreeMap<>();
	
	
	// this metod return the lift code from liftsColl given the lift name 
	public String getLiftCode(String liftname) {
		
		for (Lift l : this.liftsColl) {
			if (l.getName().equals(liftname)) {
				return l.getTypeCode();
			}
		}
		return null;
	}
	/**
	 * Creates a new ski area
	 * @param name name of the new ski area
	 */
	public SkiArea(String name) {
		this.resortName=name;
	}

	/**
	 * Retrieves the name of the ski area
	 * @return name
	 */
	public String getName() { return this.resortName; }

    /**
     * define a new lift type providing the code, the category (Cable Cabin, Chair, Ski-lift)
     * and the capacity (number of skiers carried) of a single unit
     * 
     * @param code		name of the new type
     * @param category	category of the lift
     * @param capacity	number of skiers per unit
     * @throws InvalidLiftException in case of duplicate code or if the capacity is <= 0
     */
    public void liftType(String code, String category, int capacity) throws InvalidLiftException {
    	
    	// check if the code has already been inserted in the collection
    	if (this.liftTypesColl.containsKey(code)) {
    		throw new InvalidLiftException();
    	}
    	
    	// create a new lift type
    	LiftType l = new LiftType(code,category,capacity);
    	
    	// add it to the collection
    	this.liftTypesColl.put(code,l);
    }
    
    /**
     * retrieves the category of a given lift type code
     * @param typeCode lift type code
     * @return the category of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public String getCategory(String typeCode) throws InvalidLiftException {
		return this.liftTypesColl.get(typeCode).getCategory();
    }

    /**
     * retrieves the capacity of a given lift type code
     * @param typeCode lift type code
     * @return the capacity of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public int getCapacity(String typeCode) throws InvalidLiftException {
        return this.liftTypesColl.get(typeCode).getCapacity();
    }


    /**
     * retrieves the list of lift types
     * @return the list of codes
     */
	public Collection<String> types() {
		
		Collection<String> codesList = this.liftTypesColl.keySet();
		
		return codesList;
	}
	
	/**
	 * Creates new lift with given name and type
	 * 
	 * @param name		name of the new lift
	 * @param typeCode	type of the lift
	 * @throws InvalidLiftException in case the lift type is not defined
	 */
    public void createLift(String name, String typeCode) throws InvalidLiftException{
    	// throws InvalidLiftException in case the lift type is not defined
    	if (!this.liftTypesColl.containsKey(typeCode)) {
    		throw new InvalidLiftException();
    	}
    	
    	Lift l = new Lift(name, typeCode);
    	
    	this.liftsColl.add(l);
    }
    
	/**
	 * Retrieves the type of the given lift
	 * @param lift 	name of the lift
	 * @return type of the lift
	 */
	public String getType(String lift) {
		
		for (Lift l : this.liftsColl)
		{
			if (l.getName().equals(lift)) {
				return l.getTypeCode();
			}
		}
		System.out.println("getType didn't find the lift");
		return null;
	}

	/**
	 * retrieves the list of lifts defined in the ski area
	 * @return the list of names sorted alphabetically
	 */
	public List<String> getLifts(){
		return this.liftsColl.stream().map(Lift::getName).toList();
    }

	// this method check if a given lift has been defined
	public boolean isLiftDefined(String lift) {
		for (Lift l : this.liftsColl) {
			if (l.getName().equals(lift))
				return true;
		}
		return false;
	}
	/**
	 * create a new slope with a given name, difficulty and a starting lift
	 * @param name			name of the slope
	 * @param difficulty	difficulty
	 * @param lift			the starting lift for the slope
	 * @throws InvalidLiftException in case the lift has not been defined
	 */
    public void createSlope(String name, String difficulty, String lift) throws InvalidLiftException {
    	
    	// throws InvalidLiftException in case the lift has not been defined
    	if (!isLiftDefined(lift)) {
    		throw new InvalidLiftException();
    	}
    	
    	// create a new slope 
    	Slope s = new Slope(name,difficulty,lift);
    	
    	// add the slope to the collection
    	this.slopeColl.add(s);
    }
    
    /**
     * retrieves the name of the slope
     * @param slopeName name of the slope
     * @return difficulty
     */
	public String getDifficulty(String slopeName) {
		// looking for the slope
		for (Slope s : this.slopeColl) {
			if (s.getRunName().equals(slopeName)) {
				return s.getDifficulty();
			}
		}
		System.out.println("Slope not found!");
		return null;
	}

	/**
	 * retrieves the start lift
	 * @param slopeName name of the slope
	 * @return starting lift
	 */
	public String getStartLift(String slopeName) {
		
		// looking for the slope
		for (Slope s : this.slopeColl) {
			if (s.getRunName().equals(slopeName)) {
				return s.getLiftName();
			}
		}
		System.out.println("Slope not found!");
				
		return null;
	}

	/**
	 * retrieves the list of defined slopes
	 * 
	 * @return list of slopes
	 */
    public Collection<String> getSlopes(){
		return this.slopeColl.stream().map(Slope::getRunName).toList();
    }

    /**
     * Retrieves the list of slopes starting from a given lift
     * 
     * @param lift the starting lift
     * @return the list of slopes
     */
    public Collection<String> getSlopesFrom(String lift){
		return this.slopeColl.stream()
				.filter(s->s.getLiftName().equals(lift))
				.map(Slope::getRunName)
				.toList();
				
    }

    /**
     * Create a new parking with a given number of slots
     * @param name 	new parking name
     * @param slots	slots available in the parking
     */
    public void createParking(String name, int slots){
    	
    	// create a new parking area
    	CarParking parking = new CarParking(name,slots);
    	
    	this.parkingsColl.add(parking);
    	
    }

    /**
     * Retrieves the number of parking slots available in a given parking
     * @param parking	parking name
     * @return number of slots
     */
	public int getParkingSlots(String parking) {
		
		// look for the correct parking area
		for (CarParking p : this.parkingsColl) {
			if (p.getName().equals(parking)) {
				return p.getAvailableSlots(); 
			}
		}
		System.out.println("Parking area not found!");
		return -1;
	}

	/**
	 * Define a lift as served by a given parking
	 * @param lift		lift name
	 * @param parking	parking name
	 */
	public void liftServedByParking(String lift, String parking) {
		// if we don't have yet this parking services we create a new entry
		if (!this.parkingServices.containsKey(parking)) {
			List<String> liftsServed = new LinkedList<>();
			liftsServed.add(lift);
			this.parkingServices.put(parking, liftsServed);
			return;
		}
		// otherwise we just need to update the list of lift served by the current parking area
		this.parkingServices.get(parking).add(lift);
	}

	
	/**
	 * Retrieves the list of lifts served by a parking.
	 * @param parking	parking name
	 * @return the list of lifts
	 */
	public Collection<String> servedLifts(String parking) {
		return this.parkingServices.get(parking);
	}

	/**
	 * Checks whether the parking is proportional to the capacity of the lift it is serving.
	 * A parking is considered proportionate if its size divided by the sum of the capacity of the lifts 
	 * served by the parking is less than 30.
	 * 
	 * @param parkingName name of the parking to check
	 * @return true if the parking is proportionate
	 */
	public boolean isParkingProportionate(String parkingName) {
		
		// get the list of lifts served
		List<String> served = this.parkingServices.get(parkingName);
		
		// for each lift get the slots and update the total number of slots
		int totalSlotsNumber=0;
		for (String s : served) {
			String liftcode = getLiftCode(s);
			int slots = this.liftTypesColl.get(liftcode).getCapacity();
			totalSlotsNumber += slots;
		}
		// get parking area size
		int parkingSize = getParkingSlots(parkingName);
		double res = parkingSize/totalSlotsNumber;
		
		if (res<30) {
			return true;
		}
		return false;
	}

	/**
	 * reads the description of lift types and lift descriptions from a text file. 
	 * The contains a description per line. 
	 * Each line starts with a letter indicating the kind of information: "T" stands for Lift Type, 
	 * while "L" stands for Lift.
	 * A lift type is described by code, category and seat number. 
	 * A lift is described by the name and the lift type.
	 * Different data on a line are separated by ";" and possible spaces surrounding the separator are ignored.
	 * If a line contains the wrong number of information it should be skipped and
	 * the method should continue reading the following lines. 
	 * 
	 * @param path 	the path of the file
	 * @throws IOException	in case IO error
	 * @throws InvalidLiftException in case of duplicate type or non-existent lift type
	 */
    public void readLifts(String path) throws IOException, InvalidLiftException {

    }

}
