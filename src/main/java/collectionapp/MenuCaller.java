package collectionapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.exit;

public class MenuCaller {
    private ArrayList<Hobby> arrayList = new ArrayList<>();
    private LinkedList<Hobby> linkedList = new LinkedList<>();
    private HashSet<Hobby> hashSet = new HashSet<>();
    private HashMap<Integer, Hobby> hashMap = new HashMap<>();
    private TreeMap<Integer, Hobby> treeMap = new TreeMap<>();
    private String[] names = {"ArrayList", "LinkedList", "HashSet", "HashMap", "TreeMap"};

    public void callMain() {
        System.out.println("Main menu, choose one of the options:");
        System.out.println("1. ArrayList\n2. LinkedList\n3. HashSet\n4. HashMap\n5. TreeMap\n6. Statistics\n7. Exit");

        chooseSubMenu(getUserChoice(7));
    }

    private void chooseSubMenu(int index) {
        switch (index) {
            case 1:
                callSubMenu(arrayList);
                break;
            case 2:
                callSubMenu(linkedList);
                break;
            case 3:
                callSubMenu(hashSet);
                break;
            case 4:
                callSubMenu(hashMap);
                break;
            case 5:
                callSubMenu(treeMap);
                break;
            case 6:
                getStatistics();
                break;
            case 7:
                System.out.println("Exiting the program..");
                exit(0);
        }
    }

    private void chooseSubMenu(int choice, List<Hobby> list) {
        switch (choice) {
            case 1:
                System.out.println("Enter index:");
                System.out.println(list.get(getUserChoice(1000000)));
                callSubMenu(list);
            case 2:
                System.out.println(list.size());
                callSubMenu(list);
            case 3:
                callMain();
            case 4:
                System.out.println("Exiting the program..");
                exit(0);
        }
    }

    private void chooseSubMenu(int choice, Set<Hobby> set) {
        switch (choice) {
            case 1:
                int count = 0;
                System.out.println("Enter Hobby name:");
                String itemName = getUserChoice();
                System.out.println("Enter years spent:");
                int itemYears = getUserChoice(100);
                for (Object o : set) {
                    Hobby h = (Hobby) o;
                    if (h.equals(new Hobby(itemName, itemYears))) {
                        System.out.println("HashSet contains given hobby. It's hashCode is - " + h.hashCode());
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println("HashSet does not contain given hobby.");
                }

                callSubMenu(set);
            case 2:
                System.out.println(set.size());
                callSubMenu(set);
            case 3:
                callMain();
            case 4:
                System.out.println("Exiting the program..");
                exit(0);
        }
    }

    private void chooseSubMenu(int choice, Map<Integer, Hobby> map) {
        switch (choice) {
            case 1:
                System.out.println("Enter key:");
                int key = getUserChoice(1000000);
                System.out.println(map.get(key));
                callSubMenu(map);
            case 2:
                System.out.println(map.size());
                callSubMenu(map);
            case 3:
                callMain();
            case 4:
                System.out.println("Exiting the program..");
                exit(0);
        }
    }

    private void callSubMenu(List<Hobby> collection){
        CollectionGenerator generator = new CollectionGenerator();
        collection = generator.generateList(collection);
        System.out.println("What would you like to do with this list ?");
        System.out.println("1. Get Hobby\n2. Get Size\n3. Go back\n4. Exit");

        chooseSubMenu(getUserChoice(4), collection);
    }

    private void callSubMenu(Set<Hobby> set) {
        CollectionGenerator generator = new CollectionGenerator();
        set = generator.generateHashSet();
        System.out.println("What would you like to do with this list ?");
        System.out.println("1. Check whether set contains given hobby\n2. Get Size\n3. Go back\n4. Exit");

        chooseSubMenu(getUserChoice(4), set);
    }

    private void callSubMenu(Map<Integer, Hobby> map) {
        CollectionGenerator generator = new CollectionGenerator();
        map = generator.generateMap(map);
        System.out.println("What would you like to do with this list ?");
        System.out.println("1. Get Hobby by Key\n2. Get Size\n3. Go back\n4. Exit");

        chooseSubMenu(getUserChoice(4), map);
    }

    private int getUserChoice(int upperBoundary) {
        int choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            choice = Integer.parseInt(reader.readLine());
            if (choice > upperBoundary || choice <= 0) {
                System.out.println("Try again with integer > 0 and < " + upperBoundary);
                getUserChoice(upperBoundary);
            } else {
                return choice;
            }
        } catch (IOException ioe) {
            System.out.println("Entered value has to be greater than zero and lesser than 7.");
            callMain();
        } catch (NumberFormatException nfe) {
            System.out.println("You have to enter integer value.");
            callMain();
        }

        return -1;
    }

    private String getUserChoice() {
        String choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            choice = reader.readLine();
            return choice;
        } catch (IOException ioe) {
            System.out.println("Entered value has to be of string type.");
            callMain();
        } catch (NumberFormatException nfe) {
            System.out.println("You have to enter string value.");
            callMain();
        }

        return "";
    }

    private void getStatistics() {
        long[] addTimes = getAddTime();
        long[] accessTimes = getAccessTime();
        long[] removeTimes = getRemoveTime();
        System.out.println(String.format("%-15s%-10s%-10s%-10s", "", "Add", "Access", "Remove"));
        for (int i = 0; i < addTimes.length; i++) {
            System.out.println(String.format("%-15s%-10d%-10d%-10d", names[i], addTimes[i], accessTimes[i], removeTimes[i]));
        }
        callMain();
    }

    private long[] getAddTime() {
        CollectionGenerator generator = new CollectionGenerator();

        int i = 0;
        long[] addTimes = new long[names.length];

        long start = System.currentTimeMillis();
        arrayList = (ArrayList<Hobby>) generator.generateList(new ArrayList<Hobby>());
        long curr = System.currentTimeMillis() - start;
        addTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        linkedList = (LinkedList<Hobby>) generator.generateList(new LinkedList<Hobby>());
        curr = System.currentTimeMillis() - start;
        addTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        hashSet = generator.generateHashSet();
        curr = System.currentTimeMillis() - start;
        addTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        hashMap = (HashMap<Integer, Hobby>) generator.generateMap(new HashMap<Integer, Hobby>());
        curr = System.currentTimeMillis() - start;
        addTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        treeMap = (TreeMap<Integer, Hobby>) generator.generateMap(new TreeMap<Integer, Hobby>());
        curr = System.currentTimeMillis() - start;
        addTimes[i] = curr;

        return addTimes;
    }

    private long[] getRemoveTime() {
        int i = 0;
        long[] removeTimes = new long[names.length];

        long start = System.currentTimeMillis();
        arrayList.clear();
        long curr = System.currentTimeMillis() - start;
        removeTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        linkedList.clear();
        curr = System.currentTimeMillis() - start;
        removeTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        hashSet.clear();
        curr = System.currentTimeMillis() - start;
        removeTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        hashMap.clear();
        curr = System.currentTimeMillis() - start;
        removeTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        treeMap.clear();
        curr = System.currentTimeMillis() - start;
        removeTimes[i] = curr;

        return removeTimes;
    }

    private long[] getAccessTime() {
        String[] hobbyNames = {"Hunting", "Art", "Chess", "Football", "Basketball"};
        Random random = new Random();
        int i = 0;
        int sum = 0;
        long[] accessTimes = new long[names.length];

        long start = System.currentTimeMillis();
        sum += arrayList.get(random.nextInt(1000000)).getYearsSpent();
        long curr = System.currentTimeMillis() - start;
        accessTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        sum += linkedList.get(random.nextInt(1000000)).getYearsSpent();
        curr = System.currentTimeMillis() - start;
        accessTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        for (Hobby hobby : hashSet) {
            sum += hobby.getYearsSpent();
        }
        curr = System.currentTimeMillis() - start;
        accessTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        sum += hashMap.get(random.nextInt(1000000)).getYearsSpent();
        curr = System.currentTimeMillis() - start;
        accessTimes[i] = curr;
        i++;

        start = System.currentTimeMillis();
        sum = treeMap.get(random.nextInt(1000000)).getYearsSpent();
        curr = System.currentTimeMillis() - start;
        accessTimes[i] = curr;

        return accessTimes;
    }
}
