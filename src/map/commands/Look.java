package map.commands;

public class Look extends Command {
    @Override
    public String execute() {
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
    /*String direction = "";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> available = new ArrayList<>();
        available.add("nowhere");
        available.add("north");
        available.add("south");
        available.add("west");
        available.add("east");
        try {
            boolean repeat = true;
            while (repeat) {

                System.out.println("Where would you like to go ? " + available);
                direction = sc.nextLine();
                if (available.contains(direction)) {
                    repeat = false;
                } else {
                    repeat = true;
                    System.out.println("Wrong direction " + available);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong");
        }

        return "";*/
}
