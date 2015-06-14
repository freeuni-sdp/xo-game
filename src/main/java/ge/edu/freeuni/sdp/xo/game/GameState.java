/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.xo.game;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toka
 */
@XmlRootElement
public class GameState {
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_RUNNING = 1;
    public static final int STATUS_FINISHED = 2;
    
    
    @XmlElement
    public int status;
    
    @XmlElement
    public List<Integer> table;
    
    @XmlElement
    public String winner;
    
}
