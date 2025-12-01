import java.util.*;


class Guest {
    private String name;
    private String id;
    private ArrayList<Integer> activityLog;



    public Guest(String name, String id) {
        this.name = name;
        this.id = id;
        this.activityLog = new ArrayList<>();


    }
    public void addActivity(int activity) {
        activityLog.add(activity);

    }

    public int getActivityCount() {
        return activityLog.size();


    }

    public boolean isSuspicious() {
        return getActivityCount() > 10;

    }

    public void printActivities() {
        System.out.println("Activity log for " + name + " (" + id + "):");
        for (int a : activityLog) {
            System.out.print((a == 1 ? "Check-in " : "Check-out ") + "| ");
        }
        System.out.println("\nTotal Activities: " + getActivityCount());

        if (isSuspicious()) {

            System.out.println("⚠️ Suspicious activity detected!");
        }

    }

    public void resetActivities() {
        activityLog.clear();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class HotelSystem {
    private Map<String, Guest> guests = new HashMap<>();
    private Scanner sc = new Scanner(System.in);
    public void checkIn() {
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();

        System.out.print("Enter guest ID: ");
        String id = sc.nextLine();

        guests.putIfAbsent(id, new Guest(name, id));
        guests.get(id).addActivity(1);

        System.out.println(name + " checked in.");
        if (guests.get(id).isSuspicious()) {
            System.out.println("⚠️ Suspicious activity detected!");
        }
    }

    public void checkOut() {
        System.out.print("Enter guest ID: ");
        String id = sc.nextLine();

        Guest guest = guests.get(id);
        if (guest != null) {
            guest.addActivity(0);
            System.out.println(guest.getName() + " checked out.");
            if (guest.isSuspicious()) {
                System.out.println("⚠️ Suspicious activity detected!");
            }
        } else {
            System.out.println("Guest not found.");
        }
    }

    public void viewGuestDetails() {
        System.out.print("Enter guest ID: ");
        String id = sc.nextLine();

        Guest guest = guests.get(id);
        if (guest != null) {
            guest.printActivities();
        } else {
            System.out.println("Guest not found.");
        }
    }

    public void viewAllGuests() {
        if (guests.isEmpty()) {
            System.out.println("No guests to show.");
            return;
        }
        for (Guest guest : guests.values()) {
            guest.printActivities();
            System.out.println("-----------------------------");
        }
    }

    public void resetDay() {
        for (Guest guest : guests.values()) {
            guest.resetActivities();
        }
        System.out.println("All activity logs cleared for new day.");
    }
}

class SuspiciousHotelSystem {
    public static void main(String[] args) {
        HotelSystem system = new HotelSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== Hotel Suspicious Activity System =====");
            System.out.println("1. Check-in");
            System.out.println("2. Check-out");
            System.out.println("3. View Guest Details");
            System.out.println("4. View All Guests");
            System.out.println("5. Reset All Activities (End of Day)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    system.checkIn();
                    break;
                case 2:
                    system.checkOut();
                    break;
                case 3:
                    system.viewGuestDetails();
                    break;
                case 4:
                    system.viewAllGuests();
                    break;
                case 5:
                    system.resetDay();
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
