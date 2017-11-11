import javafx.geometry.Point2D;
import util.Coordinates;

public class UseCaseMove {

    private class MovementExample extends CoordinatesExample{

	public enum Direction {
	    UP (0,1),
	    DOWN (0,-1),
	    LEFT (-1, 0),
	    RIGHT (1, 0);
	    
	    private final CoordinatesExample unitMovement;
	    
	    public Direction(double x, double y) {
		unitMovement = new CoordinatesExample(x,y);
	    }
	}

	private Direction direction;
	private double value;

	public MovementExample(Direction givenDirection, double givenValue) {
	    direction = givenDirection;
	    value = givenValue;
	}
	
	
	public void apply(CoordinateExample coordinate) {
	    direction.multiply(value);
	    coordinate.add(direction);
	}
    }

    private class CoordiantesExample { //should really be called VectorExample

	private Point2D coordinates;

	public Coordinates(double x, double y) {
	    coordinates = new Point2D(x,y);
	}
	
	public add(CoordinatesExample operand) {
	    coordinates.add(operand.coordinates);
	}
	
	public void multiply(double factor) {
	    cooridnates = coordinates.multiply(factor);
	}
    }

    private class PlayerBackendExample {
	private CoordinateExample coordinates;

	public PlayerBackendExample(CoordinateExample initialCoordinates){
	    coordinates = initialCoordinates;
	}
	
	public Coordinates move(Movement movement) {
	    movement.apply(coordinates);
	}

    }

    public void run() {
	CoordinateExample initialCoordinates = new CoordianteExample(0,0);
	PlayerBackendExample player = new PlayerBackendExample(initialCoordinates);
	MovementExample movement = new MovementExample(Direction.UP, 50);
	player.move(movement);
    }
}
