import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animals implements DatabaseManagement {
    public int id;
    public String name;
    public String type;
    public static final String ANIMAL_TYPE="Non-endangered";
    public String health;


    public Animals (String name){
        if (name.equals ("")) {
            throw new IllegalArgumentException ("Please enter an animal name.");
        }

        this.name=name;
        this.type=ANIMAL_TYPE;
        this.health="";


    }

    public String getName (){
        return name;
    }

    public int getId (){
        return id;
    }

    public String getType (){
        return type;
    }

    public void save (){
        if (this.name.equals ("") || this.type.equals ("") || this.name.equals (null) || this.type.equals (null)) {
            throw new IllegalArgumentException ("Fields cannot be empty");
        }
        try (Connection con=DB.sql2o.open ()) {


            String sql="INSERT INTO animals (name,type) VALUES (:name,:type)";

            this.id=(int) con.createQuery (sql, true)
                    .addParameter ("name", this.name)
                    .addParameter ("type", this.type)
                    .executeUpdate ()
                    .getKey ();
        }


    }

    public static Animals find (int id){
        try (Connection con=DB.sql2o.open ()) {
            String sql="SELECT * FROM animals WHERE id=:id";
            Animals animal=con.createQuery (sql)
                    .addParameter ("id", id)
                    .throwOnMappingFailure (false)
                    .executeAndFetchFirst (Animals.class);
            return animal;


        }

        }
    public static List<Animals> all(){
        try (Connection con=DB.sql2o.open()) {
            String sql ="SELECT * FROM animals";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);

        }

    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Animals animals=(Animals) o;
        return name.equals (animals.name) &&
                type.equals (animals.type);

    }
    @Override
    public int hashCode() {
        return Objects.hash(name,type);
    }

    public String getHealth (){
        return health;
    }
}




