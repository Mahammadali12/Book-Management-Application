public class Book {
    public static Integer Id=1;
    public static Integer AdminId=1;
    private Integer BookId;
    private String Title;
    private String Author;
    private Double Rating;
    private Integer RatingCount;
    private String Reviews;
    private String Status;
    private Integer TimeSpent;
    private String StartDate;
    private String EndDate;

    
    Book(Integer BookId,String Title,String Author,Double Rating,String Reviews,String Status, Integer TimeSpent,String StartDate,String EndDate){
        this.BookId=BookId;
        this.Title=Title;
        this.Author=Author;
        this.Rating=Rating;
        this.Reviews=Reviews;
        this.Status=Status;
        this.TimeSpent=TimeSpent;
        this.StartDate=StartDate;
        this.EndDate=EndDate;
    }

    public Integer getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        BookId = bookId;
    }

    public Integer getRatingCount() {
        return RatingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        RatingCount = ratingCount;
    }
    
    public Integer getTimeSpent() {
        return TimeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        TimeSpent = timeSpent;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    Book(String Title,String Author){
        this.Title=Title;
        this.Author=Author;
    }
    Book(){

    }

    @Override
    public String toString() {
        return BookId+","+Title+","+Author+","+Rating+","+Reviews+","+Status+","+TimeSpent+","+StartDate+","+EndDate;
    }

    public String toStringGeneral() {
        return Title+","+Author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public String getReviews() {
        return Reviews;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }
    public String getStartDate() {
        return StartDate;
    }
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }
    public String getEndDate() {
        return EndDate;
    }
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}