/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brandon Eugene Walker Implements the functionality of a Markov chain.
 *
 * @version Nov 12, 2018
 *
 */
public class MarkovChain {
	private Map<String, HashMap<String, Integer>> chainStructure;

	public MarkovChain() {
		this.chainStructure = new HashMap<String, HashMap<String, Integer>>();
	}

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

	public String getFirst() {
		ArrayList<String> keySet = new ArrayList<String>(this.chainStructure.keySet());
		int size = keySet.size();
		int dart = (int) (size * Math.random());
		return keySet.get(dart);
	}

	public int getTotalChains(String node) {
		int total = 0;

		HashMap<String, Integer> currentInnerMap = this.chainStructure.get(node);
		for (String currentNode : currentInnerMap.keySet()) {
			total += currentInnerMap.get(currentNode);
		}

		return total;
	}

	public void incrementNode(String node, String innerNode) {
		int innerNodeCount = this.chainStructure.get(node).get(innerNode);
		innerNodeCount++;
		this.chainStructure.get(node).replace(innerNode, innerNodeCount);
	}

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

	public void addOuterNode(String node) {
		if (this.chainStructure.containsKey(node)) {
			this.chainStructure.putIfAbsent(node, new HashMap<String, Integer>());
		} else {
			this.chainStructure.put(node, new HashMap<String, Integer>());
		}

	}

}
