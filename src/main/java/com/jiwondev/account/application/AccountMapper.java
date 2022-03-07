package com.jiwondev.account.application;

import com.jiwondev.account.domain.dto.AccountRegisterCommand;
import com.jiwondev.account.interfaces.SignUpForm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring", // Spring Bean 으로 생성
    injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입
    unmappedTargetPolicy = ReportingPolicy.ERROR // 매핑 실패시 컴파일 에러
)
public interface AccountMapper {

    AccountRegisterCommand toCommand(SignUpForm form);
}
