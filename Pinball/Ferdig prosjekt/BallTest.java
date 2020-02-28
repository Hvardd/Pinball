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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BallTest extends Application {

		// Poengberegning
		int score;
		int lives = 3;

		// Vectorer for fysikk
		PVector location;
		PVector velocity; 
		PVector acceleration;
		PVector friction;

		// Boolean for tastetrykk 
		boolean goNorth, goEast, goSouth, goWest, jump;

		// Pinball 
		Circle circleForce; // dummy 
		Circle circle;

		// Flippere
		Line flipperLeft;
		Line flipperRight;

		// Nederste skråvegg
		Line slopeLeft;
		Line slopeRight;

		// Vegg
		Line wallLeft;
		Line wallRight;

		// Vegg topp høyre 
		Line wallTopRight;

		// Label for å telle poeng og liv 
		Label counterLifeScore;
		// Label livesLabel;
		// TextField scoreCounter;

		// Bouncere i spillbrettet 
		Circle bouncer1;
		Circle bouncer2;
		Circle bouncer3;

		// String for å beregne poeng og liv 
	    String scoreString = "0";  // dummy 
	    String livesString = "3";

	    // Masse kalkulasjon ball // , ball kan enten bli stor eller liten i spill 
		float mass = (Math.random() <= 0.5) ? 1 : 2;
		float frictionValue = -0.1f;

		// Vector for gravitasjon og vind 
		PVector gravity;
		PVector wind; // dummy 

		// Høyde og bredde på spillbrettet
		final float HEIGHT = 750;
		final float WIDTH = 750;

		Circle[] circleArray = new Circle[5]; // dummy

		Pane pane;
		Scene scene;


	@Override
	public void start(Stage primaryStage) {

		// Spillpanel 
		pane = new Pane();
		scene = new Scene(pane,HEIGHT,WIDTH);

		// Pinball 
	    circle = new Circle(); // ball 
	    circle.setRadius(mass*10);
		circle.setFill(Color.BLUE);
		
		// Flippere 
		flipperLeft = new Line();
		flipperRight = new Line();

		// Bouncere
		bouncer1 = new Circle(WIDTH*0.40, HEIGHT*0.20, HEIGHT*0.05);
		bouncer1.setFill(Color.LIGHTBLUE);
		bouncer2 = new Circle(WIDTH*0.60, HEIGHT*0.40, HEIGHT*0.03);
		bouncer2.setFill(Color.LIGHTBLUE);
		bouncer3 = new Circle(WIDTH*0.40, HEIGHT*0.60, HEIGHT*0.01);
		bouncer3.setFill(Color.LIGHTBLUE);

		// Liv og poeng label 
		//livesLabel = new Label(livesString);
		counterLifeScore = new Label("Score: " + scoreString + " Lives: " + livesString);

		// livesLabel.setAlignment(Pos.TOP);
		// scoreCounter = new TextField(scoreString);

		// Vegger
		wallLeft  = new Line(WIDTH*0.20, HEIGHT*0, WIDTH*0.20, HEIGHT*0.60);
		wallRight = new Line(WIDTH*0.80, HEIGHT*0.60, WIDTH*0.80, HEIGHT*0.20);

		wallTopRight = new Line(WIDTH*0.90, HEIGHT*0.05, WIDTH*0.95, HEIGHT*0.10);

		slopeLeft  = new Line(WIDTH*0.20,HEIGHT*0.60,WIDTH*0.30,HEIGHT*0.80);
		slopeRight = new Line(WIDTH*0.70,HEIGHT*0.80,WIDTH*0.80,HEIGHT*0.60);

		// Vectorer, startposisjon ball
		location = new PVector(WIDTH*0.925f,HEIGHT+(float)circle.getRadius());  // x and y 
		velocity = new PVector(0,0);   // x and y 
		acceleration = new PVector(0,0);
		friction = new PVector(0,0);

		gravity = new PVector(0,0.3f);
		wind = new PVector(-0.2f,0.0f); // dummy 

		// Tastetrykk registrering, true or false 
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

		// Registrere når tastetrykk blir sluppet 
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

		/* for (int i=0; i<circleArray.length; i++) {
			circleArray[i] = new Circle(HEIGHT,WIDTH,mass);
			circleArray[i].setRadius(mass*10);
			circle.setFill(Color.GREEN);
			pane.getChildren().add(circleArray[i]);
		} */ 

		// Vector multiplikasjon
		gravity.multiply(mass);

		// Legger til alle nodene i spillbrettet 
		pane.getChildren().add(bouncer1);
		pane.getChildren().add(bouncer2);
		pane.getChildren().add(bouncer3);

		// applyForce
		pane.getChildren().add(slopeLeft);
		pane.getChildren().add(slopeRight);

		pane.getChildren().add(wallLeft);
		pane.getChildren().add(wallRight);

		pane.getChildren().add(wallTopRight);
	 
		pane.getChildren().add(flipperLeft);
		pane.getChildren().add(flipperRight);
		pane.getChildren().add(circle);

		// pane.getChildren().add(livesLabel);
		pane.getChildren().add(counterLifeScore);

		primaryStage.setScene(scene);
		primaryStage.show();
		

		 AnimationTimer animationTimer = new AnimationTimer() {
		 	@Override 
		 	public void handle(long now) {
 
		 		// applyForce(gravity);
		 		// applyForce(wind);

		 		// dummy 
		 		pane.setOnMousePressed(e-> {
		 			location.setX((float)e.getX());
		 			location.setY((float)e.getY());
		 		});

		 		// Brukes for å starte spill, ved trykk på SPACE 
		 		if(jump) {
		 			velocity.setY(-30);
		 		}

		 	    applyForce(gravity,mass);
		 	    
		 	  //   applyForce(wind,mass);


		 	    // applyForce(wind,mass);
		 		velocity.add(acceleration);
		 		location.add(velocity);
		 		acceleration.multiply(0);

		 		
		 		// PVector friction = new PVector(0,0);
		 		friction.setX(velocity.getX());
		 		friction.setY(velocity.getY());
		 		friction.normalize(); // value can be changed 
		 		friction.multiply(frictionValue);
		 		applyForce(friction,mass);
		 		

		 		/* 
		 		PVector drag = new PVector(0,0);
		 		drag.setX(velocity.getX());
		 		drag.setY(velocity.getY());
		 		drag.normalize();
		 		float c = -0.03f;
		 		float speed = velocity.getLength();

		 		drag.multiply(c*speed*speed);
		 		applyForce(drag,mass); 
		 		*/ 

		 		// Blir gjort basert på boolean verdier, som kommer av tastetrykk 
		 	    if (goWest)  paddleLeft(true);
				if (!goWest) paddleLeft(false); 
				if (goEast)  paddleRight(true);	
				if (!goEast) paddleRight(false);
		 		 
		 		// Veggkollisjonregistrering 
		 		touchWallLeftCheck();
		 		touchWallRightCheck();
		 		touchTopRightWallCheck();

		 		// Flipperkollisjon registrering 
		 		touchPaddleLeft();
		 		touchPaddleRight();

		 		// Bouncer kollisjon registrering 
		 		touchBouncerCheck();

		 		// Lukker vegg når ball er i spillområdet
		 		closeWalls();

		 		// Sjekker om ball er innenfor pane 
		 		edge();

		 		// bounce();

		 		// Resetter ball når den er utenfor spill, og trekker fra et liv 	
		 	    resetBall();

		 		// Oppdaterer lokasjon på ball 
		 		circle.setCenterX(location.getX());
		 	    circle.setCenterY(location.getY());


		 	}
		 };
		 animationTimer.start();

	}

	// Sjekker om ball treffer flipper venstre
	public void touchPaddleLeft() {
		if (circle.getBoundsInParent().intersects(flipperLeft.getBoundsInParent()) && goWest == true) {
			velocity.setY(-16);
			velocity.add((Math.random() <= 0.5) ? 1 : 2,0);
		}
	}

	// Sjekker om ball treffer flipper høyre 
	public void touchPaddleRight() {
		if (circle.getBoundsInParent().intersects(flipperRight.getBoundsInParent()) && goEast == true) {
			velocity.setY(-16);
			velocity.add((Math.random() <= 0.5) ? -1 : -2,0);
		}
		
	}

	// Sjekker om ball treffer bouncere 
	public void touchBouncerCheck() {
		if(circle.getBoundsInParent().intersects(bouncer1.getBoundsInParent()) ||
			circle.getBoundsInParent().intersects(bouncer2.getBoundsInParent()) ||
			circle.getBoundsInParent().intersects(bouncer3.getBoundsInParent())) {
			velocity.setX(velocity.getX() * 1.05f);
			velocity.setX(velocity.getX() * -1);
			score += 20;
			counterLifeScore.setText("Score: " + Integer.toString(score) + " Lives: " + Integer.toString(lives));
			// scoreString.setLabelFor(score);
			// scoreString = Integer.toString(score);

		}
	}

	// Sjekker om ball treffer vegg ventre 
	public void touchWallLeftCheck() {

		if(circle.getBoundsInParent().intersects(wallLeft.getBoundsInParent())||
		  circle.getBoundsInParent().intersects(slopeLeft.getBoundsInParent())||
		   circle.getBoundsInParent().intersects(flipperLeft.getBoundsInParent())) {
			   
			  //location.setX(location.getX() + 1); // = WIDTH;
			  velocity.setX(velocity.getX() * -1);
			  velocity.setY(-1);
			  velocity.setX(+7);
     		 //  velocity.setX(velocity.getX() * -1); 
			 
		}
	}

	// Lukker vegg etter at ball er inne i spillbrettet 
	public void closeWalls() {
		if(circle.getCenterX() > WIDTH * 0.20 && circle.getCenterX() < WIDTH * 0.80) {
			wallLeft.setStartY(0);
			wallRight.setEndY(0);
		}
		else 
			wallRight.setEndY(HEIGHT*0.20);
	}

	// Sjekker om ball treffer vegg høyre 
	public void touchWallRightCheck() {

		if(circle.getBoundsInParent().intersects(wallRight.getBoundsInParent())||
		  circle.getBoundsInParent().intersects(slopeRight.getBoundsInParent())||
		   circle.getBoundsInParent().intersects(flipperRight.getBoundsInParent())) {
			
			//  location.setX(location.getX() - 1); // = WIDTH;
			 velocity.setX(velocity.getX() * -1); 
			 velocity.setY(-1);
			 velocity.setX(-7);
  
		}
	}

	// Sjekker om ball treffer skråvegg topp høyre utenfor spillbrett 
	public void touchTopRightWallCheck() {
		if (circle.getBoundsInParent().intersects(wallTopRight.getBoundsInParent())) {

			//  location.setX(location.getX() - 1); // = WIDTH;
     	    //  velocity.setX(velocity.getX() * -1); 
     	    velocity.normalize();
     	    velocity.setX(-12);
     	    // velocity.setY(velocity.getY() + 1);
		}
	}

	// Sjekker om ballen er utenfor pane 
	public void edge() {
			// void checkEdges() {
    if (location.getX() > WIDTH) {
     	 location.setX(WIDTH); // = WIDTH;
     	 velocity.setX(velocity.getX() * -1); 
    } else if (location.getX() < 0) {
      velocity.setX(velocity.getX() * -1); // *= -1;
      location.setX(0);//  = 0;
    }
 
    if (location.getY() > HEIGHT) {
    	 velocity.setY(velocity.getY() * -1); //*= -1;
     	 location.setY(HEIGHT); // = HEIGHT;
 	     
     	//  velocity.normalize();

     	  // WIDTH*0.925f,HEIGHT+(float)circle.getRadius()
	}
}

	// Resetter ballen når den treffer bakken 
	public void resetBall() {
		if (location.getY() > HEIGHT*0.95 && location.getX() < WIDTH * 0.80 && location.getX() > WIDTH * 0.20) {
		   location.setX(WIDTH*0.925f);
		   velocity.setX(0);
		   lives -= 1;
		   counterLifeScore.setText("Score: " + Integer.toString(score) + " Lives: " + Integer.toString(lives));
		   if (lives <= 0) {
		   	 pane.getChildren().remove(circle);
		    }
		   }
     	   // location.setY(HEIGHT+(float)circle.getRadius());
	}
	// dummy 
	public void addSeveralCircles() {
/* 
		location = new PVector(WIDTH/2,HEIGHT/2);  // x and y 
		velocity = new PVector(0,0);   // x and y 
		acceleration = new PVector(0,0);	
		*/

	}

	// dummy 
	public void bounce() {

	if ((location.getX() > WIDTH) || (location.getX() < 0)) {
		 	velocity.setX(velocity.getX() * -1 ); 
		}

    if ((location.getY() > HEIGHT) || (location.getY() < 0)) {
			velocity.setY(velocity.getY() * -1 );		 	
		}

	}

	// Legger til kraft på vector 
	public void applyForce(PVector force, float mass) {
		PVector f = new PVector(0,0);
		f.div(force,mass);
		acceleration.add(f);
	}

	// Legger til kraft på vector, metode overloading
	public void applyForce(float value) {
		acceleration.add(value);
	}

	// Legger til kraft på vector, metode overloading 
	public void applyForce(float x, float y) {
		acceleration.add(x,y);
	}

	// Returnerer flipperverdi, en på true, og annen på false med forskjellig setEndY() verdi
	public void paddleLeft(boolean value) {
		 		if (value) { // 80,180,60,200
				 	flipperLeft.setStartX(WIDTH*0.30);
				 	flipperLeft.setStartY(HEIGHT*0.8);
					flipperLeft.setEndX(WIDTH*0.40);
					flipperLeft.setEndY(HEIGHT*0.80);  // Up motion 
				} // 60,180,80,200
				if (!value) {
					flipperLeft.setStartX(WIDTH*0.30);
					flipperLeft.setStartY(HEIGHT*0.8);
					flipperLeft.setEndX(WIDTH*0.40); // 60,180,80,200
					flipperLeft.setEndY(HEIGHT*0.85);
				}  
	}

	// Returnerer flipperverdi, en på true, og en annen på false med forskjellig setStartY() verdi
	public void paddleRight(boolean value) {
				if (value) {
					flipperRight.setStartX(WIDTH*0.60);
					flipperRight.setStartY(HEIGHT*0.8); // Up motion
					flipperRight.setEndX(WIDTH*0.70);
					flipperRight.setEndY(HEIGHT*0.80);
				}
				if(!value) {
					flipperRight.setStartX(WIDTH*0.60);
					flipperRight.setStartY(HEIGHT*0.85);
					flipperRight.setEndX(WIDTH*0.70);
					flipperRight.setEndY(HEIGHT*0.80);
				} // 20,180,40,180
	}




	/* public void setGravity() {
		if (xSpeed > 1) {
			xSpeed -= 0.1;
		}
		if (ySpeed > 1) {
			ySpeed -= 1;
		}

		if (y < HEIGHT) {
			y -= xSpeed;
		}

	} */ 

    public static void main(String[] args) {

	launch(args);

	} 


}