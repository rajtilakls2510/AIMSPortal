package database.models;

import java.time.LocalDate;

public class Batch {
    private Integer id;
    private LocalDate startMonth;
    private LocalDate endMonth;
    private Integer duration;

    public Batch(
            Integer id,
            LocalDate startMonth,
            LocalDate endMonth,
            Integer duration
    ) {
        this.id = id;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(LocalDate startMonth) {
        this.startMonth = startMonth;
    }

    public LocalDate getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(LocalDate endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                ", duration=" + duration +
                '}';
    }
}
