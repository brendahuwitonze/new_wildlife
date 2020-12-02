import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalsTest {

    @Rule
    public DatabaseRule databaseRule=new DatabaseRule ();
    public EndangeredAnimals setNewEngeredAnimal(){
        return new EndangeredAnimals ("zebra","endagered","health","10");

    }
    @Test
    public void allAnimalClassest(){
        DatabaseManagement testAnimal=new EndangeredAnimals ("zebra","Engered","health","10");
        assertEquals (true, testAnimal instanceof EndangeredAnimals);
    }
    @Test
    public void getName() {
        EndangeredAnimals testAnimal=setNewEngeredAnimal ();
        assertEquals("zebra", testAnimal.getName());
    }
    @Test
    public void gethealth() {
        EndangeredAnimals testAnimal=setNewEngeredAnimal ();
        assertEquals("health", testAnimal.getHealth());
    }
    @Test
    public void getage() {
        EndangeredAnimals testAnimal=setNewEngeredAnimal ();
        assertEquals("10", testAnimal.getAge ());
    }

}