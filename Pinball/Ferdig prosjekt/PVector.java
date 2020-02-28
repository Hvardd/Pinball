// Vector klasse 

public class PVector {
	float x;
	float y;

// Konstruktør metode 
	public PVector(float x, float y) {
	this.x = x;
	this.y = y;
	}

// Set metoder 
	public void setX(float x) {
	this.x = x;
	}

	public void setY(float y) {
	this.y = y;
	}

// Get metoder
	public float getX() {
	return x;
	}

	public float getY() {
	return y;
	}

// Addisjon, med method overloading 
	public void add(PVector p) {
		x += p.getX();
		y += p.getY();
	}

	public void add(float value) {
		this.x += value;
		this.y += value;
	}

	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
 
// Substraksjon med method overloading 
	public void subtract(float x, float y) {
		this.x -= x;
		this.y -= y;	
	}

	public void subtract(PVector p) {
		x -= p.getX();
		x -= p.getY();
	}

// Multiplikasjon med method overloading
	public void multiply(float value1, float value2) {
		x *= value1;
		y *= value2;
	}

	public void multiply(float value) {
		x *= value;
		y *= value;
	}

	public void multiply(PVector p) {
		x *= p.getX();
		x *= p.getY();
	}

// Finne lengden av en vector 
	public float getLength() { // getMagnitude 
		float magnitude = (float)Math.sqrt(x*x+y*y);
		return magnitude;

		// Math.sqrt(x*x+y*y);  
	}

// Sette lengden 
	public void setMagnitude(float length) {
		normalize();
		multiply(length);
	}

// Divisjon med method overloading
	public void div(PVector p, float mass) {
		x = p.getX() / mass;
		y = p.getY() / mass;
	}

	public void div(PVector p) {
		x /= p.getX();
		y /= p.getY();
	}

	public void div(float value) {
		x /= value;
		y /= value;
	}

// Normalisering av vector, med og dele lengden på seg selv
	public void normalize() { 
		/* if (m != 0 && m != 1) {
      div(m);
    } */ 
		float magnitude = getLength();
		if (magnitude != 0 && magnitude != 1) {
			div(magnitude);
		}
	}
}