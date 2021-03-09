
/**
 * This class represents a library patron that has a name and assigns values to different literary
 * aspects of books.
 * @author Omer Salman
 *
 */
class Patron {
	
	/** The first name of the patron. */
	final String firstName;
	
	/** The last name of the patron. */
	final String lastName;
	
	/** The weight the patron assigns to the comic aspects of books. */
	int comicTend;
	
	/** The weight the patron assigns to the dramatic aspects of books. */
	int dramaticTend;
	
	/** The weight the patron assigns to the educational aspects of books. */
	int educationalTend;
	
	/** The minimal literary value a book must have for this patron to enjoy it. */
	int enjoymentThreshold;
	
	/*----=  Constructors  =-----*/
	
	/**
	 * Creates a new patron with the given characteristics.
	 * @param patronFirstName The first name of the patron.
	 * @param patronLastName The last name of the patron.
	 * @param comicTendency The weight the patron assigns to the comic aspects of books.
	 * @param dramaticTendency The weight the patron assigns to the dramatic aspects of books.
	 * @param educationalTendency The weight the patron assigns to the educational aspects of books.
	 * @param patronEnjoymentThreshold The minimal literary value a book must have for this patron to
	 *  enjoy it.
	 */
	Patron(String patronFirstName, String patronLastName,
			int comicTendency, int dramaticTendency,
			int educationalTendency, int patronEnjoymentThreshold){
		firstName = patronFirstName;
		lastName = patronLastName;
		comicTend = comicTendency;
		dramaticTend= dramaticTendency;
		educationalTend = educationalTendency;
		enjoymentThreshold = patronEnjoymentThreshold;
	}
	
	/*----=  Instance Methods  =-----*/
	
	/**
	 * Returns a string representation of the patron, which is a sequence of its first and last name,
	 * separated by a single white space. 
	 * For example, if the patron's first name is "Ricky" and his last name is "Bobby",
	 * this method will return the String "Ricky Bobby".
	 * @return the String representation of this patron.
	 */
	String stringRepresentation() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Returns the literary value this patron assigns to the given book.
	 * @param book - The book to asses.
	 * @return the literary value this patron assigns to the given book.
	 */
	int getBookScore(Book book) {
		return comicValueCal(book) + dramaticValueCal(book) + educationalValueCal(book);
	}
	
	/**
	 * calculates the comic value of a book relative to The weight a patron assigns
	 *  to the comic aspects of books.
	 * @param book - the book to calculate it's comic value relative to The weight a patron assigns
	 *  to the comic aspects of books.
	 * @return the calculated comic value of the book relative to The weight a patron assigns
	 *  to the comic aspects of books.
	 */
	int comicValueCal(Book book){
		return book.comicValue * comicTend;
	}
	
	/**
	 * calculates the dramatic value of a book relative to The weight a patron assigns
	 *  to the comic aspects of books.
	 * @param book - the book to calculate it's dramatic value relative to The weight a patron assigns
	 *  to the dramatic aspects of books.
	 * @return the calculated dramatic value of the book relative to The weight a patron assigns
	 *  to the dramatic aspects of books.
	 */
	int dramaticValueCal(Book book){
		return book.dramaticValue * dramaticTend;
	}
	
	/**
	 * calculates the educational value of a book relative to The weight a patron assigns
	 *  to the educational aspects of books.
	 * @param book - the book to calculate it's educational value relative to The weight a patron assigns
	 *  to the educational aspects of books.
	 * @return the calculated educational value of the book relative to The weight a patron assigns
	 *  to the educational aspects of books.
	 */
	int educationalValueCal(Book book){
		return book.educationalValue * educationalTend;
	}
	
	/**
	 * Returns true of this patron will enjoy the given book, false otherwise.
	 * @param book - The book to asses.
	 * @return true of this patron will enjoy the given book, false otherwise.
	 */
	boolean willEnjoyBook(Book book){
		if (getBookScore(book) >= enjoymentThreshold)
			return true;
		else
			return false;
	}

}
