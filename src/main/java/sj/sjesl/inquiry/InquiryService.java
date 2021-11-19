package sj.sjesl.inquiry;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.entity.ReservationInquiry;
import sj.sjesl.repository.ReservationInquiryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final ReservationInquiryRepository inquiryRepository;

    @Transactional
    public Long save(InquirySaveRequestDto requestDto) {
        return inquiryRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, InquiryUpdateRequestDto requestDto) {
        ReservationInquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));

        inquiry.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public InquiryResponseDto findById(Long id) {
        ReservationInquiry entity = inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));

        return new InquiryResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<InquiryListResponseDto> findAllDesc() {
        return inquiryRepository.findAllDesc().stream()
                .map(InquiryListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        ReservationInquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));

        inquiryRepository.delete(inquiry);
    }
//
//    public void findByUserId(Long id){
//        ReservationInquiry inquiry = inquiryRepository.findBy
//
//    }
}