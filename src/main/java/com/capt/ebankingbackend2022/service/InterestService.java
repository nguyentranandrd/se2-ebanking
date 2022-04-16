package com.capt.ebankingbackend2022.service;

import com.capt.ebankingbackend2022.dto.InterestDto;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InterestService {
    ResponseEntity<Response<InterestDto>> addInterest(InterestDto interestDto);

    ResponseEntity<Response<Page<InterestDto>>> getInterests(Pageable pageable);

    ResponseEntity<Response<Boolean>> deleteInterest(Long id);

    ResponseEntity<Response<InterestDto>> updateInterest(Long id, InterestDto interestDto);
}
