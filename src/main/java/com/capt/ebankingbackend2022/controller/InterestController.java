package com.capt.ebankingbackend2022.controller;

import com.capt.ebankingbackend2022.dto.InterestDto;
import com.capt.ebankingbackend2022.dto.TransactionDto;
import com.capt.ebankingbackend2022.service.InterestService;
import com.capt.ebankingbackend2022.service.TransactionService;
import com.capt.ebankingbackend2022.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    private InterestService interestService;


    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<Page<InterestDto>>> getInterest(Pageable pageable) {
        return interestService.getInterests(pageable);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<InterestDto>> addInterest(@RequestBody InterestDto interestDto) {
        return interestService.addInterest(interestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<InterestDto>> updateInterest(@PathVariable("id") Long id, @RequestBody InterestDto interestDto) {
        return interestService.updateInterest(id , interestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Response<Boolean>> deleteInterest(@PathVariable("id") Long id) {
        return interestService.deleteInterest(id);
    }



}
