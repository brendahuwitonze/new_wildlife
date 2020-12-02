import org.junit.Rule;
import org.junit.Test;


import static org.junit.Assert.*;

public class SightingsTest {

    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();
    private Sightings setNewSighting() {
        return new Sightings(3,3,3);
    }

    @Test
    public void InstanceOfSightingsClass_true() {

        Sightings testsighting= setNewSighting();

        assertEquals(true,testsighting instanceof Sightings);
    }

    @Test
    public void Saved() {
        Sightings testsightings=setNewSighting();

        Sightings otherSighting=new Sightings(-3,3,3);
        try {
            testsightings.save();

            otherSighting.save();

            assertTrue(Sightings.find(testsightings.getId()).equals(testsightings));

        }catch (IllegalArgumentException e){

            System.out.println(e);
        }
    }

    @Test
    public void ByID() {

        Sightings testsighting=setNewSighting();

        testsighting.save();

        Sightings foundSighting=Sightings.find(testsighting.getId());

        assertTrue(foundSighting.equals(testsighting));
    }


}
