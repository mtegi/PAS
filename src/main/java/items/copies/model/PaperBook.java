package items.copies.model;

public class PaperBook implements BookType {
    private int pages;

    public PaperBook(int pages) {
        this.setPages(pages);
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
