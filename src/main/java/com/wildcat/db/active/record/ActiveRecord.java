package com.wildcat.db.active.record;

public abstract class ActiveRecord<T> {
    protected T record;

    public abstract void save();

    public abstract void delete();

    public ActiveRecord<T> wrap(T record) {
        this.record = record;
        return this;
    }
}
