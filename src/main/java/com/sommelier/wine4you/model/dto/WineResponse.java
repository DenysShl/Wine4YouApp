package com.sommelier.wine4you.model.dto;

import com.sommelier.wine4you.model.dto.wine.WineResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WineResponse {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<WineResponseDto> content;
}
