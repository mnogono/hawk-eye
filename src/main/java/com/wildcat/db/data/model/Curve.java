package com.wildcat.db.data.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Curve {
    public enum Type {
        UNKNOWN(-1),
        TIME(0),
        FID(1),
        TEMPERATURE(2),
        CO(5),
        CO2(6),
        FID_AIR_FLOW(7),
        CARRIER_FLOW(8),
        SPLIT_FLOW(9),
        FID_HYDROGEN_FLOW(10);

        int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            Type []types = values();
            for (Type type : types) {
                if (value == type.value) {
                    return type;
                }
            }

            return UNKNOWN;
        }
    }

    public enum Kind {
        UNKNOWN(-1),
        ORIGINAL(0),
        CALIBRATED(1);

        int value;

        Kind(int value) {
            this.value = value;
        }

        public static Kind fromValue(int value) {
            Kind []kinds = values();
            for (Kind kind : kinds) {
                if (value == kind.value) {
                    return kind;
                }
            }

            return UNKNOWN;
        }
    }

    public enum Unit {
        MINUTE,
        CELSIUM,
        VOLT
    }

    public enum UnitFactor {
        unknown(1),
        m(0.001);

        double factor;

        UnitFactor(double factor) {
            this.factor = factor;
        }
    }

    @Id
    private ObjectId id;
    private Type type;
    private Unit unit;
    private UnitFactor unitFactor;
    private List<Double> data;
    private Kind kind;

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public UnitFactor getUnitFactor() {
        return unitFactor;
    }

    public void setUnitFactor(UnitFactor unitFactor) {
        this.unitFactor = unitFactor;
    }

    public Unit getUnit() {
        return unit;
    }

    public List<Double> getData() {
        return data;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
