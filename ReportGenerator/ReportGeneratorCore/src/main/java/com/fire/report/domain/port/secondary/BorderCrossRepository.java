package com.fire.report.domain.port.secondary;

import com.fire.report.domain.model.BorderCrossing;

public interface BorderCrossRepository {
    void save(BorderCrossing borderCrossing);
}
