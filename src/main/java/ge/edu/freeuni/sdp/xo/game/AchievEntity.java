package ge.edu.freeuni.sdp.xo.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gkiko on 7/16/15.
 */
@XmlRootElement
public class AchievEntity {
    @XmlElement
    @JsonProperty("rank")
    private int rank;

    @XmlElement
    @JsonProperty("score")
    private int score;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
