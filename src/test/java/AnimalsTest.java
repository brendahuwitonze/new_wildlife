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

@Test
public void save_savesAnimalIdIntoDB() {
    Animals testAnimal=setNewAnimal ();
    testAnimal.save();
    Animals savedAnimals = Animals.find(testAnimal.getId());
    assertEquals(savedAnimals.getId(), testAnimal.getId());
}


}