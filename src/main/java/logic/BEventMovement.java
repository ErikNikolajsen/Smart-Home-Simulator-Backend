package logic;

import java.time.LocalDateTime;

import entities.Agent;
import pathfinding.AStarNode;

public class BEventMovement extends BEvent {
	
	private Agent agent;
	private AStarNode node;
	
	public BEventMovement(LocalDateTime eventDateTime, Agent agent, AStarNode node) {
		super(eventDateTime);
		this.agent = agent;
		this.node = node;
	}

	public BEventMovement() {
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public AStarNode getNode() {
		return node;
	}

	public void setNode(AStarNode node) {
		this.node = node;
	}
	
}
