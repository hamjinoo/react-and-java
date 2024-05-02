package org.zerock.apiserver.dto;

import lombok.Builder;
import lombok.experimental.SuperBuilder;


@SuperBuilder //상속
public class PageRequestDTO {
    
    @Builder.Default
    private int page = 1;
    
    @Builder.Default
    private int size = 10;
}
