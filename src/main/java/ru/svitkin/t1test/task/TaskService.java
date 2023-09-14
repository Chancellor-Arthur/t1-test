package ru.svitkin.t1test.task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
	public Map<Character, Integer> calculate(String input) {
		Map<Character, Integer> symbolFrequency = new HashMap<>();
		for (char symbol : input.toCharArray()) {
			symbolFrequency.put(symbol, symbolFrequency.getOrDefault(symbol, 0) + 1);
		}

		Map<Character, Integer> sortedSymbolFrequency = symbolFrequency.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return sortedSymbolFrequency;
	}
}
