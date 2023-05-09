package com.fire.report.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Report {

    private List<BorderCrossing> borderCrossingEvent;
}
