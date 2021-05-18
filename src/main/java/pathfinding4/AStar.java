package pathfinding4;
/*
package pathfinding;

import java.util.ArrayList;
import pathfinding.Network;
import pathfinding.Node;

public class AStar {

    private Network network;
    private ArrayList<Node> path;

    private Node start;
    private Node end;

    private ArrayList<Node> openList; // the set of nodes to be evaluated
    private ArrayList<Node> closedList; // the set of nodes already evaluated

    public AStar(Network network) {
        this.network = network;
    }

    public void solve() {
    	
    	// return if start and end are null
        if (start == null && end == null) {
            return;
        }
        
        // return empty path list if start equals end
        if (start.equals(end)) {
            this.path = new ArrayList<>();
            return;
        }
        
        // initialize empty lists
        this.path = new ArrayList<>();
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        
        // add the start node to the OPEN list
        this.openList.add(start); 

        // loop until OPEN in empty
        while (!openList.isEmpty()) {
        	
        	// set the CURRENT node to be the node in OPEN with lowest f_cost
            Node current = getLowestF();
            
            // if CURRENT is the target node the path has been found
            if (current.equals(end)) {
                retracePath(current);
                break;
            }
            
            // remove CURRENT from OPEN
            openList.remove(current);
            // add CURRENT to CLOSED
            closedList.add(current);
            
            // foreach neighbour of the CURRENT node
            for (Node n : current.getNeighbours()) {
            	
            	// if neighbour is in CLOSED or is not traversable then skip to the next neighbour
                if (closedList.contains(n) || !n.isValid()) {
                    continue;
                }
                
                // calculate new f_cost of neighbour
                double tempScore = current.getCost() + current.distanceTo(n);
                
                // if 
                if (openList.contains(n)) {
                    if (tempScore < n.getCost()) {
                        n.setCost(tempScore);
                        n.setParent(current);
                    }
                } else {
                    n.setCost(tempScore);
                    openList.add(n);
                    n.setParent(current);
                }

                n.setHeuristic(n.heuristic(end));
                n.setFunction(n.getCost() + n.getHeuristic());

            }

        }

        //updateUI(); 
    }

    public void reset() {
        this.start = null;
        this.end = null;
        this.path = null;
        this.openList = null;
        this.closedList = null;
        for (Node n : network.getNodes()) {
            n.setValid(true);
        }
    }

    private void retracePath(Node current) {
        Node temp = current;
        this.path.add(current);
        
        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }
        
        this.path.add(start);
    }

    private Node getLowestF() {
        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getFunction()< lowest.getFunction()) {
                lowest = n;
            }
        }
        return lowest;
    }

    public Network getNetwork() {
        return network;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

}
*/