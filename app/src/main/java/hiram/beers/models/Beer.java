package hiram.beers.models;

public class Beer {
    String name, description, tagline, image;

    public Beer(String name, String description, String tagline, String image) {
        this.name = name;
        this.description = description;
        this.tagline = tagline;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
