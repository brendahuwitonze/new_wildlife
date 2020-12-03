import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.route.HttpMethod.post;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args){

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());



        get("/create/animal",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"animalsForms.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/animal/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name=request.queryParams("name");
            System.out.println(name);
            Animals animal=new Animals(name);
            animal.save();

            return new ModelAndView(model,"animalsForms.hbs");
        },new HandlebarsTemplateEngine());

        get("/view/animal",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("animals",Animals.all());
            return new ModelAndView(model,"animals.hbs");
        },new HandlebarsTemplateEngine());


        post("/create/Endangeredanimal/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String health=request.queryParams("health");
            System.out.println(health);
            String age=request.queryParams("age");
            System.out.println(age);
            String name=request.queryParams("name");
            System.out.println(name);
            EndangeredAnimals endangered=new EndangeredAnimals(name,health,age);
                endangered.save();
            return new ModelAndView(model,"EndangeredForm.hbs");
        },new HandlebarsTemplateEngine());
        get("/create/Endangeredanimal",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            List<String> health= new ArrayList<String>();
            health.add(EndangeredAnimals.HEALTH_HEALTHY);
            health.add(EndangeredAnimals.HEALTH_ILL);
            health.add(EndangeredAnimals.HEALTH_OKAY);
            List<String> age= new ArrayList<String>();
            age.add(EndangeredAnimals.AGE_ADULT);
            age.add(EndangeredAnimals.AGE_NEWBORN);
            age.add(EndangeredAnimals.AGE_YOUNG);
            model.put("health",health);
            model.put("age",age);
            String typeChosen="endangered";
            model.put("endangered",typeChosen);
            return new ModelAndView(model,"EndangeredForm.hbs");
        },new HandlebarsTemplateEngine());

        get("/view/Endangeredanimals",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("Endangered",EndangeredAnimals.all());
            return new ModelAndView(model,"Endangered.hbs");
        },new HandlebarsTemplateEngine());






        get("/create/location",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"LocationsForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/location/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name=request.queryParams("name");
            Locations location=new Locations(name);
            try {
                location.save();
            }catch (IllegalArgumentException e){
                System.out.println(e);
            }

            return new ModelAndView(model,"LocationsForm.hbs");
        },new HandlebarsTemplateEngine());
        get("/view/locations",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("locations",Locations.all());
            return new ModelAndView(model,"Location.hbs");
        },new HandlebarsTemplateEngine());

        get("/view/location/sightings/:id",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            int idOfLocation= Integer.parseInt(request.params(":id"));
            Locations foundLocation=Locations.find(idOfLocation);
            List<Sightings> sightings=foundLocation.getLocationSightings();
            ArrayList<String> animals=new ArrayList<String>();
            ArrayList<String> types=new ArrayList<String>();
            for (Sightings sighting : sightings){
                String animal_name=Animals.find(sighting.getAnimal_id()).getName();
                animals.add(animal_name);
            }
            model.put("sightings",sightings);
            model.put("animals",animals);
            model.put("locations",Locations.all());
            return new ModelAndView(model,"Location.hbs");
        },new HandlebarsTemplateEngine());


        get("/create/ranger",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"RangerForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/ranger/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name=request.queryParams("name");
            String badge_number=request.queryParams("badge");
            String phone_number=request.queryParams("phone");
            Rangers ranger=new Rangers(name,badge_number,phone_number);
            ranger.save();
            return new ModelAndView(model,"RangerForm.hbs");
        },new HandlebarsTemplateEngine());
        get("/view/rangers",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("rangers",Rangers.all());
            return new ModelAndView(model,"Ranger.hbs");
        },new HandlebarsTemplateEngine());
        get("/view/ranger/sightings/:id",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            int idOfRanger= Integer.parseInt(request.params(":id"));
            Rangers foundRanger=Rangers.find(idOfRanger);
            List<Sightings> sightings=foundRanger.getRangerSightings();
            ArrayList<String> animals=new ArrayList<String>();
            ArrayList<String> types=new ArrayList<String>();
            for (Sightings sighting : sightings){
                String animal_name=Animals.find(sighting.getAnimal_id()).getName();
                animals.add(animal_name);

            }
            model.put("sightings",sightings);
            model.put("animals",animals);
            model.put("rangers",Rangers.all());
            return new ModelAndView(model,"Ranger.hbs");
        },new HandlebarsTemplateEngine());


        get("/create/sighting",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("rangers",Rangers.all());
            model.put("locations",Locations.all());
            model.put("animals",Animals.all());
            return new ModelAndView(model,"sightingsForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/sighting/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            int location_id= Integer.parseInt(request.queryParams("location"));
            int ranger_id= Integer.parseInt(request.queryParams("ranger"));
            int animal_id= Integer.parseInt(request.queryParams("animal"));

            Sightings sighting=new Sightings(location_id,ranger_id,animal_id);
            sighting.save();
            return new ModelAndView(model,"sightingsForm.hbs");
        },new HandlebarsTemplateEngine());

        get("/view/sightings",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            List<Sightings> sightings=Sightings.all();
            ArrayList<String> animals=new ArrayList<String>();
            ArrayList<String> types=new ArrayList<String>();
            for (Sightings sighting : sightings){
                String animal_name=Animals.find(sighting.getAnimal_id()).getName();
                animals.add(animal_name);
            }
            model.put("sightings",sightings);
            model.put("animals",animals);
            return new ModelAndView(model,"sightings.hbs");
        },new HandlebarsTemplateEngine());



    }

    }


