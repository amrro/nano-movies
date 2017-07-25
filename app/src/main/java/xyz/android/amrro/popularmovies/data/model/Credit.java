package xyz.android.amrro.popularmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class Credit {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = new ArrayList<Cast>();

    /**
     * No args constructor for use in serialization
     */
    public Credit() {
    }

    /**
     * @param id
     * @param cast
     */
    public Credit(Integer id, List<Cast> cast) {
        super();
        this.id = id;
        this.cast = cast;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;

        Credit credit = (Credit) o;

        if (getId() != null ? !getId().equals(credit.getId()) : credit.getId() != null)
            return false;
        return getCast() != null ? getCast().equals(credit.getCast()) : credit.getCast() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCast() != null ? getCast().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", cast=" + cast +
                '}';
    }
}