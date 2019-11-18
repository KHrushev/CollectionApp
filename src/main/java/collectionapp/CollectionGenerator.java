package collectionapp;

import java.util.*;

public class CollectionGenerator {
    private final String[] hobbyNames = {"Hunting", "Art", "Chess", "Football", "Basketball"};

    public List<Hobby> generateList(List<Hobby> list) {
        list.clear();
        if (list.getClass().equals(ArrayList.class)) {
            for (int i = 0; i < 1000000; i++) {
                list.add(generateHobby());
            }
        } else if (list.getClass().equals(LinkedList.class)) {
            for (int i = 0; i < 1000000; i++) {
                list.add(generateHobby());
            }
        }

        return list;
    }

    public HashSet<Hobby> generateHashSet() {
        HashSet<Hobby> hashSet = new HashSet<>();

        for (int i = 0; i < 1000000; i++) {
            hashSet.add(generateHobby());
        }

        return hashSet;
    }

    public Map<Integer, Hobby> generateMap(Map<Integer, Hobby> map) {
        map.clear();

        for (int i = 0; i < 1000000; i++) {
            map.put(i, generateHobby());
        }

        return map;
    }

    private Hobby generateHobby(){
        Random random = new Random();
        int nameIndex = random.nextInt(hobbyNames.length);
        int maximumHobbyYears = 20;
        int duration = random.nextInt(maximumHobbyYears);

        return new Hobby(hobbyNames[nameIndex], duration);
    }
}
