package sj.sjesl.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.dto.lab.LabNoticeUpdateRequestDto;
import sj.sjesl.dto.lab.LabSaveRequestDto;
import sj.sjesl.entity.Lab;
import sj.sjesl.entity.Member;

import sj.sjesl.payload.Response;
import sj.sjesl.repository.LabRepository;
import sj.sjesl.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabService {
    private final LabRepository labRepository;
    private final MemberRepository memberRepository;
    private final Response response;

    @Transactional
    public ResponseEntity<?> save(LabSaveRequestDto requestDto) {
        Member member = memberRepository.findByMemberId(requestDto.getMemberId());
        if (member==null) return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);

        Lab lab = Lab.builder()
                .member(member)
                .location(requestDto.getLocation())
                .build();

        labRepository.save(lab);
        return response.success(lab.toString(),"연구실 추가에 성공하였습니다.",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> noticeUpdate(Long id, LabNoticeUpdateRequestDto requestDto) {
        Optional<Lab> lab = labRepository.findById(id);
        if (lab==null) return response.fail("해당하는 연구실이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);

        Lab findLab= lab.get();
        findLab.noticeUpdate(requestDto.getNotice(),requestDto.getContent(),requestDto.getStartDate(),requestDto.getEndDate());

        return response.success("공지사항 변경이 완료되었습니다.");
    }

    @Transactional
    public ResponseEntity<?> stateUpdate(Long id, String state) {
        Optional<Lab> lab = labRepository.findById(id);
        if (lab==null) return response.fail("해당하는 연구실이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);

        Lab findLab= lab.get();
        findLab.setState(state);

        return response.success("상태 변경이 완료되었습니다.");
    }
//
//    public InquiryResponseDto findById(Long id) {
//        ReservationInquiry entity = inquiryRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));
//
//        return new InquiryResponseDto(entity);
//    }
//
//    @Transactional(readOnly = true)
//    public List<InquiryListResponseDto> findAllDesc() {
//        return inquiryRepository.findAllDesc().stream()
//                .map(InquiryListResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public void delete(Long id){
//        ReservationInquiry inquiry = inquiryRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));
//
//        inquiryRepository.delete(inquiry);
//    }
}
