package com.wildcat.db.data.model.qc;

public abstract class QCIssue {
    enum Type {
        PLAIN_TEXT,
        CURVE_OUT_OF_BOUNDS
    }

    protected Type type;
    protected String error;
}
