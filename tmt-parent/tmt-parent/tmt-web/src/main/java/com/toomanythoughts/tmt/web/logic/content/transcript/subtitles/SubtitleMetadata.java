package com.toomanythoughts.tmt.web.logic.content.transcript.subtitles;

import java.util.Arrays;
import java.util.HashMap;

public class SubtitleMetadata extends HashMap<String, String> implements Comparable<SubtitleMetadata> {

	private static final long serialVersionUID = -6379688944982600335L;

	@Override
	public int compareTo(SubtitleMetadata other) {
		int compareTo = this.size() - other.size();
		if (compareTo != 0) {
			return compareTo;
		}
		final String[] thisKeys = (String[]) this.keySet().toArray();
		final String[] otherKeys = (String[]) other.keySet().toArray();
		Arrays.sort(thisKeys);
		Arrays.sort(otherKeys);
		for (int i = 0; i < thisKeys.length; i++) {
			compareTo = thisKeys[i].compareTo(otherKeys[i]);
			if (compareTo != 0) {
				return compareTo;
			}
			compareTo = this.get(thisKeys[i]).compareTo(otherKeys[i]);
			if(compareTo != 0) {
				return compareTo;
			}
		}
		return compareTo;
	}
}
