import java.util.*;

class Customer {
    private String name;
    private Vector rentals = new Vector();

    public Customer(String newname) {
        name = newname;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        Enumeration enum_rentals = rentals.elements();
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + "\t" + each.getDaysRented() + "\t" + each.getCharge() + "\n";
        }

        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRentalPoints() + " frequent renter points";
        return result;
    }

    public double getTotalCharge() {
        return rentals.stream()
                .mapToDouble(rental -> ((Rental) rental).getCharge())
                .sum();
    }

    public int getTotalFrequentRentalPoints() {
        return rentals.stream()
                .mapToInt(rental -> ((Rental) rental).getFrequentRenterPoints())
                .sum();
    }
}
