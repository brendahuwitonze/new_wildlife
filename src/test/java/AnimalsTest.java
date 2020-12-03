import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule ();

private Animals testAnimal;


    public Animals setNewAnimal(){
      return new Animals ("zebra");

    }
    @Test
    public void allAnimalClassest(){
        Animals  testAnimal=new Animals ("zebra");
        assertEquals (true, testAnimal instanceof Animals);
    }
    @Test
    public void getName() {
        Animals testAnimal=setNewAnimal ();
        assertEquals("zebra", testAnimal.getName());
    }
//    @Test
//    public void getId_animalInstantiatesWithId(){
//          Animals testAnimal=setNewAnimal ();
//            testAnimal.save ();
//        assertTrue(testAnimal.getId() > 0);
//        }

//    @Test
//    public void getType_animalInstantiatesWithType_Type() {
//        Animals testAnimal=setNewAnimal ();
//        assertEquals("Non-endangered", testAnimal.getType());
//    }
//    @Test
//    public void findById() {
//        Animals testAnimal=setNewAnimal();
//        testAnimal.save();
//        Animals foundAnimal= Animals.find(testAnimal.getId());
//        assertTrue(foundAnimal.equals(testAnimal));
//    }
@Test
public void save_savesAnimalIdIntoDB() {
    Animals testAnimal=setNewAnimal ();
    testAnimal.save();
    Animals savedAnimals = Animals.find(testAnimal.getId());
    assertEquals(savedAnimals.getId(), testAnimal.getId());
}
//    @Test
//    public void all_returnsAllInstancesOfAnimal_false() {
//        Animals testAnimal=setNewAnimal ();
//        testAnimal.save();
//        Animals otherAnimal = new Animals("Deer");;
//        otherAnimal.save();
//        assertEquals(true, Animals.all().get(0).equals(testAnimal));
//        assertEquals(true, Animals.all().get(1).equals(otherAnimal));
//    }

}