import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		MyTreeMap<String, String> tm = new MyTreeMap<String, String>();
		while (s.hasNextLine()) {
			Scanner inputLine = new Scanner(s.nextLine());
			String command = inputLine.next();
			String first = "", last = "", number = "";
			if (!command.equals("list")) {
				first = inputLine.next();
				last = inputLine.next();
			}
			if (command.equals("add"))
				number = inputLine.next();
			switch (command) {
				case "find":
					System.err.println(">>> Finding " + first + " " + last);
					String found = tm.get(new Entry<String, String>(
							(last + "," + first), null));
					try {
						if (found.equals(null))
							System.out.println(first + " " + last
									+ " not found.");
						else
							System.out.println(first + " " + last + ": "
									+ found);
					} catch (Exception e) {
						System.out.println(first + " " + last + " not found.");
					}
					break;
				case "add":
					System.err.println(">>> Adding " + first + " " + last
							+ ", " + number);
					tm.put(new Entry<String, String>((last + "," + first),
							number));
					break;
				case "delete":
					System.err.println(">>> Deleting " + first + " " + last);
					String deleted = tm.remove(new Entry<String, String>((last
							+ "," + first), null));
					if (deleted != null)
						System.out.println(first + " " + last + ": " + deleted
								+ " deleted.");
					else
						System.out.println(first + " " + last
								+ " doesn't exist.");
					break;
				case "list":
					System.err.println(">>> Listing all contacts");
					System.out.println(tm.toString().substring(0,
							tm.toString().length() - 2));
					break;
			}
		}
	}
}
