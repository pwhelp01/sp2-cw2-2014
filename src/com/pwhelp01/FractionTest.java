/**
 * Created by keith for the second coursework assignment.
 *
 * You need to recode this as a series of JUnit tests
 */
public class FractionTest {
    public static void main(String[] args) {

        // test divide by zero - should print an error and exit
        new Fraction(1, 0);
        // test multiply
        assert ((new Fraction(3, 10)).equals(new Fraction(1, 2).multiply(new Fraction(3, 5)));
        // test equals
        assert (new Fraction(1, 2).equals(new Fraction(1, 2)));
        assert (new Fraction(1, 2).equals(new Fraction(3, 6)));
        assert (new Fraction(-1, 2).equals(new Fraction(1, -2)));
        assert (new Fraction(-1, -2).equals(new Fraction(1, 2)));

        // extend with extra tests
    }

}
