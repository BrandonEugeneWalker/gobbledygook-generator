/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon Eugene Walker Implements the functionality of a Markov chain.
 *
 * @version Nov 15, 2018
 *
 */
public class MarkovChain {
	private Map<String, HashMap<String, Integer>> chainStructure;

	/**
	 * Creates a new instance of a MarkovChain.
	 */
	public MarkovChain() {
		this.chainStructure = new HashMap<String, HashMap<String, Integer>>();
	}

	
	/**
	 * Gets and returns a calculated next word.
	 * @param node the node to calculate off of
	 * @return the next node
	 */
	public String getNext(String node) {
		int accumulation = 0;
		int totalChains = this.getTotalChains(node);
		int dart = (int) (totalChains * Math.random());

		HashMap<String, Integer> innerNodes = this.chainStructure.get(node);
		for (String currentInnerNode : innerNodes.keySet()) {
			accumulation += innerNodes.get(currentInnerNode);
			if (accumulation >= dart) {
				return currentInnerNode;
			}
		}
		return ".";
	}

	
	/**
	 * Returns a randomly selected first word from the outer nodes.
	 * @return a random node
	 */
	public String getFirst() {
		//ArrayList<String> keySet = new ArrayList<String>(this.chainStructure.keySet());
		//int size = keySet.size();
		//int dart = (int) (size * Math.random());
		//return keySet.get(dart);
		return "the";
	}

	
	/**
	 * Gets the total number of chain hits.
	 * @param node the node to check the inner hits on
	 * @return the number of total hits
	 */
	public int getTotalChains(String node) {
		int total = 0;

		HashMap<String, Integer> currentInnerMap = this.chainStructure.get(node);
		for (String currentNode : currentInnerMap.keySet()) {
			total += currentInnerMap.get(currentNode);
		}

		return total;
	}

	
	/**
	 * Increments the inner nodes hit count
	 * @param node the outter node
	 * @param innerNode the inner node
	 */
	public void incrementNode(String node, String innerNode) {
		int innerNodeCount = this.chainStructure.get(node).get(innerNode);
		innerNodeCount++;
		this.chainStructure.get(node).replace(innerNode, innerNodeCount);
	}

	/**
	 * Adds an inner node to the chain, if either of the parameters are missing they are added as outer nodes.
	 * @param node the outer node
	 * @param innerNode the inner node
	 */
	public void addInnerNode(String node, String innerNode) {
		if (!this.chainStructure.containsKey(node)) {
			this.addOuterNode(node);
		}
		if (!this.chainStructure.containsKey(innerNode)) {
			this.addOuterNode(innerNode);
		}
		if (this.chainStructure.get(node).containsKey(innerNode)) {
			this.incrementNode(node, innerNode);
		} else {
			this.chainStructure.get(node).put(innerNode, 1);
		}
	}

	/**
	 * Adds an outer node to the chain.
	 * @param node the node
	 */
	public void addOuterNode(String node) {
		if (this.chainStructure.containsKey(node)) {
			this.chainStructure.putIfAbsent(node, new HashMap<String, Integer>());
		} else {
			this.chainStructure.put(node, new HashMap<String, Integer>());
		}

	}

}
