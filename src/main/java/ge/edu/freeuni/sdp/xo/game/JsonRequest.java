/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.xo.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toka
 */
@XmlRootElement
public class JsonRequest {
	
	@XmlElement
	@JsonProperty("user_id")
	String user_id;

	@XmlElement
	@JsonProperty("cell")
	int cell;

	@Override
	public String toString() {
		return user_id + ", " + cell;
	}

}
