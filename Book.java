
public class Book {
    public String title;
    public String author;
    public Book(String title,String author){
        this.title=title;
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
        }
        public void setAuthor(String author){
            this.author=author;
            }
            public void setTitle(String title){
                this.title=title;
                }
}