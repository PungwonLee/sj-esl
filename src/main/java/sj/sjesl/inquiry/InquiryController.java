package sj.sjesl.inquiry;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/inquiry")    //문의내역들 , 변경해야함
    public String index(Model model) {  //변경해야함
        model.addAttribute("inquiry", inquiryService.findAllDesc());
        return "inquiry";
    }

    @GetMapping("/inquiry/save")    //문의 등록창
    public String inquirySave() {
        return "inquiry-save";
    }

    @GetMapping("/inquiry/update/{id}") //문의 내용 변경창
    public String inquiryUpdate(@PathVariable Long id, Model model) {
        InquiryResponseDto dto = inquiryService.findById(id);
        model.addAttribute("inquiry", dto);

        return "inquiry-update";
    }

}
