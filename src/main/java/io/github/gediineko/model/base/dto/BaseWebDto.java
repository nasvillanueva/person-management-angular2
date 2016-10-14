package io.github.gediineko.model.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ggolong on 10/4/16.
 */
public abstract class BaseWebDto<T> {

    private T target;

    protected BaseWebDto(T target){
        this.target = target;
    }

    @JsonIgnore
    public T getTarget() {
        return target;
    }
}
