import java.io.*;

public class Spiller implements Serializable {

	String navn; 
	int score;

	public Spiller(String navn, int score) {
		this.navn = navn;
		this.score = score;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNavn() {
		return navn;
	}

	public int getScore() {
		return score;
	}

}