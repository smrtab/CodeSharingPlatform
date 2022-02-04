package platform.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.data.remote.entity.RemoteCode;
import platform.data.remote.entity.RemoteLatestCode;
import platform.data.remote.request.CodeRequest;
import platform.data.remote.response.CodeResponse;
import platform.data.remote.response.EmptyJsonResponse;
import platform.service.CodeService;

import java.util.List;

@RestController
public class ApiCodeController {

    private final CodeService codeService;

    @Autowired
    public ApiCodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/api/code/latest")
    public List<RemoteCode> getCodeLatest() {
        return codeService.getCodeLatest();
    }

    @GetMapping("/api/code/{token}")
    public RemoteCode getCodeByToken(@PathVariable String token) {
        return codeService.getCodeByToken(token);
    }

    @PostMapping("/api/code/new")
    public CodeResponse setCode(@RequestBody CodeRequest codeRequest) {
        return codeService.setCode(codeRequest);
    }
}
