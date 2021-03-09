
/**
 * This class represents a library, which hold a collection of books.
 * Patrons can register at the library to be able to check out books,
 * if a copy of the requested book is available.
 * @author Omer Salman
 */

class Library {
	
	/** An array of the library's books */
	Book [] bookArray;
	
	/** An array of the library's patrons */
	Patron [] patronArray;
	
	/** The maximal number of books this library allows a single patron to borrow at the same time */
	int maxBorrBooks;
	
	/** The library's books array length */
	int bookArrayLength;
	
	/** The library's patrons array length */ 
	int patronArrayLength;
	
	/*----=  Constructors  =-----*/
	
	/**
	 * Creates a new library with the given parameters.
	 * @param maxBookCapacity The maximal number of books this library can hold.
	 * @param maxBorrowedBooks The maximal number of books this library allows a single patron
	 *  to borrow at the same time.
	 * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
	 */
	Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
		bookArray = new Book [maxBookCapacity];
		patronArray = new Patron [maxPatronCapacity];
		maxBorrBooks = maxBorrowedBooks;
		bookArrayLength = maxBookCapacity;
		patronArrayLength = maxPatronCapacity;
	}
	
	/*----=  Instance Methods  =-----*/
	
	/**
	 * Adds the given book to this library, if there is place available,
	 *  and it isn't already in the library.
	 * @param book - The book to add to this library.
	 * @return a non-negative id number for the book if there was a spot and the book
	 *  was successfully added, or if the book was already in the library;
	 *   a negative number otherwise.
	 */
	int addBookToLibrary(Book book){
		for (int i = 0; i < bookArrayLength; i++ ) {
			if (bookArray[i] == book)
				return i;
			else if (bookArray[i] == null) {
				bookArray[i] = book;
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns true if the given number is an id of some book in the library, false otherwise.
	 * @param bookId - The id to check.
	 * @return true if the given number is an id of some book in the library, false otherwise.
	 */
	boolean isBookIdValid(int bookId) {
		if (bookId >= bookArrayLength || bookId < 0)
			return false;
		if (bookArray[bookId] == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns the non-negative id number of the given book if he is
	 *  owned by this library, -1 otherwise.
	 * @param book - The book for which to find the id number.
	 * @return a non-negative id number of the given book if he is
	 *  owned by this library, -1 otherwise.
	 */
	int getBookId(Book book) {
		for (int i = 0; i < bookArrayLength; i++ ) {
			if (bookArray[i] == book)
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns true if the book with the given id is available, false otherwise.
	 * @param bookId - The id number of the book to check.
	 * @return true if the book with the given id is available, false otherwise.
	 */
	boolean isBookAvailable(int bookId) {
		if (isBookIdValid(bookId))
			if (bookArray[bookId].getCurrentBorrowerId() == -1)
				return true;
		return false;
	}
	
	/**
	 * Registers the given Patron to this library, if there is a spot available.
	 * @param patron
	 * @return a non-negative id number for the patron if there was a spot and
	 * the patron was successfully registered or if the patron was already registered.
	 * a negative number otherwise.
	 */
	int registerPatronToLibrary(Patron patron) {
		for (int i = 0; i < patronArrayLength; i++ ) {
			if (patronArray[i] == patron)
				return i;
			else if (patronArray[i] == null) {
				patronArray[i] = patron;
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns true if the given number is an id of a patron in the library, false otherwise.
	 * @param patronId - The id to check.
	 * @return true if the given number is an id of a patron in the library, false otherwise.
	 */
	boolean isPatronIdValid(int patronId) {
		if (patronId >= patronArrayLength || patronId < 0)
			return false;
		if (patronArray[patronId] == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns the non-negative id number of the given patron if he is
	 *  registered to this library, -1 otherwise.
	 * @param patron - The patron for which to find the id number.
	 * @return a non-negative id number of the given patron if he is
	 *  registered to this library, -1 otherwise.
	 */
	int getPatronId(Patron patron) {
		for (int i = 0; i < patronArrayLength; i++ ) {
			if (patronArray[i] == patron)
				return i;
			}
		return -1;
	}
	
	/**
	 * Marks the book with the given id number as borrowed by the patron with the given patron id,
	 * if this book is available, the given patron isn't already borrowing the maximal number
	 *  of books allowed, and if the patron will enjoy this book.
	 * @param bookId - The id number of the book to borrow.
	 * @param patronId - The id number of the patron that will borrow the book.
	 * @return true if the book was borrowed successfully, false otherwise.
	 */
	boolean borrowBook(int bookId, int patronId) {
		if (checkValidAndAvailable(bookId, patronId)) {
			if (didntBorrowMaxBooks(patronId)) {
				Book currentBook = bookArray[bookId];
				Patron currentPatron = patronArray[patronId];
				if (currentPatron.willEnjoyBook(currentBook)) {
					currentBook.setBorrowerId(patronId);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a book's ID and patron's ID is valid,
	 * if the book's ID is valid, checks if the book is available.
	 * @param bookId - the book's id number to check.
	 * @param patronId - the patron's id number to check.
	 * @return true if the book's id and the patron's id is valid and if
	 *  the book is available, false otherwise.
	 */
	boolean checkValidAndAvailable(int bookId, int patronId) {
		if (isBookAvailable(bookId))
			if (isPatronIdValid(patronId))
				return true;
		return false;
	}
	
	/**
	 * Checks if a patron borrowed the maximal books number allowed to borrow.
	 * @param patronId - the patron's ID number to check.
	 * @return true if the patron didn't borrow the maximal books number allowed
	 *  to borrow, false otherwise.
	 */
	boolean didntBorrowMaxBooks(int patronId) {
		int patronBooksCounter = 0;
		for (int i = 0; i < bookArrayLength; i++) {
			if (bookArray[i] == null) { continue; }
			if (bookArray[i].getCurrentBorrowerId() == patronId)
				patronBooksCounter++;
			if (patronBooksCounter >= maxBorrBooks)
				return false;
		}
		return true;
	}
	
	/**
	 * Return the given book.
	 * @param bookId - The id number of the book to return.
	 */
	void returnBook(int bookId) {
		if (isBookIdValid(bookId))
			bookArray[bookId].setBorrowerId(-1);
	}	
	
	/**
	 * Suggest the patron with the given id the book he will enjoy the most,
	 * out of all available books he will enjoy, if any such exist.
	 * @param patronId - The id number of the patron to suggest the book to.
	 * @return The available book the patron with the given ID will enjoy the most.
	 *  Null if no book is available.
	 */
	Book suggestBookToPatron(int patronId) {
		if (isPatronIdValid(patronId)) {
			Patron currentPatron = patronArray[patronId];
			Book bestBook = bookArray[0];
			int bestBookId = 0;
			for (int i = 1; i < bookArrayLength; i++) {
				int currentBScore = currentPatron.getBookScore(bookArray[i]);
				int bestBScore = currentPatron.getBookScore(bestBook);
				if (isBookAvailable(i))
					if (currentBScore > bestBScore) {
						bestBook = bookArray[i];
						bestBookId = i;
					}
			}
			if (currentPatron.willEnjoyBook(bestBook) && isBookAvailable(bestBookId))
				return bestBook;
		}
		return null;
	}

}
