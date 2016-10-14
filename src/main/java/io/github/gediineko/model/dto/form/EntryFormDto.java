package io.github.gediineko.model.dto.form;

import io.github.gediineko.model.base.dto.BaseWebDto;
import io.github.gediineko.model.entity.Entry;
import io.github.gediineko.model.ref.Category;
import io.github.gediineko.model.ref.Recurrence;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by ggolong on 10/7/16.
 */
public class EntryFormDto extends BaseWebDto<Entry> {

    public EntryFormDto() {
        super(new Entry());
    }

    public EntryFormDto(Entry target) {
        super(target);
    }

    public Long getId() {
        return getTarget().getId();
    }

    public String getDescription() {
        return getTarget().getDescription();
    }

    public Double getValue() {
        return getTarget().getValue();
    }

    public void setRecurrence(Recurrence recurrence) {
        getTarget().setRecurrence(recurrence);
    }

    public Category getCategory() {
        return getTarget().getCategory();
    }

    public Recurrence getRecurrence() {
        return getTarget().getRecurrence();
    }

    public void setId(Long id) {
        getTarget().setId(id);
    }

    public void setValue(Double value) {
        getTarget().setValue(value);
    }

    public void setDescription(String description) {
        getTarget().setDescription(description);
    }

    public void setCategory(Category category) {
        getTarget().setCategory(category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("value", getValue())
                .append("description", getDescription())
                .append("category", getCategory())
                .append("recurrence", getRecurrence())
                .toString();
    }
}
