package edu.westga.cs1302.task_tracker.model;

/** Stores basic information for a Comic
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Comic {

	private final String title;
    private final String issueNumber;

    /**
     * Create a new comic with a title and issue number.
     * 
     * @precondition title != null && !title.isEmpty() 
     *               issueNumber != null && !issueNumber.isEmpty()
     * @param title the title of the comic
     * @param issueNumber the issue number of the comic
     */
    public Comic(String title, String issueNumber) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title must not be null or empty");
        }
        if (issueNumber == null || issueNumber.isEmpty()) {
            throw new IllegalArgumentException("issue number must not be null or empty");
        }
        if (!issueNumber.matches("\\d+")) {
            throw new IllegalArgumentException("issue number must contain only digits");
        }
        this.title = title;
        this.issueNumber = issueNumber;
    }

    /** Return the title of the comic
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the title of the comic
	 */
    public String getTitle() {
        return this.title;
    }

    /** Return the issue number of the comic
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the issue number of the comic
	 */
    public String getIssueNumber() {
        return this.issueNumber;
    }

    @Override
    public String toString() {
        return this.title + " " + this.issueNumber;
    }
}
