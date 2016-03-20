package uk.ac.aber.dcs.cs12320.cards.game;

public class Score {
	
	private String name;
	private int score;
	private long time;
	
	
	public Score(String name, int score, long time) {
		super();
		this.name = name;
		this.score = score;
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [name=" + name + ", score=" + score + ", time=" + time
				+ "]";
	}

	
}
