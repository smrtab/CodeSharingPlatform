package platform.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import platform.data.remote.entity.RemoteCode;
import platform.data.remote.entity.RemoteLatestCode;
import platform.data.remote.response.CodeResponse;
import platform.service.CodeService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        List<RemoteCode> codes = codeService.getCodeLatest();
        model.addAttribute("codes", codes);
        return "latest_code";
    }

    @GetMapping("/code/{token}")
    public String getCodeById(@PathVariable String token, Model model) {
        RemoteCode remoteCode = codeService.getCodeByToken(token);
        model.addAttribute("code", remoteCode.getCode());
        model.addAttribute("date", remoteCode.getDate());
        model.addAttribute("time", remoteCode.getTime());
        model.addAttribute("views", remoteCode.getViews());
        model.addAttribute("isTimeRestricted", remoteCode.isTimeRestricted());
        model.addAttribute("isViewsRestricted", remoteCode.isViewsRestricted());

        return "code";
    }

    @GetMapping("/code/new")
    public String getNewCode() {
        return "code_new";
    }
}
