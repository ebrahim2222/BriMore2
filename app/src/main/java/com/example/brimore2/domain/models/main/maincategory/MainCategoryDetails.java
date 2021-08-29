package com.example.brimore2.domain.models.main.maincategory;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class MainCategoryDetails implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("colorCode")
    @Expose
    private String colorCode;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("children")
    @Expose
    private List<MainCategoryDetailsChild> children = null;

    protected MainCategoryDetails(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        image = in.readString();
        colorCode = in.readString();
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
    }

    public static final Creator<MainCategoryDetails> CREATOR = new Creator<MainCategoryDetails>() {
        @Override
        public MainCategoryDetails createFromParcel(Parcel in) {
            return new MainCategoryDetails(in);
        }

        @Override
        public MainCategoryDetails[] newArray(int size) {
            return new MainCategoryDetails[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<MainCategoryDetailsChild> getChildren() {
        return children;
    }

    public void setChildren(List<MainCategoryDetailsChild> children) {
        this.children = children;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(colorCode);
        if (order == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(order);
        }
    }
}
