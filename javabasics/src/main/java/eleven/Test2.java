package eleven;

public class Test2 {
    public static void main(String[] args){
        new Persons().printPerson();
        new Students().printPerson();
    }
}

class Students extends Persons{
    @Override
    public String getInfo(){
        return "Student";
    }
}

class Persons{
    public String getInfo(){
        return "Person";
    }
    public void printPerson(){
        System.out.println(getInfo());
    }
}
