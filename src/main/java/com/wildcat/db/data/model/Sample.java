package com.wildcat.db.data.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Sample {
    public enum Type {
        BACKGROUND(0),
        STANDARD(1),
        ANALYZE(2),
        QUALITY(3);

        int value;

        Type(int type) {
            value = type;
        }

        public int getValue() {
            return value;
        }
    }

    @Id
    private ObjectId id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private ObjectId projectId;
    private ObjectId wellId;
    private ObjectId wellboreId;
    private double depth;
    private Type type;

    private List<ObjectId> curveIds;

    public Sample() {
        curveIds = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public double getDepth() {
        return depth;
    }

    public Type getType() {
        return type;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void addCurve(Curve curve) {
        if (!curveIds.contains(curve.getId())) {
            curveIds.add(curve.getId());
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void addCurves(List<Curve> curves) {
        curveIds.clear();
        for (Curve curve : curves) {
            curveIds.add(curve.getId());
        }
    }
}
