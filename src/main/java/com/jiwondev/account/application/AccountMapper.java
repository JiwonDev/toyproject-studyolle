package com.jiwondev.account.application;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring", // Spring Bean (@Component 추가)
    injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입
    unmappedTargetPolicy = ReportingPolicy.ERROR // 매핑 실패시 컴파일 에러
)
interface AccountMapper {

}
