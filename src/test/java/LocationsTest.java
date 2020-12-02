import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationsTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule ();

    @Test
    public void createInstanceOfLocationsClass (){
        Locations location=setUpNewLocation ();
        assertEquals (true, location instanceof Locations);
    }
    private Locations setUpNewLocation() {
        return new Locations("Akagera");
    }
//    @Test
//    public void allSaved() {
//        Locations location=setUpNewLocation();
//        Locations newLocation=new Locations("");
//        try {
//            location.save();
//            assertTrue(Locations.all().get(0).equals(location));
//            newLocation.save();
//
//        }catch (IllegalArgumentException e){
//            System.out.println(e);
//        }

//    }
    @Test
    public void SightingReturnForRanger() {

        Locations location = setUpNewLocation();

        try {

            location.save();

            Sightings sighting=new Sightings(location.getId(),3,3);

            Sightings otherSighting=new Sightings(location.getId(),3,3);

            sighting.save();

            otherSighting.save();

            assertEquals(location.getLocationSightings().get(0),sighting);

            assertEquals(location.getLocationSightings().get(1),otherSighting);

        }catch (IllegalArgumentException e){

            System.out.println(e);
        }

    }


}