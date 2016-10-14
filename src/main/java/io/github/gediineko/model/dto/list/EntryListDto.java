package io.github.gediineko.model.dto.list;

import io.github.gediineko.model.base.dto.BaseWebDto;
import io.github.gediineko.model.entity.Entry;
import io.github.gediineko.model.ref.Category;
import io.github.gediineko.model.ref.Recurrence;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by ggolong on 10/7/16.
 */
public class EntryListDto extends BaseWebDto<Entry> {

    public EntryListDto(Entry target) {
        super(target);
    }

    public Long getId() {
        return getTarget().getId();
    }

    public Double getValue() {
        return getTarget().getValue();
    }

    public String getDesc() {
        return getTarget().getDescription();
    }

    public Category getCategory() {
        return getTarget().getCategory();
    }

    public Recurrence getRecurrence() {
        return getTarget().getRecurrence();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("value", getValue())
                .append("description", getDesc())
                .append("category", getCategory())
                .append("recurrence", getRecurrence())
                .toString();
    }
}
