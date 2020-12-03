import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangersTest {

    @Rule
    public DatabaseRule databaseRule=new DatabaseRule ();

    @Test
    public void createInstanceOfRangersClass (){
        Rangers ranger=setNewRanger ();
        assertEquals (true, ranger instanceof Rangers);
    }

    @Test
    public void getName() {
        Rangers testAnimal=setNewRanger ();
        assertEquals("bress", testAnimal.getName());
    }
    @Test
    public void getbadge() {
        Rangers testAnimal=setNewRanger ();
        assertEquals("1", testAnimal.getBadge_number());
    }
    @Test
    public void getPhone() {
        Rangers testAnimal=setNewRanger ();
        assertEquals("0787123456", testAnimal.getPhone_number());
    }

    private Rangers setNewRanger (){
        return new Rangers ("bress", "1", "0787123456");
    }

@Test
public void empty() {
    Rangers ranger=new Rangers("","","0783245673");
    try{
        ranger.save();

        assertTrue(Rangers.all().get(0).equals(ranger));

    }catch (IllegalArgumentException e){

        System.out.println(e);
    }

}
    @Test
    public void findById() {
        Rangers ranger= setNewRanger();

        Rangers otherRanger=new Rangers("zilfa","2","07845677888");
        ranger.save();

        otherRanger.save();

        Rangers foundRanger=Rangers.find(ranger.getId());

        assertTrue(foundRanger.equals(ranger));

    }
    @Test
    public void Updated() {

        Rangers testranger= setNewRanger();

        Rangers otherRanger=testranger;

        testranger.save();
        try {
            testranger.update(testranger.getId(),"brendah","073414735954");

            Rangers foundRanger=Rangers.find(testranger.getId());

            assertNotEquals(foundRanger,otherRanger);

            assertEquals(foundRanger.getId(),otherRanger.getId());


        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }
    @Test
    public void ReturningslightsForRanger() {

        Rangers testranger=setNewRanger();

        try {
            Locations location=new Locations("Akagera");

            testranger.save();

            location.save();

            Sightings sighting=new Sightings(location.getId(),testranger.getId(),3);

            Sightings otherSighting=new Sightings(3,testranger.getId(),3);

            sighting.save();

            otherSighting.save();

            assertEquals(testranger.getRangerSightings().get(0),sighting);

            assertEquals(testranger.getRangerSightings().get(1),otherSighting);

        }catch (IllegalArgumentException e){

            System.out.println(e);
        }

    }


}

