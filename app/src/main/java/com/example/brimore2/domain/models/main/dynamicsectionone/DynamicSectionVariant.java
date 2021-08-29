package com.example.brimore2.domain.models.main.dynamicsectionone;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicSectionVariant implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;
    @SerializedName("original_price")
    @Expose
    private Integer originalPrice;
    @SerializedName("member_price")
    @Expose
    private Integer memberPrice;
    @SerializedName("consumer_price")
    @Expose
    private Integer consumerPrice;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("attributes")
    @Expose
    private List<DynamicSectionVariantAttribute> attributes = null;
    @SerializedName("tag")
    @Expose
    private Object tag;
    @SerializedName("slider")
    @Expose
    private Object slider;
    @SerializedName("add_points")
    @Expose
    private Integer addPoints;
    @SerializedName("low_limit")
    @Expose
    private Integer lowLimit;
    @SerializedName("low_quantity")
    @Expose
    private Object lowQuantity;

    protected DynamicSectionVariant(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        image = in.readString();
        code = in.readString();
        if (in.readByte() == 0) {
            availableQuantity = null;
        } else {
            availableQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            originalPrice = null;
        } else {
            originalPrice = in.readInt();
        }
        if (in.readByte() == 0) {
            memberPrice = null;
        } else {
            memberPrice = in.readInt();
        }
        if (in.readByte() == 0) {
            consumerPrice = null;
        } else {
            consumerPrice = in.readInt();
        }
        if (in.readByte() == 0) {
            points = null;
        } else {
            points = in.readInt();
        }
        if (in.readByte() == 0) {
            addPoints = null;
        } else {
            addPoints = in.readInt();
        }
        if (in.readByte() == 0) {
            lowLimit = null;
        } else {
            lowLimit = in.readInt();
        }
    }

    public static final Creator<DynamicSectionVariant> CREATOR = new Creator<DynamicSectionVariant>() {
        @Override
        public DynamicSectionVariant createFromParcel(Parcel in) {
            return new DynamicSectionVariant(in);
        }

        @Override
        public DynamicSectionVariant[] newArray(int size) {
            return new DynamicSectionVariant[size];
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Integer memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Integer getConsumerPrice() {
        return consumerPrice;
    }

    public void setConsumerPrice(Integer consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<DynamicSectionVariantAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DynamicSectionVariantAttribute> attributes) {
        this.attributes = attributes;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Object getSlider() {
        return slider;
    }

    public void setSlider(Object slider) {
        this.slider = slider;
    }

    public Integer getAddPoints() {
        return addPoints;
    }

    public void setAddPoints(Integer addPoints) {
        this.addPoints = addPoints;
    }

    public Integer getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(Integer lowLimit) {
        this.lowLimit = lowLimit;
    }

    public Object getLowQuantity() {
        return lowQuantity;
    }

    public void setLowQuantity(Object lowQuantity) {
        this.lowQuantity = lowQuantity;
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
        dest.writeString(code);
        if (availableQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(availableQuantity);
        }
        if (originalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(originalPrice);
        }
        if (memberPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(memberPrice);
        }
        if (consumerPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(consumerPrice);
        }
        if (points == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(points);
        }
        if (addPoints == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(addPoints);
        }
        if (lowLimit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(lowLimit);
        }
    }
}