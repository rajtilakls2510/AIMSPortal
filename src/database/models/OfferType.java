package database.models;

public class OfferType {

    private Batch batch;
    private CourseOffer offer;
    private OfferTypeEnum type;

    public OfferType(Batch batch, CourseOffer offer, OfferTypeEnum type) {
        this.batch = batch;
        this.offer = offer;
        this.type = type;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public CourseOffer getOffer() {
        return offer;
    }

    public void setOffer(CourseOffer offer) {
        this.offer = offer;
    }

    public OfferTypeEnum getType() {
        return type;
    }

    public void setType(OfferTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OfferType{" +
                "batch=" + batch +
                ", offer=" + offer +
                ", type=" + type +
                '}';
    }
}
