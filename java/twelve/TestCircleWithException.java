public class TestCircleWithException {
    public static void main(String[] args){
        try{
            CircleWithException c1 = new CircleWithException(5);
            CircleWithException c2 = new CircleWithException(-5);
            CircleWithException c3 = new CircleWithException(0);
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }

        System.out.println("Number of objects created: " +
                CircleWithException.getNumberOfObjects());
    }
}

class CircleWithException{
    private double radius;

    private static int numberOfObjects =0;

    public CircleWithException(){
        this(1.0);
    }

    public CircleWithException(double newRadius){
        setRadius(newRadius);
        numberOfObjects++;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double newRadius)
        throws IllegalArgumentException{
        if(newRadius >=0)
            radius = newRadius;
        else
            throw new IllegalArgumentException(
            "Radius cannot be negative");
    }

    public static int getNumberOfObjects(){
        return numberOfObjects;
    }

    public double findArea(){
        return radius*radius*3.14159;
    }
}























