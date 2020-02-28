import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;


public class BallTest extends Application {

	private Circle circle;
	private Rectangle rectangle;
	private Rectangle rectangle2;
	private Rectangle rectangle3;

	private Line line; // flipper venstre 
	private Line line2; // flipper høyre 
	private Line line3; // skrå vegg 
	private Line lineWallLeft;
	private Line lineWallRight;

	//float previousTime;
	//float currentTime = System.currentTimeMillis();

 //    private Node  hero;

	double xValue = 0;
	boolean goNorth, goEast, goSouth, goWest, jump;
	double dx = 180, 	dy = 195;
	double lastPositionX;
	double lastPositionY;

	public void start(Stage primaryStage) {

		Pane pane = new Pane();

		circle = new Circle(100,10,5);
		circle.setFill(Color.BLUE);
		pane.getChildren().add(circle);

		rectangle2 = new Rectangle(0,0,20,200);
		rectangle3 = new Rectangle(150,50,20,200);
		line3 = new Line(170,0,190,20);
		line = new Line(); 
		line2 = new Line(); 
		line2.setStroke(Color.BLUE);
		line.setStroke(Color.BLUE);
		lineWallLeft = new Line(17,150,50,180);
		lineWallRight = new Line(120,180,150,150); 

		
		// System.out.println(currentTime);
		

		pane.getChildren().addAll(rectangle2,rectangle3,line3,line2,line,lineWallLeft,lineWallRight);

		Scene scene = new Scene(pane,350,350);
		primaryStage.setScene(scene);
	

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				switch(event.getCode()) {
					//case UP: 	goNorth = true; break;
					case RIGHT: goEast  = true; break;
					// case DOWN:  goSouth = true; break;
					case LEFT:	goWest  = true; break;
					case SPACE: jump  = true; break;
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				//	case UP: 	goNorth = false;  break;
					case RIGHT: goEast  = false;  break;
				//	case DOWN: 	goSouth = false;  break;
					case LEFT: 	goWest  = false;  break;
					case SPACE: jump  = false;  break;
				}
			}
 
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				//previousTime = currentTime;
				//currentTime = System.currentTimeMillis();

				//float dt = currentTime - previousTime;

				//dy += dt;

				if (jump) {  if(canJump()) dy -= 28; }       
				  	//  System.out.println("Jump");   
				  	//   };

				dy = setGravity(2);
				
				if (touchLeftWall()) 		dx =   0;
				if (touchRightWall())		dx = 200;
				if (touchBounceLine()) 		dx -= 70;
				
				lastPositionY = circle.getCenterY();
				lastPositionX = circle.getCenterX();

				if (collisionWallLeft()) 	dx += 1;
				if (collisionWallRight()) 	dx -= 1;	

				// if (goWest = true) 

				if (touchLeftPaddle())  			  { dy -= 1; dx += 1;   }
				if (touchRightPaddle())				  { dy -= 1; dx -= 1;	}
				if (touchCheck(circle,lineWallLeft))  { dy -= 1; dx += 1;	}
				if (touchCheck(circle,lineWallRight)) {	dy -= 1; dx -= 1;	}
				
				if (touchLeftPaddle() && goWest == true) {
					dx += 40;
					dx += (Math.random())*25;
					dy -= 80;
					dy -= (Math.random())*25;
				}

				if (touchRightPaddle() && goEast == true) {
					// dx -= 100;
					dx -= 40;
					dy -= 80;
				}

				if (goWest)  paddleLeft(true);
				if (!goWest) paddleLeft(false); 
				if (goEast)  paddleRight(true);	
				if (!goEast)
				 paddleRight(false);
				
				circle.setCenterY(dy);
				circle.setCenterX(dx);
			}
		};
		timer.start();

		primaryStage.show();

		// rectangle som endrer x og y 
		// Line som endrer x og y, startpunkt og x og y sluttpunkt, if intersects sprett 

	}

	public boolean canJump() {
		boolean statement = false;
		 if (dy>0)
		 statement = true;
		return statement;
	}

	public boolean touchLeftPaddle() {
		boolean statement = false;
		
		if (circle.getBoundsInParent().intersects(line.getBoundsInParent()))
		statement = true;

		return statement; 


	}

	public boolean touchRightPaddle() {
		boolean statement = false;
				if (circle.getBoundsInParent().intersects(line2.getBoundsInParent())) {
					// dx -= 10;
					// dy -= 150;
					statement = true;
				}
				return statement;

	}

	public boolean touchBounceLine() {
		boolean statement = false;
		if (circle.getBoundsInParent().intersects(line3.getBoundsInParent())) {
					// dx -= 70;
				statement = true;
				}
		 	return statement;
	}

	public double setGravity(double value) {

		// double dy = 0;

		if ((circle.getCenterY() + circle.getRadius()) <= 200) {
						dy += value;
				} // gravity
		return dy;
	}

	public boolean touchLeftWall() {
		boolean statement = false;
		if (dx < 0)
			statement = true;
		return statement;
	}


	public boolean touchRightWall() {
		boolean statement = false;
		if (dx > 200)
			statement = true;
		return statement;
		// dx = 200;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean collisionWallLeft() {

		boolean collision = false;

			if (circle.getBoundsInParent().intersects(rectangle2.getBoundsInParent())) 
				collision = true;
		return collision;
	}

	public boolean collisionWallRight() {

		boolean collision = false;

			if (circle.getBoundsInParent().intersects(rectangle3.getBoundsInParent()))
				collision = true;
		return collision;
	}

	public boolean touchCheck(Shape shape, Shape shape2) {
		boolean collision = false;
		if (shape.getBoundsInParent().intersects(shape2.getBoundsInParent()))
				collision = true;
		return collision;
	}

	public void paddleLeft(boolean value) {
		 		if (value) { // 80,180,60,200
				 	line.setStartX(50);
				 	line.setStartY(180);
					line.setEndX(75);
					line.setEndY(180);
				} // 60,180,80,200
				if (!value) {
					line.setStartX(50);
					line.setStartY(180);
					line.setEndX(70); // 60,180,80,200
					line.setEndY(200);
				}  
	}
	public void paddleRight(boolean value) {
				if (value) {
					line2.setStartX(95);
					line2.setStartY(180);
					line2.setEndX(120);
					line2.setEndY(180);
				}
				if(!value) {
					line2.setStartX(100);
					line2.setStartY(200);
					line2.setEndX(120);
					line2.setEndY(180);
				} // 20,180,40,180
	}
}