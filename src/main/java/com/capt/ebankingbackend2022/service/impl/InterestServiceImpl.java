package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.dto.InterestDto;
import com.capt.ebankingbackend2022.entity.InterestEntity;
import com.capt.ebankingbackend2022.repository.InterestRepository;
import com.capt.ebankingbackend2022.service.InterestService;
import com.capt.ebankingbackend2022.utils.InterestType;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class InterestServiceImpl extends BaseServiceImpl implements InterestService {
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public ResponseEntity<Response<InterestDto>> addInterest(InterestDto interestDto) {
        String errorMessage = validateInterest(interestDto);
        if (errorMessage != null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, errorMessage, null), HttpStatus.BAD_REQUEST);
        }
        InterestEntity interestEntity = modelMapper.map(interestDto, InterestEntity.class);
        interestEntity.setCreatedAt(new Date());
        interestEntity = interestRepository.save(interestEntity);
        interestDto = modelMapper.map(interestEntity, InterestDto.class);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", interestDto), HttpStatus.OK);
    }

    private String validateInterest(InterestDto interestDto) {
        String errorMessage = null;

        if (interestDto.getDuration() < TimeUnit.DAYS.toMillis(1)) {
            errorMessage = "Duration must be greater 1 day";
        }
        if (interestDto.getInstantRate() < 0 || interestDto.getInstantRate() > 100) {
            errorMessage = "Instant interest rate must be greater than 0 and lower than 100";
        }
        if (interestDto.getRate() < 0 || interestDto.getRate() > 100) {
            errorMessage = "Interest rate must be greater than 0 and lower than 100";
        }
        if (!interestDto.getType().equals(InterestType.SAVING) && !interestDto.getType().equals(InterestType.LOAN)) {
            errorMessage = "Interest type must be saving or loan";
        }
        return errorMessage;
    }

    @Override
    public ResponseEntity<Response<Page<InterestDto>>> getInterests(Pageable pageable) {
        Page<InterestEntity> interestEntityPage;
        try {
            interestEntityPage = interestRepository.findAll(pageable);
        } catch (PropertyReferenceException exception) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "sort param not found"), HttpStatus.BAD_REQUEST);
        }
        Page<InterestDto> accountDtoPage = interestEntityPage.map(interestEntity -> modelMapper.map(interestEntity, InterestDto.class));
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "success", accountDtoPage), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Boolean>> deleteInterest(Long id) {
        if (!interestRepository.existsById(id))
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest not found", false), HttpStatus.BAD_REQUEST);
        interestRepository.deleteById(id);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "delete success", true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Response<InterestDto>> updateInterest(Long id, InterestDto interestDto) {
        InterestEntity interestEntity = interestRepository.findById(id).orElse(null);
        if (interestEntity == null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_FAILED, "interest not found", interestDto), HttpStatus.BAD_REQUEST);
        }
        String errorMessage = validateInterest(interestDto);
        if (errorMessage != null) {
            return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, errorMessage, null), HttpStatus.BAD_REQUEST);
        }
        interestEntity.setDuration(interestDto.getDuration());
        interestEntity.setRate(interestDto.getRate());
        interestEntity.setInstantRate(interestDto.getInstantRate());
        interestEntity.setType(interestDto.getType());
        interestEntity.setUpdatedAt(new Date());
        interestRepository.save(interestEntity);
        return new ResponseEntity<>(new Response<>(Response.STATUS_SUCCESS, "update success", modelMapper.map(interestEntity, InterestDto.class)), HttpStatus.OK);
    }
}
